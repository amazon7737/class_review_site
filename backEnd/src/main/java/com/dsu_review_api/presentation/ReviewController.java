package com.dsu_review_api.presentation;

import com.dsu_review_api.application.ClassListService;
import com.dsu_review_api.application.ReviewPostService;
import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.infrastructure.config.Api;
import com.dsu_review_api.infrastructure.config.JwtUtil;
import jakarta.persistence.Index;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewPostService reviewPostService;
    private final JwtUtil jwtUtil;
    private final ClassListService classListService;


    @GetMapping("/review") // 수강후기 전체조회
    public Api selectReviewPost(){
//        public Api selectReviewPost(@RequestHeader("Authorization") String token){

        Api api = new Api();
        JSONArray resultArray = new JSONArray();
        List<Review_post> postList = reviewPostService.selectPost();

//        log.info("header: {}", token);


//        try{
//            jwtUtil.isValidToken(token);
//            jwtUtil.getUsernameFromToken(token);
//        }catch (Exception e){
//            e.printStackTrace();
//
//            api.setStatus("401");
//            api.setServer("로그인이 유효하지 않습니다.");
//            api.setData(null);
//            return api;
//        }

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

        api.setStatus("200");
        api.setServer("수강후기 목록입니다.");
        api.setData(resultArray);
        return api;
    }

    @PostMapping("/review/add")
//    public Api addReviewPost(@RequestBody JSONObject requestAddByPost) {
    public Api addReviewPost(@RequestBody JSONObject requestAddByPost, @RequestHeader("Authorization") String token) {
        Api api = new Api();

        try{
            jwtUtil.isValidToken(token);
            jwtUtil.getUsernameFromToken(token);
        }catch (Exception e){
            e.printStackTrace();

            api.setStatus("401");
            api.setServer("로그인이 유효하지 않습니다.");
            api.setData(null);
            return api;
        }

        try{
            reviewPostService.addPost(requestAddByPost);
            api.setStatus("200");
            api.setServer("수강후기 작성 완료");
            api.setData(null);
            return api;
        }catch (IllegalArgumentException e){
            log.info("IllegalArgumentException -----");
            api.setServer(e.getMessage());
            api.setStatus("203");
            api.setData(null);
            return api;
        }catch (NullPointerException e){
            log.info("NullPointerException-----");
            api.setServer("강의가 없습니다.");
            api.setStatus("202");
            api.setData(null);
            return api;
        }catch (Exception e){
            log.info("이미 작성한 강의------");
            api.setServer("한 강의당 리뷰를 하나씩 작성가능합니다.");
            api.setStatus("299");
            api.setData(null);
            return api;
        }
//        catch (SQLIntegrityConstraintViolationException e){
//            api.setStatus("204");
//            api.setServer("이미 작성한 강의입니다.");
//            api.setData(null);
//            return api;
//        }

    }

    @PostMapping("/review/update")
//    public Api updateReviewPost(@RequestBody JSONObject updateData){
    public Api updateReviewPost(@RequestBody JSONObject updateData, @RequestHeader("Authorization") String token){
        Api api = new Api();

        try{
            jwtUtil.isValidToken(token);
            jwtUtil.getUsernameFromToken(token);
        }catch (Exception e){
            e.printStackTrace();

            api.setStatus("401");
            api.setServer("로그인이 유효하지 않습니다.");
            api.setData(null);
            return api;
        }

        try{
            reviewPostService.updatePost(updateData);
            api.setStatus("200");
            api.setServer("수정 완료");
            api.setData(null);
        }catch (Exception e){
            e.printStackTrace();
            api.setStatus("500");
            api.setServer("알수 없는 문제가 발생했습니다. 관리자에게 문의하세요.");
            return api;
        }

        return api;
    }

    @PostMapping("/review/delete")
//    public Api deleteReviewPost(@RequestBody JSONObject data){
    public Api deleteReviewPost(@RequestParam(name = "id") Long id, @RequestHeader("Authorization") String token){
        log.info("삭제---");
        Api api = new Api();

        try{
            jwtUtil.isValidToken(token);
            jwtUtil.getUsernameFromToken(token);
        }catch (Exception e){
            e.printStackTrace();

            api.setStatus("401");
            api.setServer("로그인이 유효하지 않습니다.");
            api.setData(null);
            return api;
        }

        try{
            reviewPostService.deletePost(Long.valueOf(id));
            api.setStatus("200");
            api.setServer("삭제 완료");
            api.setData(null);
            return api;
        }catch (Exception e){
            e.printStackTrace();
            api.setStatus("500");
            api.setServer("알수 없는 문제가 발생했습니다. 관리자에게 문의하세요.");
            return api;
        }

    }


    @GetMapping("/post/detail")
