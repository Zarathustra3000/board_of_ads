package com.board_of_ads.restControllers;

import com.board_of_ads.model.dto.CityDto;
import com.board_of_ads.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CityRestController {
    private final CityService cityService;

    @GetMapping("/city")
    public List<CityDto> findAll() {
        return cityService.getCitiesList();
    }
}
