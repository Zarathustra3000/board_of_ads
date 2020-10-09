package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.dto.CategoryDto;
import com.board_of_ads.repository.CategoryRepository;
import com.board_of_ads.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
//        Set<CategoryDto> categoryDto = new LinkedHashSet<>();
//        Map<String, String> maps = new LinkedHashMap<>();
//        var categories = categoryRepository.findAll();
//        categories.forEach(cat -> {
//            if (cat.getCategory() == null) {
//                maps.put(cat.getName(), null);
//            } else {
//                maps.merge(cat.getCategory().getName(), cat.getName(), (o, n) -> o + "/" +n);
//            }
//        });
//        maps.forEach((key, value) -> {
//            categoryDto.add(new CategoryDto(key, true, value));
//            if (value != null) {
//                Arrays.stream(value.split("/"))
//                        .forEach(str -> categoryDto.add(new CategoryDto(str, false, key)));
//            }
//        });
//        return categoryDto;
        return null;
    }

    public Set<CategoryDto> findAllCategory2() {
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
