package com.board_of_ads.service.impl;

import com.board_of_ads.model.City;
import com.board_of_ads.model.Region;
import com.board_of_ads.repository.CityRepository;
import com.board_of_ads.repository.RegionRepository;
import com.board_of_ads.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Data
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;

    @Override
    public Set<City> getAllCitiesAndRegions() {
        Set<City> cities = new HashSet<>();
        cityRepository.findAll()
                .forEach(city -> {
                    String regionName = "";
                    String formSubject = "";
                    if (city.getRegion() != null) {
                        regionName = city.getRegion().getName() == null ? "" : city.getRegion().getName();
                        formSubject = city.getRegion().getFormSubject() == null ? "" : city.getRegion().getFormSubject();
                    }
                    cities.add(new City(city.getName(), new Region(), regionName + " " + formSubject));
                });
        regionRepository.findAll()
                .forEach(region -> {
                    String regionName = region.getName();
                    String formSubject = region.getFormSubject();
                    if (formSubject.equals("Республика") || formSubject.equals("Город")) {
                        cities.add(new City(formSubject + " " + regionName, new Region(), ""));
                    } else {
                        cities.add(new City(regionName + " " + formSubject, new Region(), ""));
                    }
                });
        return cities;
    }
}
