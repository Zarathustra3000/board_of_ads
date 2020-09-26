package com.board_of_ads.service.interfaces;

import com.board_of_ads.model.posting.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingService {
    void save(Posting posting);
    Optional<Posting> getPostingByTitle(String title);
    List<Posting> getTestPostingList();
}
