package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.posting.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingService {
    void save(Posting posting);
    Optional<Posting> getPostingByTitle(String title);

    Optional<List<Posting>> findAll();
}
