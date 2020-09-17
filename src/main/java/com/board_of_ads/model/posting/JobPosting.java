package com.board_of_ads.model.posting;

import com.board_of_ads.model.posting.category.Category;

public class JobPosting {
    private Long id;
    private Long userId;
    private Category category;
    private String title;
    private String schedule;
    private String workExperience;
    private String description;
    private Double salary;
    private Image logo;
    private String address;
    private String contact;

}
