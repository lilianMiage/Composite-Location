package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("location")
public interface LocationClients {

    @RequestMapping(method = RequestMethod.POST, value = "/api/location/create", consumes = "application/json")
    Location createLocation(@RequestBody Location location);

    @GetMapping("/api/location/active/user/{idUser}")
    Iterable<Location> getActiveLocationByUser(@PathVariable("idUser") Long idUser);

    @GetMapping("/api/location/active/car/{idVoiture}")
    Iterable<Location> getActiveLocationByVoiture(@PathVariable("idVoiture") Long idVoiture);
}

