package fr.miage.lroux.compositelocation.dto;

import java.util.List;

public class StationWithTime extends Station{
    private Station station;

    private int time;

    private double distance;

    public StationWithTime(Station station, int time,double distance) {
        super(station.getStationId(), station.getPosition(), station.getNbPlaces(), station.getNbPlacesTaken(), station.getNbPlacesFree(), station.getLocalisation());
        this.time = time;
        this.distance = distance;
    }

    public StationWithTime(long stationId, String position, int nbPlaces, int nbPlacesTaken, int nbPlacesFree, List<Double> localisation, Station station, int time) {
        super(stationId, position, nbPlaces, nbPlacesTaken, nbPlacesFree, localisation);
        this.station = station;
        this.time = time;
    }

    public StationWithTime(Station station, double distance) {
        super(station.getStationId(), station.getPosition(), station.getNbPlaces(), station.getNbPlacesTaken(), station.getNbPlacesFree(), station.getLocalisation());
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
