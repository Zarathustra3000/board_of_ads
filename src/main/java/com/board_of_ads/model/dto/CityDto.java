package com.board_of_ads.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityDto {
    private String city;
    private String region;
    private String formSubject;
}
