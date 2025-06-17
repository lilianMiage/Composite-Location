package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("statistique")
public interface StatistiqueClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/statistique/station/mostused/{seuil}", produces = "application/json")
    List<StationOccupation> getStationsSurchargees(@PathVariable("seuil") double seuil);

    @RequestMapping(method = RequestMethod.GET, value = "/api/statistique/station/lessused/{seuil}", produces = "application/json")
    List<StationOccupation> getStationsMoinsOccupees(@PathVariable("seuil") double seuil);

    @RequestMapping(method = RequestMethod.POST, value = "/api/snapshots/car/", produces = "application/json")
    Car postCarHistorisation(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST, value = "/api/snapshots/station/", produces = "application/json")
    Station postStationHistorisation(@RequestBody Station station);

    @RequestMapping(method = RequestMethod.POST, value = "/api/snapshots/location/", produces = "application/json")
    Location postLocationHistorisation(@RequestBody LocationWithDistance location);

}
