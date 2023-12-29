package com.dsu_review_api.application;

import com.dsu_review_api.domain.*;
import com.dsu_review_api.infrastructure.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final LecService lecService;

    private final ReviewLecService reviewLecService;

    private  final ClassListService classListService;

    @Transactional
    public List<Review_post> selectPost() {

//        for (Integer i = 0; i < postList.size(); i++) {
//            log.info("postList : {}", String.valueOf(postList.get(i)));
//        }

        List<Review_post> postList = reviewRepository.findAll();


        return postList;
    }

    @Transactional
    public void addPost(JSONObject request) {

        log.info("request: {}", request);

        // 유저 엔티티 검색
        User user = userRepository.findById(String.valueOf(request.get("user_number"))).orElseThrow(() ->
                new IllegalArgumentException("회원을 찾을 수 없습니다."));

        log.info("user: {}", user.getUser_number());

        User userData = User.builder()
                .user_number(user.getUser_number())
                .user_name(user.getUser_name())
                .department(user.getDepartment())
                .password(user.getPassword())
                .build();

        // 강의 엔티티 검색


            Lec lec = lecService.findByLec_name(String.valueOf(request.get("lec_name")));
            log.info("lec: {}", lec.getLec_name() + lec.getLec_id());

            if (lec.equals(null) == true) {
                new NullPointerException("강의가 없습니다.");
            }

            Review_post reviewPost = Review_post.builder()
                    .post_title(String.valueOf(request.get("post_title")))
                    .post_content(String.valueOf(request.get("post_content")))
                    .likes(0)
                    .lec_lec_name(lec)
                    .user_user_number(userData)
                    .star_lating(Long.valueOf(String.valueOf(request.get("star_lating"))))
                    .build();

//            Class_list classList = classListRepository.findById(reviewPost.getLec_lec_name().getLec_id()).orElseThrow(() ->
//                    new RuntimeException("클래스강좌가 없습니다."));

            Class_list classList = classListService.selectClass_list(reviewPost.getLec_lec_name().getLec_id());

            if(classList.equals(null) == true){
                new RuntimeException("클래스 강좌가 없습니다.");
            }

            Review_lec_list reviewRecord = Review_lec_list.builder()
                    .number(classList)
                    .post_id(reviewPost)
                    .build();

            reviewRepository.save(reviewPost);

            reviewLecService.saveRecord(reviewRecord);

    }

    @Transactional
    public void updatePost(JSONObject updateData){
        /**
         * 체크해야할 사항 : user_id 에 대해서 검증하지 않고 업데이트를 시키는 상태 이것을 같이 담아서 보내줘야할지 ?
         */

        log.info("updateData: {}", updateData);
        Optional<Review_post> data = reviewRepository.findById(Long.valueOf(String.valueOf(updateData.get("post_id"))));

        Review_post updatePost = Review_post.builder()
                .id(data.get().getId())
                .post_title(String.valueOf(updateData.get("post_title")))
                .post_content(String.valueOf(updateData.get("post_content")))
                .likes(data.get().getLikes())
                .lec_lec_name(data.get().getLec_lec_name())
                .user_user_number(data.get().getUser_user_number())
                .build();

        reviewRepository.save(updatePost);
    }

    @Transactional
    public void deletePost(JSONObject data){

        /**
         * 수강후기 글을 삭제했을때 다시 작성하도록 해줄것인지?
         */
        Optional<Review_post> post = reviewRepository.findById(Long.valueOf(String.valueOf(data.get("post_id"))));

        Optional<Lec> lec = lecService.findById(post.get().getLec_lec_name().getLec_id());


        log.info("post: {}", lec.get().getLec_id());

//        Long deleteLecId = reviewRecordRepository.findByLecIdAndUserNumber(post.get().getPost_id(), post.get().getUser_user_number().getUser_number());
//        reviewRecordRepository.deleteById(deleteLecId);
//        reviewRepository.deleteById(Long.valueOf(String.valueOf(data.get("post_id"))));

    }

    @Transactional
    public List<Review_post> detailPost(Class_list request){


        List<Review_lec_list> record = reviewLecService.findByClassNumber(request);
         List<Review_post> post = reviewRepository.selectfromId(record.get(0).getPost_id().getLec_lec_name().getLec_id());
         log.info("post: {}", post);


//         log.info("post: {}", post.getPost_title());

         return post;
    }

}
