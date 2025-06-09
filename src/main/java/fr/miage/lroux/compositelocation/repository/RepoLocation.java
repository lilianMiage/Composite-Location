package fr.miage.lroux.compositelocation.repository;

import fr.miage.lroux.compositelocation.dto.Car;
import fr.miage.lroux.compositelocation.dto.Location;
import fr.miage.lroux.compositelocation.dto.UserWithCar;

public interface RepoLocation {

    UserWithCar createLocation(long userId, Car car) throws Exception;
}
