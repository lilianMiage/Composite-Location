package fr.miage.lroux.compositelocation.dto;

import java.util.List;

public class Station {

    private long stationId;


    private String position;


    private int nbPlaces;


    private int nbPlacesTaken;


    private int nbPlacesFree;

    private List<Double> localisation;

    public Station() {
    }

    public Station(long stationId, String position, int nbPlaces, int nbPlacesTaken, int nbPlacesFree, List<Double> localisation) {
        this.stationId = stationId;
        this.position = position;
        this.nbPlaces = nbPlaces;
        this.nbPlacesTaken = nbPlacesTaken;
        this.nbPlacesFree = nbPlacesFree;
        this.localisation = localisation;
    }

    public List<Double> getLocalisation() {
        return localisation;
    }

    public void setLocalisation(List<Double> localisation) {
        this.localisation = localisation;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbPlacesTaken() {
        return nbPlacesTaken;
    }

    public void setNbPlacesTaken(int nbPlacesTaken) {
        this.nbPlacesTaken = nbPlacesTaken;
    }

    public int getNbPlacesFree() {
        return nbPlacesFree;
    }

    public void setNbPlacesFree(int nbPlacesFree) {
        this.nbPlacesFree = nbPlacesFree;
    }
}
