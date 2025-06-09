package fr.miage.lroux.compositelocation.dto;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Location {

    private long idLocation;

    private long carId;

    private long userId;

    private long accessCardId;

    private long stationId;

    private boolean active;


    public Location() {}

    public Location(long idLocation, long carId, long userId, long accessCardId, long stationId,boolean active) {
        this.idLocation = idLocation;
        this.carId = carId;
        this.userId = userId;
        this.accessCardId = accessCardId;
        this.stationId = stationId;
        this.active = active;
    }


    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public long getAccessCardId() {
        return accessCardId;
    }

    public long getStationId() {
        return stationId;
    }

    public boolean isActive() {
        return active;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    public void setAccessCardId(long accessCardId) {
        this.accessCardId = accessCardId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
