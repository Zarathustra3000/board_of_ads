package com.board_of_ads.controllers.rest;

import com.board_of_ads.model.dto.PostingDto;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostingRestController {

    private final CityService cityService;
    private final PostingService postingService;

    @GetMapping("/posting")
    public List<PostingDto> findAllPosts() {
        return postingService
                .getAllPostings()
                .stream()
                .map(post -> new PostingDto(post.getId(), post.getTitle(), post.getDescription(), post.getPrice(), post.getContact()))
                .collect(Collectors.toList());
    }

    @GetMapping("/posting/city/{name}")
    public List<PostingDto> findPostingsByCityName(@PathVariable String name) {
        System.out.println(name);
        var city = cityService.findCityByName(name).get();
        return postingService
                .getPostingByCity(city)
                .stream()
                .map(post -> new PostingDto(post.getId(), post.getTitle(), post.getDescription(), post.getPrice(), post.getContact()))
                .collect(Collectors.toList());
    }

    @GetMapping("/posting/region/{name}")
    public List<PostingDto> findPostingsByRegionName(@PathVariable String name) {
        System.out.println(name);
        var postings = postingService.getPostingByFullRegionName(name);
        return postings.stream()
                .map(post -> new PostingDto(post.getId(), post.getTitle(), post.getDescription(), post.getPrice(), post.getContact()))
                .collect(Collectors.toList());
    }
}
