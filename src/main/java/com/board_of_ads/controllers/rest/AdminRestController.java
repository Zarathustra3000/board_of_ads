package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @PostMapping("/newUser")
    public Response<User> createNewUser(@RequestBody User user) {
            return new SuccessResponse<>(userService.saveUser(user));
    }

    @PutMapping("/newUserData")
    public Response<User> updateUser(@RequestBody User user) {
        return new SuccessResponse<>(userService.saveUser(user));
    }

    @GetMapping("/allUsers")
    public Response<List<User>> getAllUsersList() {
        return new SuccessResponse<>(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public Response<User> getUserById(@PathVariable(name = "id") Long id) {
        return new SuccessResponse<>(userService.getUserById(id));
    }

    @DeleteMapping("/user/{id}")
    public Response<Void> deleteUserById(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new Response<>();
    }

}
