package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.City;
import com.board_of_ads.models.posting.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingService {
    void save(Posting posting);
    Posting getPostingById(Long id);
    Optional<Posting> getPostingByTitle(String title);
    List<Posting> getPostingByCity(City city);
    List<Posting> getPostingByFullRegionName(String name);
    List<Posting> getAllPostings();
}
