package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @PostMapping("/addNewUser")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsersList() {
            List<User> userList = userService.getAllUsers();
            return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
