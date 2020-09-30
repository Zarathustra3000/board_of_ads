package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.dto.PostingDto;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/posting")
@AllArgsConstructor
public class PostingRestController {

    private final CityService cityService;
    private final PostingService postingService;

    @GetMapping
    public ResponseEntity<List<PostingDto>> findAllPosts() {
        return new ResponseEntity<>(postingService.getAllPostings(), HttpStatus.OK);
    }

    @GetMapping("/city/{name}")
    public ResponseEntity<List<PostingDto>> findPostingsByCityName(@PathVariable String name) {
        return new ResponseEntity<>(postingService.getPostingByCity(cityService.findCityByName(name).get()), HttpStatus.OK);
    }

    @GetMapping("/region/{name}")
    public ResponseEntity<List<PostingDto>> findPostingsByRegionName(@PathVariable String name) {
        return new ResponseEntity<>(postingService.getPostingByFullRegionName(name), HttpStatus.OK);
    }
}