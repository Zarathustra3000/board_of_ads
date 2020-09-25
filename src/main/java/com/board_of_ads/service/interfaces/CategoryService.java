package com.board_of_ads.service.interfaces;

import com.board_of_ads.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Optional<Category> getCategoryByName(String name);
    List<Category> getListCategories();
    List<Category> getListSubCategories();
    void saveCategory(Category category);
}
