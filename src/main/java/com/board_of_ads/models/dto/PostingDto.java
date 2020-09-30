package com.board_of_ads.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostingDto  {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String contact;
}
