package com.dsu_review_api.infrastructure.service;

import com.dsu_review_api.application.ReviewPostService;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hibernate.sql.Update;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
@Slf4j
public class ReviewPostServiceTest {

    @Autowired
    ReviewPostService reviewPostService;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    @Transactional
    void selectPostTest(){

        List<Review_post> postList = reviewPostService.selectPost();

        log.info("postList: {}", postList);

        assertNotNull(postList);

    }

    @Test
//    @Transactional
    void addPostTest(){

        JSONObject request = new JSONObject();

        request.put("post_title", "글제목테스트");
        request.put("post_content", "글제목내용");
        request.put("likes", 0);
        request.put("user_number", "20191111");
        request.put("lec_name", "데이터베이스 개론");
        request.put("star_lating", 2L);

        reviewPostService.addPost(request);

    }

    @Test
    @Transactional
    void updatePostTest(){

        addPostTest();
        List<Review_post> postList = reviewRepository.findAll();
//        postList.get(0)

        Review_post UpdatePost = Review_post.builder()
                .post_content("변경하는 내용")
                .post_title("변경하는 제목")
                .likes(postList.get(0).getLikes())
                .id(postList.get(0).getId())
                .lec_lec_name(postList.get(0).getLec_lec_name())
                .user_user_number(postList.get(0).getUser_user_number())
                .build();

        reviewRepository.save(UpdatePost);

        log.info("UpdatePost : {}", UpdatePost.getPost_title());
        log.info("result: {}", reviewRepository.findById(postList.get(0).getId()).get().getPost_title());

        Assertions.assertThat(UpdatePost.getPost_title()).isEqualTo(reviewRepository.findById(postList.get(0).getId()).get().getPost_title());
        Assertions.assertThat(UpdatePost.getPost_content()).isEqualTo(reviewRepository.findById(postList.get(0).getId()).get().getPost_content());


    }

    @Test
    @Transactional
    void deletePostTest(){

        addPostTest();
        List<Review_post> postList = reviewRepository.findAll();

        Long matchId = postList.get(0).getId();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post_id" , postList.get(0).getId());



        List<Review_post> review = reviewRepository.findAll();

//       IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> matchId.equals());
//       assertThat(e.getMessage()).isEqualTo("삭제 완료되었습니다.");


    }
}
