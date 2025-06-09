package fr.miage.lroux.compositelocation.service;

import feign.FeignException;
import fr.miage.lroux.compositelocation.clients.AccessCardClients;
import fr.miage.lroux.compositelocation.clients.CarClients;
import fr.miage.lroux.compositelocation.clients.LocationClients;
import fr.miage.lroux.compositelocation.clients.UserClients;
import fr.miage.lroux.compositelocation.dto.Car;
import fr.miage.lroux.compositelocation.dto.Location;
import fr.miage.lroux.compositelocation.dto.User;
import fr.miage.lroux.compositelocation.dto.UserWithCar;
import fr.miage.lroux.compositelocation.repository.RepoLocation;
import org.springframework.stereotype.Component;

@Component
public class ServiceLocation implements RepoLocation {

    private final AccessCardClients accessCardClients;

    private final LocationClients locationClients;

    private final CarClients carClients;

    private final UserClients userClients;

    public ServiceLocation(AccessCardClients accessCardClients, LocationClients locationClients, CarClients carClients, UserClients userClients) {
        this.accessCardClients = accessCardClients;
        this.locationClients = locationClients;
        this.carClients = carClients;
        this.userClients = userClients;
    }

    public UserWithCar createLocation(long userId, Car car) throws Exception {

        // Check User
        User user = checkUserExists(userId);

        // Check AccessCard
        checkUserHasAccessCard(userId);

        // Check voiture disponible
        checkVoitureAvailable(car.getCarId());

        if(hasActiveLocationForUser(userId) || hasActiveLocationForCar(car.getCarId())){
            throw new Exception("The car or the user are alrealdy active");
        }
        Location location = new Location();

        // Création de la location
        location.setActive(true);
        location.setCarId(car.getCarId());
        location.setAccessCardId(user.getAccessCardId());
        location.setStationId(car.getStationId());
        location.setUserId(userId);
        locationClients.createLocation(location);
        return new UserWithCar(user,car);
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

    private void checkUserHasAccessCard(long userId) throws Exception {
        try {
            accessCardClients.getAccessCard(userId);
        } catch (FeignException.NotFound e) {
            throw new Exception("L'utilisateur " + userId + " n'a pas de carte d'accès");
        }
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
