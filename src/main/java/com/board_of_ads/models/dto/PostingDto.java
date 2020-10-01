package com.board_of_ads.models.dto;

import com.board_of_ads.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostingDto {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String contact;
    private LocalDateTime datePosting;
//    private List<Image> images;
}
