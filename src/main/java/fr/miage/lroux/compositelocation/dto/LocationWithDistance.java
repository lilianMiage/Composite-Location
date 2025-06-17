package fr.miage.lroux.compositelocation.dto;

public class LocationWithDistance extends Location {

    private double distanceTravelledLocation;

    public LocationWithDistance(Location location,double distanceTravelledLocation) {
        super(location.getIdLocation(),
              location.getCarId(),
              location.getUserId(),
              location.getAccessCardId(),
              location.getStationId(),
              location.isActive());
        this.distanceTravelledLocation = distanceTravelledLocation;
    }

    public double getDistanceTravelledLocation() {
        return distanceTravelledLocation;
    }

    public void setDistanceTravelledLocation(double distanceTravelledLocation) {
        this.distanceTravelledLocation = distanceTravelledLocation;
    }
}
