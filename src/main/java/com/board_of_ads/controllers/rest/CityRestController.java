package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.City;
import com.board_of_ads.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Set<City>> findAll() {
        return new ResponseEntity<>(cityService.getCitiesList(), HttpStatus.OK);
    }
}
