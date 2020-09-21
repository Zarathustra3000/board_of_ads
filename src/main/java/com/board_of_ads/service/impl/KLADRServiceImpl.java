package com.board_of_ads.service.impl;

import com.board_of_ads.model.City;
import com.board_of_ads.model.Region;
import com.board_of_ads.repository.CityRepository;
import com.board_of_ads.repository.RegionRepository;
import com.board_of_ads.service.KLADRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KLADRServiceImpl implements KLADRService {

    CityRepository cityRepository;
    RegionRepository regionRepository;

    @Autowired
    public KLADRServiceImpl(CityRepository cityRepository, RegionRepository regionRepository) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public Region getRegionByRegionNumber(String regionNumber) {
        return regionRepository.findRegionByRegionNumber(regionNumber);
    }

    @Override
    public void saveRegion(Region region) {
        regionRepository.save(region);
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public Set<City> getAllCityOfRegion(Region region) {
        return cityRepository.findCitiesByRegion(region);
    }

    @Override
    public boolean existsCityByCityNameAndRegion(String cityName, Region region) {
        return cityRepository.existsCityByNameAndRegion(cityName, region);
    }

    @Override
    public boolean existsRegionByName(String regionName) {
        return regionRepository.existsRegionByName(regionName);
    }

}

