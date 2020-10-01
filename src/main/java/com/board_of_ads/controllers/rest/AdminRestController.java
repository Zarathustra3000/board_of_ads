package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
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
    public Response<User> createNewUser(@RequestBody User user) {
            return new SuccessResponse<>(userService.saveUser(user));
    }

    @PutMapping("/updateUser")
    public Response<User> updateUser(@RequestBody User user) {
        return new SuccessResponse<>(userService.saveUser(user));
    }

    @GetMapping("/getAllUsers")
    public Response<List<User>> getAllUsersList() {
            return new SuccessResponse<>(userService.getAllUsers());
    }

    @GetMapping("/getUserById/{id}")
    public Response<User> getUserById(@PathVariable(name = "id") Long id) {
        return new SuccessResponse<>(userService.getUserById(id));
    }

    @DeleteMapping("/deleteUser/{id}")
    public Response<Void> deleteUserById(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new Response<>();
    }

}
