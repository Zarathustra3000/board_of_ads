package com.board_of_ads.model.dto;

import com.board_of_ads.model.City;
import com.board_of_ads.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostingDto extends City {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String contact;
}
