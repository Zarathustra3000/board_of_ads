package com.board_of_ads.repository;

import com.board_of_ads.model.City;
import com.board_of_ads.model.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CityRepository extends CrudRepository<City, Long> {

    Set<City> findCitiesByRegion(Region region);

    boolean existsCityByNameAndRegion(String cityName, Region region);

}
