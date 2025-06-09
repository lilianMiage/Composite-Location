package fr.miage.lroux.compositelocation.clients;

import fr.miage.lroux.compositelocation.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("utilisateur")
public interface UserClients {

    @RequestMapping(method = RequestMethod.GET, value = "/api/user/{id}",produces = "application/json")
    User getUser(@PathVariable long id);
}
