package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String categoryName);

}
