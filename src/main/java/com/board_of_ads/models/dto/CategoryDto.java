package com.board_of_ads.models.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private boolean isParent;
    private String parentName;

    public CategoryDto(String name, boolean parent) {
        this.name = name;
        this.isParent = parent;
    }

    public CategoryDto(Long id, String name, String parentName) {
        this.id = id;
        this.name = name;
        this.parentName = parentName;
    }
}
