package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    ClassListRepository classListRepository;


    @Test
    void resetDataPost(){
        reviewRecordRepository.deleteAll();
        reviewRepository.deleteAll();

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
            jsonObject.put("post_id", postList.get(i).getId());
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

        /**
         * review_post : class_list /
         * review_lec_list : 강의 / class
         // user_number 넘겨줘야됨ㅇ아
         */

        Optional<User> user = userRepository.findById("20190987");
        Optional<User> user2 = userRepository.findById("20196544");
        Optional<User> user3 = userRepository.findById("20192345");
        Optional<User> user4 = userRepository.findById("20194324");
        Optional<User> user5 = userRepository.findById("20191111");


        User detailUser = User.builder()
                .user_number(user.get().getUser_number())
                .user_name(user.get().getUser_name())
                .department(user.get().getDepartment())
                .password(user.get().getPassword())
                .nickname(user.get().getNickname())
                .build();

        User detailUser2 = User.builder()
                .user_number(user2.get().getUser_number())
                .user_name(user2.get().getUser_name())
                .department(user2.get().getDepartment())
                .password(user2.get().getPassword())
                .nickname(user.get().getNickname())
                .build();

        User detailUser3 = User.builder()
                .user_number(user3.get().getUser_number())
                .user_name(user3.get().getUser_name())
                .department(user3.get().getDepartment())
                .password(user3.get().getPassword())
                .nickname(user.get().getNickname())
                .build();

        User detailUser4 = User.builder()
                .user_number(user4.get().getUser_number())
                .user_name(user4.get().getUser_name())
                .department(user4.get().getDepartment())
                .password(user4.get().getPassword())
                .nickname(user.get().getNickname())
                .build();

        User detailUser5 = User.builder()
                .user_number(user5.get().getUser_number())
                .user_name(user5.get().getUser_name())
                .department(user5.get().getDepartment())
                .password(user5.get().getPassword())
                .nickname(user.get().getNickname())
                .build();


        List<Lec> lec = lecRepository.findAll();



        // 클라이언트가 줘야할 데이터 : user_number(User 객체) , post_title, post_content , lec_id
        // reivew_post

//
//        Review_post reviewPost = Review_post.builder()
//                .post_title("한편의 영화같은 강의")
//                .post_content("교수님 감사합니다 덕분에 엄청난 강의를 들었어요")
//                .user_user_number(detailUser)
//                .star_lating(4L)
//                .likes(0)
//                .lec_lec_name(lec.get(0))
//                .build();
//
//        Review_post reviewPost2 = Review_post.builder()
//                .post_title("한시의 주옥같은 강의")
//                .post_content("교수님 감사합니다 이 강의는 두고두고 봐야하는 강의입니다.")
//                .user_user_number(detailUser2)
//                .star_lating(4L)
//                .likes(0)
//                .lec_lec_name(lec.get(0))
//                .build();
//
//        Review_post reviewPost3 = Review_post.builder()
//                .post_title("잠은 죽어서 잘수 있는 강의")
//                .post_content("교수님 덕분에 수명이 15년 줄었습니다.")
//                .user_user_number(detailUser3)
//                .star_lating(4L)
//                .likes(0)
//                .lec_lec_name(lec.get(0))
//                .build();
//
//
//        Review_post reviewPost4 = Review_post.builder()
//                .post_title("교수님의 강의는 정말 쉽게 가르쳐줘요")
//                .post_content("교수님 감사합니다 절대 잊을 수 없는 감명깊은 강의였습니다.")
//                .user_user_number(detailUser4)
//                .star_lating(4L)
//                .likes(0)
//                .lec_lec_name(lec.get(0))
//                .build();
//
//
//        Review_post reviewPost5 = Review_post.builder()
//                .post_title("엄청난 강의입니다.")
//                .post_content("교수님 감사합니다 이 강의 들을려고 동서대 다닌다고 생각합니다.")
//                .user_user_number(detailUser5)
//                .star_lating(4L)
//                .likes(0)
//                .lec_lec_name(lec.get(0))
//                .build();
//

        Review_post reviewPost6 = Review_post.builder()
                .post_title("태풍같은 강의")
                .post_content("2학기의 해가 떴습니다. 눈 깜짝할사이 제 손에는 워크벤치만 남고, 2학기가 끝났습니다.")
                .user_user_number(detailUser)
                .star_lating(4L)
                .likes(0)
                .lec_lec_name(lec.get(0))
                .build();


        Review_post reviewPost7 = Review_post.builder()
                .post_title("데이터베이스가 싫어졌어요")
                .post_content("교수님 감사합니다")
                .user_user_number(detailUser2)
                .star_lating(1L)
                .likes(0)
                .lec_lec_name(lec.get(0))
                .build();


        Review_post reviewPost8 = Review_post.builder()
                .post_title("하하하하 수강 나이스~")
                .post_content("교수님 감사합니다 학점 개꿀이에요")
                .user_user_number(detailUser3)
                .star_lating(2L)
                .likes(0)
                .lec_lec_name(lec.get(0))
                .build();


        Review_post reviewPost9 = Review_post.builder()
                .post_title("강의 평가 잘해드릴게요")
                .post_content("교수님 감사합니다")
                .user_user_number(detailUser4)
                .star_lating(3L)
                .likes(0)
                .lec_lec_name(lec.get(0))
                .build();


        Review_post reviewPost10 = Review_post.builder()
                .post_title("엄청난 강의를 제가 들어도 됬나모르겠네요")
                .post_content("동서대 다닐려면 이것만 들어야되요")
                .user_user_number(detailUser5)
                .star_lating(1L)
                .likes(0)
                .lec_lec_name(lec.get(0))
                .build();



//        Optional<Class_list> classList = classListRepository.findById(reviewPost.getLec_lec_name().getLec_id());
//        Optional<Class_list> classList2 = classListRepository.findById(reviewPost2.getLec_lec_name().getLec_id());
//        Optional<Class_list> classList3 = classListRepository.findById(reviewPost3.getLec_lec_name().getLec_id());
//        Optional<Class_list> classList4 = classListRepository.findById(reviewPost4.getLec_lec_name().getLec_id());
//        Optional<Class_list> classList5 = classListRepository.findById(reviewPost5.getLec_lec_name().getLec_id());
        Optional<Class_list> classList6 = classListRepository.findById(reviewPost6.getLec_lec_name().getLec_id());
        Optional<Class_list> classList7 = classListRepository.findById(reviewPost7.getLec_lec_name().getLec_id());
        Optional<Class_list> classList8 = classListRepository.findById(reviewPost8.getLec_lec_name().getLec_id());
        Optional<Class_list> classList9 = classListRepository.findById(reviewPost9.getLec_lec_name().getLec_id());
        Optional<Class_list> classList10 = classListRepository.findById(reviewPost10.getLec_lec_name().getLec_id());

//
//        Review_lec_list reviewRecord = Review_lec_list.builder()
//                .number(classList.get())
//                .post_id(reviewPost)
//                .build();
//
//
//        Review_lec_list reviewRecord2 = Review_lec_list.builder()
//                .number(classList2.get())
//                .post_id(reviewPost)
//                .build();
//
//
//        Review_lec_list reviewRecord3 = Review_lec_list.builder()
//                .number(classList3.get())
//                .post_id(reviewPost)
//                .build();
//
//
//        Review_lec_list reviewRecord4 = Review_lec_list.builder()
//                .number(classList4.get())
//                .post_id(reviewPost)
//                .build();
//
//
//
//        Review_lec_list reviewRecord5 = Review_lec_list.builder()
//                .number(classList5.get())
//                .post_id(reviewPost)
//                .build();
//

        Review_lec_list reviewRecord6 = Review_lec_list.builder()
                .number(classList6.get())
                .post_id(reviewPost6)
                .build();

        Review_lec_list reviewRecord7 = Review_lec_list.builder()
                .number(classList7.get())
                .post_id(reviewPost7)
                .build();

        Review_lec_list reviewRecord8 = Review_lec_list.builder()
                .number(classList8.get())
                .post_id(reviewPost8)
                .build();

        Review_lec_list reviewRecord9 = Review_lec_list.builder()
                .number(classList9.get())
                .post_id(reviewPost9)
                .build();

        Review_lec_list reviewRecord10 = Review_lec_list.builder()
                .number(classList10.get())
                .post_id(reviewPost10)
                .build();


        // 교수가 같은 경우
        // 교수가 다른 경우



        // 게시글 추가
//        reviewRepository.save(reviewPost);
//        reviewRepository.save(reviewPost2);
//        reviewRepository.save(reviewPost3);
//        reviewRepository.save(reviewPost4);
//        reviewRepository.save(reviewPost5);
        reviewRepository.save(reviewPost6);
        reviewRepository.save(reviewPost7);
        reviewRepository.save(reviewPost8);
        reviewRepository.save(reviewPost9);
        reviewRepository.save(reviewPost10);



        // 글쓰고나서 누가 어떤 강의 글썻다는 review_lec_list에 간단히 기록 추가
        // review_lec_list
//        reviewRecordRepository.save(reviewRecord);
//        reviewRecordRepository.save(reviewRecord2);
//        reviewRecordRepository.save(reviewRecord3);
//        reviewRecordRepository.save(reviewRecord4);
//        reviewRecordRepository.save(reviewRecord5);
        reviewRecordRepository.save(reviewRecord6);
        reviewRecordRepository.save(reviewRecord7);
        reviewRecordRepository.save(reviewRecord8);
        reviewRecordRepository.save(reviewRecord9);
        reviewRecordRepository.save(reviewRecord10);

    }

    @Test
    @Transactional
    void updatePost(){

        Optional<Review_post> data = reviewRepository.findById(16L);

        String updateTitle = "변경된 제목";
        String updateContent = "변경된 내용";

        Review_post updateData = Review_post.builder()
                .id(data.get().getId())
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
