package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("car")
public interface CarClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/car/{id}",produces = "application/json")
    Car getCar(@PathVariable long id);
}
