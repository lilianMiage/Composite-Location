package fr.miage.lroux.compositelocation.dto;

public class Car {

    private long carId;

    private boolean used;

    private long stationId;

    public Car(long carId, boolean used, long stationId) {
        this.carId = carId;
        this.used = used;
        this.stationId = stationId;
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
}
