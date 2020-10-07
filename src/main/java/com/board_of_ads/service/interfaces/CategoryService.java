package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDto;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Optional<Category> getCategoryByName(String name);

    void saveCategory(Category category);

    Set<CategoryDto> findAllCategory();

    Optional<CategoryDto> getCategoryDtoById(Long id);
}
