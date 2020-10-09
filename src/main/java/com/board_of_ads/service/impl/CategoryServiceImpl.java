package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDto;
import com.board_of_ads.repository.CategoryRepository;
import com.board_of_ads.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return Optional.ofNullable(categoryRepository.findCategoryByName(name));
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Set<CategoryDto> findAllCategory() {
        Set<CategoryDto> categoryDto = new LinkedHashSet<>();
        List<Category> list = categoryRepository.findAll();
        list.forEach(category -> {
            if (category.getCategory() == null) {
                categoryDto.add(new CategoryDto(category.getName(), false, null, category.getLayer()));
            } else {
                categoryDto.add(new CategoryDto(category.getName(), true, category.getCategory().getName(), category.getLayer()));
            }
        });
        return categoryDto;
    }
}
