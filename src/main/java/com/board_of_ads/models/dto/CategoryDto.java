package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private boolean parent;
    private String parentName;
    private int layer;
}
