package com.board_of_ads.controllers.simple;

import com.board_of_ads.model.dto.CityDto;
import com.board_of_ads.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {
    private final CityRepository cityRepository;

    @GetMapping("/city")
    public List<CityDto> findAll() {
        List<CityDto> cities = new ArrayList<>();
        cityRepository.findAll()
                .forEach(city -> {
                    String regionName = "";
                    String formSubject = "";
                    String cityName = city.getName();
                    if (city.getRegion() != null) {
                        regionName = city.getRegion().getName() == null ? "" : city.getRegion().getName();
                        formSubject = city.getRegion().getFormSubject() == null ? "" : city.getRegion().getFormSubject();
                    }
                    cities.add(new CityDto(cityName, regionName, formSubject));
                });
        return cities;
    }
}
