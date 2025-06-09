package fr.miage.lroux.compositelocation.controller;

import fr.miage.lroux.compositelocation.dto.Car;
import fr.miage.lroux.compositelocation.dto.Location;
import fr.miage.lroux.compositelocation.dto.UserWithCar;
import fr.miage.lroux.compositelocation.repository.RepoLocation;
import fr.miage.lroux.compositelocation.service.ServiceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Clocation")
public class ControllerLocation {

    @Autowired
    RepoLocation repoLocation;

    @PostMapping("/create/{userId}")
    public UserWithCar createLocation(@PathVariable long userId, @RequestBody Car car) throws Exception {
        return repoLocation.createLocation(userId, car);
    }

}
