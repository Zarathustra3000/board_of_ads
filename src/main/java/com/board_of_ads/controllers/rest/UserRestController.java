package com.board_of_ads.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    @GetMapping
    public ResponseEntity<Principal> getUser(Principal user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
