package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.City;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.util.Error;
import com.board_of_ads.util.ErrorResponse;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CityRestController {
    private final CityService cityService;

    @GetMapping()
    public Response<Set<City>> findAll() {
        Set<City> cities;
        try {
            cities = cityService.getCitiesList();
        } catch (Exception e) {
            return new ErrorResponse<>(new Error(400, "No find users"));
        }
        return new SuccessResponse<>(cities);
    }
}
