package com.board_of_ads.controllers.rest;


import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthorizationService authorizationService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> authorization(@RequestBody User userAuth) {

        if (authorizationService.isValid(userAuth)) {
            userAuth = userService.getUserByEmail(userAuth.getEmail());
            authorizationService.login(userAuth);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
