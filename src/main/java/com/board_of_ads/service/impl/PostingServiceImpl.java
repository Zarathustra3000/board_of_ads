package com.board_of_ads.service.impl;

import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    @Transactional
    @Override
    public void save(Posting posting) {
        postingRepository.save(posting);
    }

    @Override
    public Optional<Posting> getPostingByTitle(String title) {
        return Optional.ofNullable(postingRepository.findPostingByTitle(title));
    }
}
