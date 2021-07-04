package com.gfg.jbdl12majorproject.userservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.jbdl12majorproject.userservice.entities.PaymentUser;
import com.gfg.jbdl12majorproject.userservice.manager.UserManager;
import com.gfg.jbdl12majorproject.userservice.model.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserManager userManager;

    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequest signupRequest) throws JsonProcessingException {
        userManager.create(signupRequest);
    }

    @GetMapping("/user/{id}")
    public PaymentUser getUser(@PathVariable("id") String id){
        PaymentUser user  = userManager.getUser(id);

        return user;
    }


}
