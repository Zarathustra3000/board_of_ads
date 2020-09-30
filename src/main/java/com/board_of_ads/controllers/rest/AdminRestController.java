package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/**")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @PostMapping("addNewUser")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
