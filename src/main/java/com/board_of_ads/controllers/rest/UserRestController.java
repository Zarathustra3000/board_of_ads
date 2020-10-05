package com.board_of_ads.controllers.rest;

import com.board_of_ads.util.Error;
import com.board_of_ads.util.ErrorResponse;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    @GetMapping
    public Response<Principal> getUser(Principal user) {
        return user != null
                ? new SuccessResponse<>(user)
                :  new ErrorResponse<>(new Error(401, "No auth user"));
    }
}
