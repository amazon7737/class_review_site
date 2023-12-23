package com.dsu_review_api.application;

import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewRepository reviewRepository;


    @Transactional
    public Page<Review_post> selectPost(Pageable pageable) {

//        for (Integer i = 0; i < postList.size(); i++) {
//            log.info("postList : {}", String.valueOf(postList.get(i)));
//        }
        return reviewRepository.findAll(pageable);


    }

}
