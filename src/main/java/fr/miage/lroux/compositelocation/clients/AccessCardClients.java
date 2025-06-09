package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.AccessCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("accesscard")
public interface AccessCardClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/accessCard/user/{userId}", produces = "application/json")
    AccessCard getAccessCard(@PathVariable long userId);
}
