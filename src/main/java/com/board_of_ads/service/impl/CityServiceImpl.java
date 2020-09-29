package com.board_of_ads.service.impl;

import com.board_of_ads.models.dto.CityDto;
import com.board_of_ads.repository.CityRepository;
import com.board_of_ads.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Override
    public List<CityDto> getCitiesList() {
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
