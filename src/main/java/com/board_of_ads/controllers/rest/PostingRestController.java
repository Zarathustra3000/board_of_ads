package com.board_of_ads.controllers.rest;

import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posting")
@AllArgsConstructor
public class PostingRestController {
    private final PostingService postingService;

    @GetMapping("/list")
    public List<Posting> findAll() {
        return postingService.findAll().get();
    }
}
