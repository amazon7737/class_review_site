package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.LecRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import com.dsu_review_api.infrastructure.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/* 수강 후기 */

@SpringBootTest
@Slf4j
public class ReviewPostTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    LecRepository lecRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    void selectPost(){

        List<Review_post> postList = reviewRepository.findAll();
        for(Integer i = 0; i<postList.size(); i++){

            log.info("postList : {}", String.valueOf(postList.get(i)));
        }


    }

    @Test
    @Transactional
    void addPost(){


        // 해당 강의 검색 (lec)
        List<Lec> lecList = lecRepository.findAll();

        // 해당 유저 검색
        Optional<User> userData = userRepository.findById("20191434");
        User userSetData = User.builder()
                .user_number(userData.get().getUser_number())
                .user_name(userData.get().getUser_name())
                .password(userData.get().getPassword())
                .department(userData.get().getDepartment())
                .build();

        for(Integer i = 0; i< lecList.size(); i++){

        // 클라이언트가 줘야할 데이터 : user_number(User 객체) , post_title, post_content , lec_id
        Review_post examplePostData = Review_post.builder()
                .post_title("수강 후기 제목"+ i)
                .post_content("수강 후기 내용"+ i)
                .user_user_number(userSetData)
                .lec_lec_name(lecList.get(i))
                .likes(0)
                .build();

        reviewRepository.save(examplePostData);

        }


        // 해당 유저 이름 ->

        // default likes == 0 으로 시작


//        Review_post post = Review_post.builder()
//                .post_title()
//                .post_content()
//                .likes()
//                .lec_lec_name()
//


    }

    @Test
    @Transactional
    void updatePost(){

    }


    @Test
    @Transactional
    void deletePost(){

    }

}