//    public Api postDetail(@RequestParam(value = "id") String id){
    public Api postDetail(@RequestParam(value = "id") String id,@RequestParam(value = "user_number") String user_number, @RequestHeader("Authorization") String token){

        Api api = new Api();

        /**
         * 첫번째 [] : 해당 강의 정e
         * 두번째 [] : 전체 수강후기
         * 세번째 [] : 자신이 올린 수강후기
         * 네번째 [] : 좋아요 top3 수강후기
         */

        try{ // 모든 예외 없이 완전히 순조롭고 잘 진행됫을경우

        JSONArray detailInfo = new JSONArray();
        JSONArray AllPost = new JSONArray();
        JSONArray myPost = new JSONArray();
        JSONArray TopThreePost = new JSONArray();

        JSONArray resultArray = new JSONArray();

        JSONArray totalArray = new JSONArray();

        JSONObject myPostJson = new JSONObject();


        log.info("상세페이지 ------- {}", id);

        Class_list classList = classListService.selectClassListByParams(Long.valueOf(id));
        List<Review_post> DetailPost = reviewPostService.detailPost(classList); // IndexOutBoundException 터짐

        // 강의 상세 정보 JSON

        JSONObject detailInfoJson = new JSONObject();
        detailInfoJson.put("class_number", classList.getClass_number());
        detailInfoJson.put("image_url", classList.getCaptain_image().getImage_url());
        detailInfoJson.put("star_lating", classList.getStar_lating());
        detailInfoJson.put("lec_name", classList.getLec_lec_number().getLec_name());
        detailInfoJson.put("lec_id", classList.getLec_lec_number().getLec_id());
        detailInfoJson.put("prof_name", classList.getProfessor_prof_id().getProf_name());
        detailInfoJson.put("lec_type" , classList.getLec_lec_number().getLec_type());
        detailInfoJson.put("department", classList.getLec_lec_number().getDepartment());
        detailInfoJson.put("class_introduction", classList.getClass_introduction());
        detailInfo.add(detailInfoJson);


        /* */
        // 전체 정보
        for(Integer i = 0; i< DetailPost.size(); i++){
            JSONObject post = new JSONObject();
            post.put("likes", DetailPost.get(i).getLikes());
            post.put("create_time", DetailPost.get(i).getCreateTime());
            post.put("post_title", DetailPost.get(i).getPost_title());
            post.put("post_content", DetailPost.get(i).getPost_content());
            post.put("post_id", DetailPost.get(i).getId());
            post.put("nickname", DetailPost.get(i).getUser_user_number().getNickname());
            post.put("star_lating", DetailPost.get(i).getStar_lating());
            post.put("lec_id", DetailPost.get(i).getLec_lec_name().getLec_name());
            AllPost.add(post);
        }

        // 나의 수강후기 조회 JSON
        Review_post myPostGet = reviewPostService.myPostByLec(user_number, classList.getLec_lec_number().getLec_id());


        myPostJson.put("likes", myPostGet.getLikes());
        myPostJson.put("create_time", myPostGet.getCreateTime());
        myPostJson.put("post_title", myPostGet.getPost_title());
        myPostJson.put("post_content", myPostGet.getPost_content());
        myPostJson.put("post_id", myPostGet.getId());
        myPostJson.put("nickname", myPostGet.getUser_user_number().getNickname());
        myPostJson.put("star_lating", myPostGet.getStar_lating());
        myPostJson.put("lec_id", myPostGet.getLec_lec_name().getLec_id());
        myPost.add(myPostJson);


        // top3 수강후기 조회 JSON
        List<Review_post> TopThreePostGet = reviewPostService.findByClassNumberOrderByStarlating(classList.getLec_lec_number().getLec_id());

        for(Integer i = 0; i< TopThreePostGet.size(); i++){
            JSONObject TopThreePostJson = new JSONObject(); // 매번 새로정의해줘야 데이터가 증발하지 않음
            TopThreePostJson.put("likes", TopThreePostGet.get(i).getLikes());
//            TopThreePostJson.put("create_time", TopThreePostGet.get(i).getCreateTime());
//            TopThreePostJson.put("lec_id", TopThreePostGet.get(i).getLec_lec_name().getLec_id());
            TopThreePostJson.put("nickname", TopThreePostGet.get(i).getUser_user_number().getNickname());
            TopThreePostJson.put("star_lating", TopThreePostGet.get(i).getStar_lating());
//            TopThreePostJson.put("post_title", TopThreePostGet.get(i).getPost_title());
//            TopThreePostJson.put("post_content", TopThreePostGet.get(i).getPost_content());

            TopThreePost.add(TopThreePostJson);
        }


        /**
         * 최종 배열에 추가
         */
        resultArray.add(detailInfo); // 강의 정보
        resultArray.add(AllPost); // 전체 수강후기
        resultArray.add(myPost); // 나의 수강후기
        resultArray.add(TopThreePost); // top3 수강후기


        api.setStatus("200");
        api.setServer("잘 들어갔습니다.");
        api.setData(resultArray);

        return api;
        }catch (IndexOutOfBoundsException e){ // 강의를 검색했더니 안떠..
            log.info( "--------수강후기가 없음 -------");
            e.printStackTrace();
            Class_list classList = classListService.selectClassListByParams(Long.valueOf(id));

            JSONArray detailInfoArray = new JSONArray();
            JSONArray AllPostArray = new JSONArray();
            JSONArray myPostArray = new JSONArray();
            JSONArray TopThreePostArray = new JSONArray();

            JSONArray resultArray = new JSONArray();

            JSONObject myPostJson = new JSONObject();

            // 강의 정보 넣기
            JSONObject detailInfoJson = new JSONObject();
            detailInfoJson.put("class_number", classList.getClass_number());
            detailInfoJson.put("image_url", classList.getCaptain_image().getImage_url());
            detailInfoJson.put("star_lating", classList.getStar_lating());
            detailInfoJson.put("lec_name", classList.getLec_lec_number().getLec_name());
            detailInfoJson.put("lec_id", classList.getLec_lec_number().getLec_id());
            detailInfoJson.put("prof_name", classList.getProfessor_prof_id().getProf_name());
            detailInfoJson.put("lec_type" , classList.getLec_lec_number().getLec_type());
            detailInfoJson.put("department", classList.getLec_lec_number().getDepartment());
            detailInfoJson.put("class_introduction", classList.getClass_introduction());
            detailInfoArray.add(detailInfoJson);
            /* ------ */




            resultArray.add(detailInfoArray);
            resultArray.add(AllPostArray);
            resultArray.add(myPostArray);
            resultArray.add(TopThreePostArray);

            JSONArray totalArray = new JSONArray();


            api.setStatus("201");
            api.setServer("수강후기가 없습니다.");
            api.setData(resultArray);




            return api;


        }catch (NoSuchElementException e){

            log.info("------해당유저 수강후기만 없음-----");

            JSONArray detailInfo = new JSONArray();
            JSONArray AllPost = new JSONArray();
            JSONArray myPost = new JSONArray();
            JSONArray TopThreePost = new JSONArray();

            JSONArray resultArray = new JSONArray();
            JSONArray totalArray = new JSONArray(); // 마지막 배열

            JSONObject myPostJson = new JSONObject();

            // 강의정보넣기
            Class_list classList = classListService.selectClassListByParams(Long.valueOf(id));

            JSONObject detailInfoJson = new JSONObject();
            detailInfoJson.put("class_number", classList.getClass_number());
            detailInfoJson.put("image_url", classList.getCaptain_image().getImage_url());
            detailInfoJson.put("star_lating", classList.getStar_lating());
            detailInfoJson.put("lec_name", classList.getLec_lec_number().getLec_name());
            detailInfoJson.put("lec_id", classList.getLec_lec_number().getLec_id());
            detailInfoJson.put("prof_name", classList.getProfessor_prof_id().getProf_name());
            detailInfoJson.put("lec_type" , classList.getLec_lec_number().getLec_type());
            detailInfoJson.put("department", classList.getLec_lec_number().getDepartment());
            detailInfoJson.put("class_introduction", classList.getClass_introduction());
            detailInfo.add(detailInfoJson);

            /* */
            List<Review_post> DetailPost = reviewPostService.detailPost(classList); // IndexOutBoundException 터짐

            // 전체 정보
            for(Integer i = 0; i< DetailPost.size(); i++){
                JSONObject post = new JSONObject();
                post.put("likes", DetailPost.get(i).getLikes());
                post.put("create_time", DetailPost.get(i).getCreateTime());
                post.put("post_title", DetailPost.get(i).getPost_title());
                post.put("post_content", DetailPost.get(i).getPost_content());
                post.put("post_id", DetailPost.get(i).getId());
                post.put("nickname", DetailPost.get(i).getUser_user_number().getNickname());
                post.put("star_lating", DetailPost.get(i).getStar_lating());
                post.put("lec_id", DetailPost.get(i).getLec_lec_name().getLec_name());
                AllPost.add(post);
            }

            // top3 수강후기 조회 JSON
            List<Review_post> TopThreePostGet = reviewPostService.findByClassNumberOrderByStarlating(classList.getLec_lec_number().getLec_id());

            for(Integer i = 0; i< TopThreePostGet.size(); i++){
                JSONObject TopThreePostJson = new JSONObject(); // 매번 새로정의해줘야 데이터가 증발하지 않음
                TopThreePostJson.put("likes", TopThreePostGet.get(i).getLikes());
//                TopThreePostJson.put("create_time", TopThreePostGet.get(i).getCreateTime());
//                TopThreePostJson.put("lec_id", TopThreePostGet.get(i).getLec_lec_name().getLec_id());
                TopThreePostJson.put("star_lating", TopThreePostGet.get(i).getStar_lating());
                TopThreePostJson.put("nickname" , TopThreePostGet.get(i).getUser_user_number().getNickname());
//                TopThreePostJson.put("post_title", TopThreePostGet.get(i).getPost_title());
//                TopThreePostJson.put("post_content", TopThreePostGet.get(i).getPost_content());

                TopThreePost.add(TopThreePostJson);
            }


            /**
             * 최종 배열에 추가
             */
            resultArray.add(detailInfo); // 강의 정보
            resultArray.add(AllPost); // 전체 수강후기
            resultArray.add(myPost); // 나의 수강후기
            resultArray.add(TopThreePost); // top3 수강후기


            api.setStatus("202");
            api.setServer("해당유저가 남긴 수강후기가 없습니다.");
            api.setData(resultArray);


            return api;



        }



//        Api api = new Api();
//        JSONArray totalArray = new JSONArray();
//        JSONArray jsonArray = new JSONArray();
//        JSONArray jsonArray2 = new JSONArray();
//        JSONArray jsonArray3 = new JSONArray();
//        JSONArray jsonArray4 = new JSONArray(); // 베스트 메뉴 top3
//        JSONObject jsonObject = new JSONObject();
//        JSONObject jsonObject2 = new JSONObject();
//        JSONObject jsonObject5 = new JSONObject();
//        JSONObject jsonObject6 = new JSONObject(); // 베스트 메뉴 top3
//
//        try{
//            jwtUtil.isValidToken(token);
//            jwtUtil.getUsernameFromToken(token);
//        }catch (Exception e){
//            e.printStackTrace();
//
//            api.setStatus("401");
//            api.setServer("로그인이 유효하지 않습니다.");
//            api.setData(null);
//            return api;
//        }
//
//        Class_list classList = classListService.selectClassListByParams(Long.valueOf(id));
//
//
//        log.info("!!!!!!!: {}", id);
//        try{
//            Review_post myPost = reviewPostService.myPostByLec( user_number, classList.getLec_lec_number().getLec_id());
//
//            List<Review_post> post = reviewPostService.detailPost(classList);
////            List<Review_post> TopThreeReviewByLikes = reviewPostService.findByClassNumberOrderByStarlating(myPost.getLec_lec_name().getLec_name());
//
//            log.info("selectClassListByParams");
//            // ClassNumber
//            log.info("detailPost");
//
//            log.info("my------: {}", myPost.getPost_content());
////            log.info("post: {}", post.get(1).getPost_title());
//
////            List<Review_post> TopThreeReviewByLikes = reviewPostService.findByClassNumberOrderByStarlating(myPost.getLec_lec_name().getLec_name());
////            for(int i = 0; i< TopThreeReviewByLikes.size(); i++){
////                log.info("1:{}", TopThreeReviewByLikes.get(i).getPost_title());
////                log.info("2:{}", TopThreeReviewByLikes.get(i).getPost_content());
////                log.info("3:{}", TopThreeReviewByLikes.get(i).getLikes());
////
////            }
//
//
//            jsonObject5.put("post_title", myPost.getPost_title());
//            jsonObject5.put("post_content", myPost.getPost_content());
//            jsonObject5.put("post_id", myPost.getId());
//            jsonObject5.put("likes", myPost.getLikes());
//            jsonObject5.put("nickname", myPost.getUser_user_number().getNickname());
//            jsonObject5.put("creakte_time", myPost.getCreateTime());
//            jsonObject5.put("star_lating", myPost.getStar_lating());
//
//
//            //베스트 좋아요수
////            for(int i = 0; i< TopThreeReviewByLikes.size(); i++){
////
////
////            jsonObject6.put("post_title", TopThreeReviewByLikes.get(i).getPost_title());
////            jsonObject6.put("post_content", TopThreeReviewByLikes.get(i).getPost_content());
////            jsonObject6.put("post_id", TopThreeReviewByLikes.get(i).getId());
////            jsonObject6.put("likes", TopThreeReviewByLikes.get(i).getLikes());
////            jsonObject6.put("create_time", TopThreeReviewByLikes.get(i).getCreateTime());
////            }
//
//
//
//            // 베스트 메뉴 3
//            List<Review_post> list =  reviewPostService.findByClassNumberOrderByStarlating(classList.getLec_lec_number().getLec_id());
//            JSONObject object2 = new JSONObject();
//            JSONArray array = new JSONArray();
//
//            for(Integer i = 0; i< list.size(); i++){
//                JSONObject object = new JSONObject();
//
//                object.put("post_title", list.get(i).getPost_title());
//                object.put("post_content", list.get(i).getPost_content());
//                object.put("likes", list.get(i).getLikes());
//
//                object2.put(i, object);
//
//
//            }
//            array.add(object2);
//
//
//
//
//
//            jsonArray4.add(jsonObject6);
//
//
//            for(Integer i = 0; i< post.size(); i++){
//                JSONObject jsonObject3 = new JSONObject();
//                log.info("create_time: {}", post.get(i).getCreateTime());
//
//                jsonObject3.put("post_title", post.get(i).getPost_title());
//                jsonObject3.put("post_content", post.get(i).getPost_content());
//                jsonObject3.put("post_id", post.get(i).getId());
//                jsonObject3.put("likes", post.get(i).getLikes());
//                jsonObject3.put("nickname", post.get(i).getUser_user_number().getNickname());
//                jsonObject3.put("create_time", post.get(i).getCreateTime());
//                jsonObject3.put("star_lating", post.get(i).getStar_lating());
//
//                jsonObject2.put(i, jsonObject3);
//
//            }
//
//
//
//            jsonObject.put("class_number", classList.getClass_number());
//            jsonObject.put("lec_name", classList.getLec_lec_number().getLec_name());
//            jsonObject.put("department", classList.getLec_lec_number().getDepartment());
//            jsonObject.put("lec_type", classList.getLec_lec_number().getLec_type());
//            jsonObject.put("class_introduction", classList.getClass_introduction());
//            jsonObject.put("image_url", classList.getCaptain_image().getImage_url());
//            jsonObject.put("star_lating", classList.getStar_lating());
//            jsonObject.put("prof_name", classList.getProfessor_prof_id().getProf_name());
//
//
//            jsonArray.add(jsonObject);
//            jsonArray2.add(jsonObject2);
//            jsonArray3.add(jsonObject5);
//            totalArray.add(jsonArray);
//            totalArray.add(jsonArray2);
//            totalArray.add(jsonArray3);
//            totalArray.add(array);
//
//            api.setStatus("200");
//            api.setServer("잘받았습니다"+ id);
//            api.setData(totalArray);
//
//
//        }catch (IllegalArgumentException e){
//            api.setStatus("203");
//            api.setServer(e.getMessage()); //
//            api.setData(null);
//
//        }catch (IndexOutOfBoundsException e){ // 수강후기는 없고 강의 정보만 뽑을때
//
//            log.info("수강후기가 없음---");
////            List<Review_post> post = reviewPostService.detailPost(classList);
//
//
//
//            jsonObject.put("class_number", classList.getClass_number());
//            jsonObject.put("lec_name", classList.getLec_lec_number().getLec_name());
//            jsonObject.put("department", classList.getLec_lec_number().getDepartment());
//            jsonObject.put("lec_type", classList.getLec_lec_number().getLec_type());
//            jsonObject.put("class_introduction", classList.getClass_introduction());
//            jsonObject.put("image_url", classList.getCaptain_image().getImage_url());
//            jsonObject.put("star_lating", classList.getStar_lating());
//            jsonObject.put("prof_name", classList.getProfessor_prof_id().getProf_name());
//
//
//
//
//
//
//
//            jsonArray.add(jsonObject);
//            jsonArray2.add(jsonObject2);
//
//            totalArray.add(jsonArray);
//            totalArray.add(jsonArray2);
//            totalArray.add(jsonArray3);
//            totalArray.add(jsonArray4);
//
//
//            api.setStatus("201");
//            api.setServer("수강후기를 남긴 학생이 없습니다. 최초 수강후기를 남기시겠습니까?");
//            api.setData(totalArray);
//
//
//        }catch (NoSuchElementException e){
//
//
//
//            jsonObject.put("class_number", classList.getClass_number());
//            jsonObject.put("lec_name", classList.getLec_lec_number().getLec_name());
//            jsonObject.put("department", classList.getLec_lec_number().getDepartment());
//            jsonObject.put("lec_type", classList.getLec_lec_number().getLec_type());
//            jsonObject.put("class_introduction", classList.getClass_introduction());
//            jsonObject.put("image_url", classList.getCaptain_image().getImage_url());
//            jsonObject.put("star_lating", classList.getStar_lating());
//            jsonObject.put("prof_name", classList.getProfessor_prof_id().getProf_name());
//
//
//
//            List<Review_post> post = reviewPostService.findByPost(classList.getLec_lec_number().getLec_id());
//            for(Integer i = 0; i< post.size(); i++){
//                jsonObject2.put("post_title", post.get(i).getPost_title());
//                jsonObject2.put("post_content", post.get(i).getPost_content());
//                jsonObject2.put("post_id", post.get(i).getId());
//                jsonObject2.put("likes", post.get(i).getLikes());
//                jsonObject2.put("nickname", post.get(i).getUser_user_number().getNickname());
//                jsonObject2.put("create_time", post.get(i).getCreateTime());
//                jsonObject2.put("star_lating", post.get(i).getStar_lating());
//            }
//
//
//            // 베스트 메뉴 3
//            List<Review_post> list =  reviewPostService.findByClassNumberOrderByStarlating(classList.getLec_lec_number().getLec_id());
//            JSONObject object2 = new JSONObject();
//            JSONArray array = new JSONArray();
//
//            for(Integer i = 0; i< list.size(); i++){
//                JSONObject object = new JSONObject();
//
//                object.put("post_title", list.get(i).getPost_title());
//                object.put("post_content", list.get(i).getPost_content());
//                object.put("likes", list.get(i).getLikes());
//
//                object2.put(i, object);
//
//
//            }
//            array.add(object2);
//
//            jsonArray4.add(jsonObject6);
//
//            jsonArray.add(jsonObject);
//            jsonArray2.add(jsonObject2);
//
//
//            totalArray.add(jsonArray);
//            totalArray.add(jsonArray2);
//            totalArray.add(jsonArray3);
//            totalArray.add(array);
//
//            api.setStatus("202");
//            api.setServer("해당 유저가 작성한 수강후기가 없습니다.");
//            api.setData(totalArray);
//        }
//        return api;
    }

    @PostMapping("/review/likes")
    public Api ReviewLikes(@RequestBody JSONObject request,  @RequestHeader("Authorization") String token){

        Api api = new Api();

        // ^^
        try{

            reviewPostService.addLikesByPost(Long.valueOf(String.valueOf(request.get("post_id"))), String.valueOf(request.get("user_number")));
            api.setStatus("200");
            api.setServer("좋아요수가 증가하였습니다.");

            api.setData(null);
            return api;

        }catch (IllegalArgumentException e){
            api.setStatus("401");
            api.setServer("이미 좋아요한 사람입니다.");
            api.setData(null);
            return api;
        }
    }



}
