package fr.miage.lroux.compositelocation.repository;

import fr.miage.lroux.compositelocation.dto.*;

import java.util.List;

public interface RepoLocation {

    UserWithCar createLocation(long userId, Car carId) throws Exception;

    Object returnCar(ReturnCarRequest request) throws Exception;

    List<StationWithTime> getThreeClosestStations(Long carId) throws Exception;

    void technicien(double seuil);
}
