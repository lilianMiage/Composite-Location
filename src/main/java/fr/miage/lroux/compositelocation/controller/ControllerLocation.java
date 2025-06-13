package fr.miage.lroux.compositelocation.controller;

import fr.miage.lroux.compositelocation.dto.*;
import fr.miage.lroux.compositelocation.repository.RepoLocation;
import fr.miage.lroux.compositelocation.service.ServiceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Clocation")
public class ControllerLocation {

    @Autowired
    RepoLocation repoLocation;

    @PostMapping("/create/{userId}")
    public UserWithCar createLocation(@PathVariable long userId, @RequestBody Car carId) throws Exception {
        return repoLocation.createLocation(userId, carId);
    }

    @PostMapping("/return")
    public Object returnCar(@RequestBody ReturnCarRequest request) throws Exception {
        return repoLocation.returnCar(request);
    }

    @GetMapping("/proches/{carId}")
    public List<StationWithTime> getClosestStations(@PathVariable Long carId) throws Exception {
        return repoLocation.getThreeClosestStations(carId);
    }

}
