package fr.miage.lroux.compositelocation.dto;

import java.util.List;

public class ReturnCarRequest {
    private long carId;
    private long stationId;

    private long locationId;

    private double batteryLevel;
    private double distanceTravelled;
    private List<Double> localisation;

    public ReturnCarRequest(long carId, long stationId, long locationId, double batteryLevel, double distanceTravelled, List<Double> localisation) {
        this.carId = carId;
        this.stationId = stationId;
        this.locationId = locationId;
        this.batteryLevel = batteryLevel;
        this.distanceTravelled = distanceTravelled;
        this.localisation = localisation;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public List<Double> getLocalisation() {
        return localisation;
    }

    public void setLocalisation(List<Double> localisation) {
        this.localisation = localisation;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
