package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.Station;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("station")
public interface StationClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/station/stations",produces = "application/json")
    List<Station> getStations();

    @RequestMapping(method = RequestMethod.GET, value = "/api/station/{stationId}",produces = "application/json")
    Station getStation(@PathVariable long stationId);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/station/retrieve/{stationId}",produces = "application/json")
    Station updateAfterRetrievingCar(@PathVariable long stationId);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/station/add/{stationId}", produces = "application/json")
    Station updateStationAddCar(@PathVariable Long stationId);
}
