package com.dsu_review_api.application;

import com.dsu_review_api.domain.*;
import com.dsu_review_api.infrastructure.persistence.*;
import jakarta.persistence.Index;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewRepository reviewRepository;

    private final ReviewRecordRepository reviewRecordRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private final LecService lecService;

    private final ReviewLecService reviewLecService;

    private  final ClassListService classListService;

    private final PostLikesRepository postLikesRepository;

    /**
     *
     * 너무 느려서 폐기
     * @return List<Review_Post>
     */
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

            reviewRepository.save(reviewPost); // 게시글 추가

            reviewLecService.saveRecord(reviewRecord); // 게시글 내용 추가

            classListService.updateStar(reviewPost.getLec_lec_name().getLec_id(), Long.valueOf(String.valueOf(request.get("star_lating"))));; // 별점 추가






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
                .star_lating(data.get().getStar_lating())
                .build();


        reviewRepository.save(updatePost);
    }

    @Transactional
    public void deletePost(Long post_id){

        /**
         * 수강후기 글을 삭제했을때 다시 작성하도록 해줄것인지?
         */


        postLikesRepository.deleteAllByPostId(post_id);
        reviewRecordRepository.deleteById(post_id);
        reviewRepository.deleteById(post_id);



    }

    /**
     *
     * findByClassNumber 한후 Select
     */
    @Transactional
    public List<Review_post> detailPost(Class_list request){


        List<Review_lec_list> record = reviewLecService.findByClassNumber(request);





         List<Review_post> post = reviewRepository.selectfromId(record.get(0).getPost_id().getLec_lec_name().getLec_id());




//         log.info("post: {}", post);


//         log.info("post: {}", post.getPost_title());

        return post;

    }

    /**
     * user_number 와 lec_id 로 검색
     */
    @Transactional
    public Review_post myPostByLec(String user_number , Long lec_id){


        Review_post myinfo = reviewRepository.findByLecAndUser_Number(lec_id, user_number).get();

        return myinfo;



    }

    @Transactional
    public void addLikesByPost(Long Review_id, String user_number){

        Review_post reviewPost = reviewRepository.findById(Review_id).get();
        User user = userRepository.findById(user_number).get();

        Post_likes postLikes = Post_likes.builder()
                        .post(reviewPost)
                        .user(user)
                        .build();

        try{

        Post_likes findPost = postLikesRepository.findByUserAndPost(postLikes.getUser(), postLikes.getPost());
            if(findPost.getUser().getUser_number().equals(user_number)){
                throw new IllegalArgumentException("이미 좋아요한 사람입니다.");
            }

        }catch (NullPointerException e){
            postLikesRepository.save(postLikes);
            reviewRepository.updateLikes(Review_id);


        }

    }

    /**
     * ID값으로 빠르게 findById
     */
    public List<Review_post> findByPost(Long id){
        return reviewRepository.selectfromId(id);
    } // lec_id


    /**
     * Lec_id으로 find Review_Post 별점순
     */
    @Transactional
    public List<Review_post> findByClassNumberOrderByStarlating(Long lec_id){

        Lec lec = lecService.findById(lec_id).get();

        List<Review_post> reviewPost = reviewRepository.findBylec_idOrderByLikes(lec);
        log.info("!!!!!!!:{}", reviewPost.size());
//        log.info("!!!!!!!!: {}", reviewPost.get(0).getPost_title());
//        log.info("!!!!!!!!: {}", reviewPost.get(0).getPost_content());
//        log.info("!!!!!!!!: {}", reviewPost.get(0).getCreateTime());
//        log.info("!!!!!!!!: {}", reviewPost.get(0).getLikes());


        return reviewPost;

    }

}
