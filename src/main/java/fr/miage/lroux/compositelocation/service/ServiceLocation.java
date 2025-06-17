package fr.miage.lroux.compositelocation.service;

import feign.FeignException;
import fr.miage.lroux.compositelocation.clients.*;
import fr.miage.lroux.compositelocation.dto.*;
import fr.miage.lroux.compositelocation.repository.RepoLocation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class ServiceLocation implements RepoLocation {

    private final AccessCardClients accessCardClients;

    private final LocationClients locationClients;

    private final CarClients carClients;

    private final UserClients userClients;

    private final StationClients stationClients;

    private final StatistiqueClients statistiqueClients;


    public ServiceLocation(AccessCardClients accessCardClients, LocationClients locationClients, CarClients carClients, UserClients userClients, StationClients stationClients, StatistiqueClients statistiqueClients) {
        this.accessCardClients = accessCardClients;
        this.locationClients = locationClients;
        this.carClients = carClients;
        this.userClients = userClients;
        this.stationClients = stationClients;
        this.statistiqueClients = statistiqueClients;
    }


    public UserWithCar createLocation(long userId, Car carId) throws Exception {

        // Check User
        User user = checkUserExists(userId);

        // Check AccessCard
        AccessCard accessCard = checkUserHasAccessCard(userId);

        // Check voiture disponible
        Car carLoue = checkVoitureAvailable(carId.getCarId());

        if(hasActiveLocationForUser(userId) || hasActiveLocationForCar(carId.getCarId())){
            throw new Exception("The car or the user are alrealdy active");
        }

        Station stationAfterUpdate =stationClients.updateAfterRetrievingCar(carLoue.getStationId());
        statistiqueClients.postStationHistorisation(stationAfterUpdate);

        Location location = new Location();

        // Création de la location
        location.setActive(true);
        location.setCarId(carId.getCarId());
        location.setAccessCardId(accessCard.getAccessCardid());
        location.setStationId(carLoue.getStationId());
        location.setUserId(userId);
        locationClients.createLocation(location);
        statistiqueClients.postLocationHistorisation(location);

        Car carAfterUpdate = carClients.putCar(carLoue.getCarId());
        statistiqueClients.postCarHistorisation(carAfterUpdate);

        return new UserWithCar(user,carLoue);
    }

    public Object returnCar(ReturnCarRequest request) throws Exception {

        // Vérifie que la voiture existe
        Car car = getCarOrThrow(request.getCarId());

        // Récupérer et vérifier station
        Station station = getStationOrThrow(request.getStationId());

        // Si station pleine → renvoyer alternatives
        if (station.getNbPlacesFree() == 0) {
            List<StationWithTime> alternatives = findNearestAvailableStations(car);
            if (alternatives.isEmpty()) {
                throw new Exception("Aucune station disponible pour restituer la voiture.");
            }
            return alternatives;
        }

        // Mise à jour de la voiture
        Car carAfterUpdate = updateCarAfterReturn(car, request);
        statistiqueClients.postCarHistorisation(carAfterUpdate);

        // Mise à jour de la location
        Location locationAfterUpdate =deactivateLocation(request.getLocationId());
        statistiqueClients.postLocationHistorisation(
                new LocationWithDistance(locationAfterUpdate, request.getDistanceTravelled()));

        // Mise à jour de la station
        Station stationAfterUpdate =stationClients.updateStationAddCar(station.getStationId());
        statistiqueClients.postStationHistorisation(stationAfterUpdate);

        return carAfterUpdate;
    }

    @Override
    public List<StationWithTime> getThreeClosestStations(Long carId) throws Exception {
        Car car = carClients.getCar(carId);
        List<Double> carLocation = car.getLocalisation();
        List<Station> stations = stationClients.getStations();

        stations.sort(Comparator.comparingDouble(
                station -> haversine(carLocation, station.getLocalisation())
        ));


        List<Station> nearestStations = stations.stream().limit(3).collect(Collectors.toList());
        // Générer des temps logiques
        Random rand = new Random();
        int time1 = rand.nextInt(20) + 1; // 1 à 5 min
        int time2 = time1 + rand.nextInt(20) + 7; // +1 à +4 min
        int time3 = time2 + rand.nextInt(20) + 14; // +1 à +4 min

        List<StationWithTime> result = new ArrayList<>();
        result.add(new StationWithTime(nearestStations.get(0), time1));
        result.add(new StationWithTime(nearestStations.get(1), time2));
        result.add(new StationWithTime(nearestStations.get(2), time3));

        return result;
    }


    private Location deactivateLocation(long locationId) throws Exception {
        Location location;
        try {
            location = locationClients.getLocation(locationId);
        } catch (FeignException.NotFound e) {
            throw new Exception("Erreur récupération location " + locationId);
        }
        try {
            return locationClients.updateLocation(location.getIdLocation());
        } catch (FeignException.NotFound e) {
            throw new Exception("Erreur mise à jour location " + location.getIdLocation());
        }
    }


    private Car updateCarAfterReturn(Car car, ReturnCarRequest request) throws Exception {
        car.setBatteryLevel(request.getBatteryLevel());
        car.setStationId(request.getStationId());
        car.setUsed(false);
        car.setKilometresTravelled(car.getKilometresTravelled() + request.getDistanceTravelled());

        try {
            return carClients.updateCar(car.getCarId(), car);
        } catch (FeignException.NotFound e) {
            throw new Exception("Erreur mise à jour voiture " + car.getCarId());
        }
    }


    private List<StationWithTime> findNearestAvailableStations(Car car) {

        List<Station> availableStations = stationClients.getStations().stream()
                .filter(s -> s.getNbPlacesFree() > 0)
                .collect(Collectors.toList());

        List<StationWithTime> sortedStations = availableStations.stream()
                .map(s -> {
                    double distance = calculateDistance(
                            car.getLocalisation().get(0), car.getLocalisation().get(1),
                            s.getLocalisation().get(0), s.getLocalisation().get(1)
                    );
                    return new StationWithTime(s, distance);
                })
                .sorted(Comparator.comparingDouble(StationWithTime::getDistance))
                .collect(Collectors.toList());

        // Générer des temps logiques
        Random rand = new Random();
        int baseTime = 5;
        for (int i = 0; i < sortedStations.size(); i++) {
            sortedStations.get(i).setTime(baseTime + i * 3 + rand.nextInt(3));
        }

        return sortedStations.stream().limit(3).collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = lat1 - lat2;
        double dLon = lon1 - lon2;
        return Math.sqrt(dLat * dLat + dLon * dLon);
    }

    private Car getCarOrThrow(long carId) throws Exception {
        try {
            return carClients.getCar(carId);
        } catch (FeignException.NotFound e) {
            throw new Exception("Voiture " + carId + " introuvable");
        }
    }

    private Station getStationOrThrow(long stationId) throws Exception {
        try {
            return stationClients.getStation(stationId);
        } catch (FeignException.NotFound e) {
            throw new Exception("Station " + stationId + " introuvable");
        }
    }


    private double haversine(List<Double> loc1, List<Double> loc2) {
        final int R = 6371;
        double lat1 = Math.toRadians(loc1.get(0));
        double lon1 = Math.toRadians(loc1.get(1));
        double lat2 = Math.toRadians(loc2.get(0));
        double lon2 = Math.toRadians(loc2.get(1));

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }


    private User checkUserExists(long userId) throws Exception {
        User user = null;
        try {
            user = userClients.getUser(userId);
        } catch (FeignException.NotFound e) {
            throw new Exception("Utilisateur " + userId + " introuvable");
        }
        return user;
    }

    private AccessCard checkUserHasAccessCard(long userId) throws Exception {
        AccessCard accessCard = null;
        try {
            accessCard = accessCardClients.getAccessCard(userId);
        } catch (FeignException.NotFound e) {
            throw new Exception("L'utilisateur " + userId + " n'a pas de carte d'accès");
        }
        return accessCard;
    }

    private Car checkVoitureAvailable(long carId) throws Exception {
        Car car;
        try {
            car = carClients.getCar(carId);
        } catch (FeignException.NotFound e) {
            throw new Exception("Voiture " + carId + " introuvable");
        }
        if (car.isUsed()) {
            throw new Exception("Voiture " + carId + " déjà louée");
        }
        return car;
    }

    private boolean hasActiveLocationForUser(long userId){
        Iterable<Location> locations = locationClients.getActiveLocationByUser(userId);
        for (Location location : locations){
            if (location.isActive()){
                return true;
            }
        }
        return false;
    }

    private boolean hasActiveLocationForCar(long carId){
        Iterable<Location> locations = locationClients.getActiveLocationByVoiture(carId);
        for (Location location : locations){
            if (location.isActive()){
                return true;
            }
        }
        return false;
    }
}
