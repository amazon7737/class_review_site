package com.dsu_review_api.application;

import com.dsu_review_api.application.dto.ReivewPostdto;
import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.domain.Review_lec_list;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.domain.User;
import com.dsu_review_api.infrastructure.persistence.LecRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRecordRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import com.dsu_review_api.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import com.dsu_review_api.application.dto.ReivewPostdto;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewRepository reviewRepository;

    private final ReviewRecordRepository reviewRecordRepository;

    private final UserRepository userRepository;

    private final LecService lecService;

    @Transactional
    public List<Review_post> selectPost() {

//        for (Integer i = 0; i < postList.size(); i++) {
//            log.info("postList : {}", String.valueOf(postList.get(i)));
//        }

        List<Review_post> postList = reviewRepository.findAll();


        return postList;
    }

    @Transactional
    public void addPost(JSONObject request){

        log.info("request: {}", request);

        // 유저 엔티티 검색
        User user = userRepository.findById(String.valueOf(request.get("user_number"))).orElseThrow(() ->
                new IllegalArgumentException("회원을 찾을 수 없습니다."));

//        User userData = User.builder()
//                .user_number(user.get().getUser_number())
//                .user_name(user.get().getUser_name())
//                .department(user.get().getDepartment())
//                .password(user.get().getPassword())
//                .build();

        // 강의 엔티티 검색
        Lec lec = lecService.findByLec_name(String.valueOf(request.get("lec_name")));

        if(lec == null){
            throw new NullPointerException("강의를 찾을 수 없습니다.");
        }

        log.info("lec: {}", lec);
//        log.info("user: {}", user.get());

        // save
        Review_post addData = Review_post.builder()
                .post_title(String.valueOf(request.get("post_title")))
                .post_content(String.valueOf(request.get("post_content")))
                .lec_lec_name(lec)
                .user_user_number(user)
                .likes(0)
                .build();

        reviewRepository.save(addData);

               Review_lec_list recordAdd = Review_lec_list.builder()
                .lec_lec_id(lec)
                .user_user_number(user)
                .build();

        reviewRecordRepository.save(recordAdd);


    }

    @Transactional
    public void updatePost(JSONObject updateData){
        /**
         * 체크해야할 사항 : user_id 에 대해서 검증하지 않고 업데이트를 시키는 상태 이것을 같이 담아서 보내줘야할지 ?
         */

        log.info("updateData: {}", updateData);
        Optional<Review_post> data = reviewRepository.findById(Long.valueOf(String.valueOf(updateData.get("post_id"))));

        Review_post updatePost = Review_post.builder()
                .post_id(data.get().getPost_id())
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
//        Optional<Lec> lec = lecService.findById(post.get().getLec_lec_name().getLec_id());

        log.info("PostData : {}", post.get().getLec_lec_name().getLec_id());

//        log.info("post: {}", lec.get().getLec_id());

        Long deleteLecId = reviewRecordRepository.findByLecIdAndUserNumber(post.get().getLec_lec_name().getLec_id(), post.get().getUser_user_number().getUser_number());

        log.info("lecId : {}", deleteLecId);


        reviewRepository.deleteById(Long.valueOf(String.valueOf(data.get("post_id"))));
        reviewRecordRepository.deleteById(deleteLecId);

    }

}
