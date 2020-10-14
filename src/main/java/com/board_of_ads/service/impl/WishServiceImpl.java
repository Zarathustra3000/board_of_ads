package com.board_of_ads.service.impl;

import com.board_of_ads.models.Wish;
import com.board_of_ads.repository.WishRepository;
import com.board_of_ads.service.interfaces.WishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    @Override
    public void save(Wish wish) {
        wishRepository.save(wish);
    }
}
