package com.board_of_ads.repository;

import com.board_of_ads.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    Region findRegionByRegionNumber(String regionNumber);

    boolean existsRegionByName(String name);
}