package com.board_of_ads.model.posting;

import com.board_of_ads.model.Image;

import java.util.List;

public class Posting {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Double price;
    private List<Image> images;
    private String youtubeLink;
    private String address;
    private String contacts;
    private Boolean isActive;
}
