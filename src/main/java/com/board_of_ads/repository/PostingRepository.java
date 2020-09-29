package com.board_of_ads.repository;

import com.board_of_ads.model.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {
    Posting findPostingByTitle(String title);
}
