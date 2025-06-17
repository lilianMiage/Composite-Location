package fr.miage.lroux.compositelocation.dto;

public class StationOccupation {
    private long stationId;
    private Double occupationRate;

    public StationOccupation() {
    }
    public StationOccupation(long stationId, Double occupationRate) {
        this.stationId = stationId;
        this.occupationRate = occupationRate;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public Double getOccupationRate() {
        return occupationRate;
    }

    public void setOccupationRate(Double occupationRate) {
        this.occupationRate = occupationRate;
    }
}
