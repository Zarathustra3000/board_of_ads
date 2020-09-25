package com.board_of_ads.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InstagramUserDto {
    private String id;
    private String username;
}