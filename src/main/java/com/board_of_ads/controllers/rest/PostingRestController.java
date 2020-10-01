package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.dto.PostingDto;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.PostingService;
import com.board_of_ads.util.Error;
import com.board_of_ads.util.ErrorResponse;
import com.board_of_ads.util.Response;
import com.board_of_ads.util.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posting")
@AllArgsConstructor
public class PostingRestController {

    private final CityService cityService;
    private final PostingService postingService;

    @GetMapping
    public Response<List<PostingDto>> findAllPosts() {
        List<PostingDto> posts;
        try {
            posts = postingService.getAllPostings();
        } catch (Exception e) {
            return new ErrorResponse<>(new Error(400, "No find posts"));
        }
        return new SuccessResponse<>(posts);
    }

    @GetMapping("/city/{name}")
    public Response<List<PostingDto>> findPostingsByCityName(@PathVariable String name) {
        List<PostingDto> posts;
        try {
            posts = postingService.getPostingByCity(cityService.findCityByName(name).get());
        } catch (Exception e) {
            return new ErrorResponse<>(new Error(400, "No find posts"));
        }
        return new SuccessResponse<>(posts);
    }

    @GetMapping("/region/{name}")
    public Response<List<PostingDto>> findPostingsByRegionName(@PathVariable String name) {
        List<PostingDto> posts;
        try {
            posts = postingService.getPostingByFullRegionName(name);
        } catch (Exception e) {
            return new ErrorResponse<>(new Error(400, "No find posts"));
        }
        return new SuccessResponse<>(posts);
    }
}