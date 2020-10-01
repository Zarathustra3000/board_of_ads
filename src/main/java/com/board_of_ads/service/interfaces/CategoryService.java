package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> getCategoryByName(String name);

    void saveCategory(Category category);
}
