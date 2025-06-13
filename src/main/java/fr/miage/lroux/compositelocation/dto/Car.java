package fr.miage.lroux.compositelocation.dto;

import java.util.List;

public class Car {

    private long carId;

    private String brand;

    private String model;

    private double batteryLevel; // niveau de batterie

    private double kilometresTravelled; // km parcourus

    private int numberOfSeats; // nombre de places

    private boolean used; // savoir s'il est dispo ou non

    private List<Double> localisation; // localisation GPS

    private long stationId;

    public Car(long carId, String brand, String model, double batteryLevel, double kilometresTravelled, int numberOfSeats, boolean used, List<Double> localisation, long stationId) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.kilometresTravelled = kilometresTravelled;
        this.numberOfSeats = numberOfSeats;
        this.used = used;
        this.localisation = localisation;
        this.stationId = stationId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getKilometresTravelled() {
        return kilometresTravelled;
    }

    public void setKilometresTravelled(double kilometresTravelled) {
        this.kilometresTravelled = kilometresTravelled;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setUsed(boolean used) {
        this.used = used;
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

    public boolean isUsed() {
        return used;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }
}
