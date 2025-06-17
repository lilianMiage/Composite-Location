package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("car")
public interface CarClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/car/{id}",produces = "application/json")
    Car getCar(@PathVariable long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/car/{id}/use",produces = "application/json")
    Car putCar(@PathVariable long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/car/{id}/return",produces = "application/json")
    Car updateCar(@PathVariable long id, @RequestBody Car car);

    @RequestMapping(method = RequestMethod.GET, value = "/api/car/cars/{stationId}", produces = "application/json")
    List<Car> getCarByStations(@PathVariable long stationId);
}
