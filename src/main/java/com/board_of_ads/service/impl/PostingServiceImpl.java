package com.board_of_ads.service.impl;

import com.board_of_ads.model.posting.Posting;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.service.interfaces.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private PostingRepository postingRepository;
    @Override
    public void savePosting(Posting posting) {
        postingRepository.save(posting);
    }
}
