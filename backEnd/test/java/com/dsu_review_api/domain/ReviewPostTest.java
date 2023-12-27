package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.LecRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRecordRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import com.dsu_review_api.infrastructure.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

    @Autowired
    ReviewRecordRepository reviewRecordRepository;

    @Test
    void resetDataPost(){
        reviewRepository.deleteAll();
        reviewRecordRepository.deleteAll();

    }


    @Test
    @Transactional
    void selectPost(){

        List<Review_post> postList = reviewRepository.findAll();
//
        JSONArray resultArray = new JSONArray();

        for(Integer i = 0; i<postList.size(); i++){
//            log.info("postList : {}", String.valueOf(postList.get(i).getPost_id()));
//            log.info("postList : {}", String.valueOf(postList.get(i).getPost_content()));
//            log.info("postList : {}", String.valueOf(postList.get(i).getPost_title()));
//            log.info("postList : {}", String.valueOf(postList.get(i).getUser_user_number()));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("post_id", postList.get(i).getPost_id());
            jsonObject.put("post_title", postList.get(i).getPost_title());
            jsonObject.put("post_content", postList.get(i).getPost_content());
            jsonObject.put("user_number", postList.get(i).getUser_user_number().getUser_number());

            resultArray.add(jsonObject);

            log.info("jsonObject: {}", jsonObject);
        }




        log.info("jsonArray: {}", resultArray);


    }


    @Test
//    @Transactional
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
                    .star_lating(4L)
                    .build();

            reviewRepository.save(examplePostData);

        // 글쓰고나서 누가 어떤 강의 글썻다는 review_lec_list에 간단히 기록 추가
            Review_lec_list recordAdd = Review_lec_list.builder()
                    .lec_lec_id(lecList.get(i))
                    .user_user_number(userSetData)
                    .build();

            reviewRecordRepository.save(recordAdd);
        }


    }

    @Test
    @Transactional
    void updatePost(){

        Optional<Review_post> data = reviewRepository.findById(16L);

        String updateTitle = "변경된 제목";
        String updateContent = "변경된 내용";

        Review_post updateData = Review_post.builder()
                .post_id(data.get().getPost_id())
                .post_title(updateTitle)
                .post_content(updateContent)
                .likes(data.get().getLikes())
                .lec_lec_name(data.get().getLec_lec_name())
                .user_user_number(data.get().getUser_user_number())
                .build();


        reviewRepository.save(updateData);


    }


    @Test
    @Transactional
    void deletePost(){

        reviewRepository.deleteById(16L);
        reviewRecordRepository.deleteById(16L);


    }

}
