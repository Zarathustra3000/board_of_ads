package com.board_of_ads.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InstagramTokenDto {
    private String access_token;
    private String user_id;
}