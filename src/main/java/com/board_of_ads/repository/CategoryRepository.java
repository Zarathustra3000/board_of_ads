package com.board_of_ads.repository;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String categoryName);

    @Query("select new com.board_of_ads.models.dto.CategoryDto(c.id, c.name, CASE WHEN c.category is null THEN '' ELSE c.category.name END) from Category c where c.id = :id")
    CategoryDto findCategoryDtoById(Long id);
}
