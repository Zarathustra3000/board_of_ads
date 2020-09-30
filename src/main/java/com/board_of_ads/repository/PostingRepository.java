package com.board_of_ads.repository;

import com.board_of_ads.models.City;
import com.board_of_ads.models.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {
    Posting findPostingByTitle(String title);
    List<Posting> findPostingByCity(City city);
}
