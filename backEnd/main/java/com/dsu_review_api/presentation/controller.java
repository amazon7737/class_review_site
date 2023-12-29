package com.dsu_review_api.presentation;


import com.dsu_review_api.application.ClassListService;
import com.dsu_review_api.application.LecService;
import com.dsu_review_api.application.ReviewPostService;
import com.dsu_review_api.application.UserService;
import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.domain.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Api Controller
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class controller {

    private final UserService userService;

    private final LecService lecService;

    private final ReviewPostService reviewPostService;

    private final ClassListService classListService;

    @PostMapping("/signin")
    public Api login(@RequestBody User data) {

        Api api = new Api();
//        log.info("loginData: {}", data.getUser_number());
//        log.info("loginData: {}", data.getDepartment());
//        log.info("loginData: {}", data.getUser_name());

        log.info(" ------ 로그인 요청함 --------- {}님",data.getUser_number() );
        if(data.getUser_number().length() < 8 || data.getUser_number().length() > 8){
            api.setServer("학번을 다시 확인해주세요");
            api.setStatus("201");
            api.setData(null);
            return api;
        }

        if(data.getUser_number() == null || data.getPassword() == null || data.getUser_number().length() == 0 || data.getPassword().length() == 0){
            api.setServer("아이디 및 비밀번호를 모두 입력해주세요");
            api.setStatus("201");
            api.setData(null);
            return api;
        }

        try{
            userService.loginUser(data);
            api.setStatus("200");
            api.setServer("로그인성공");
            api.setData(null);
            return api;

        } catch(NullPointerException e){
            e.printStackTrace();
            api.setServer("아이디 또는 비밀번호가 틀렸습니다.");
            api.setStatus("203");
            api.setData(null);
            return api;
        }
    }

    @PostMapping("/signup")
    public Api signup(@RequestBody User data){
        Api api = new Api();

        if(data.getUser_number().length() < 8 || data.getUser_number().length() > 8){
            api.setServer("학번을 다시 확인해주세요");
            api.setStatus("201");
            api.setData(null);
            return api;
        }

        log.info("회원가입 시도 -----: {}", data.getUser_number() + " " + data.getPassword() + " " + data.getUser_name() + " " + data.getDepartment());
        // 세밀한 확인
        if(data.getUser_number().length() == 0 || data.getUser_name().length() == 0 || data.getDepartment().length() == 0 || data.getPassword().length() == 0 ){

            api.setServer("회원가입 정보를 다시 확인해주세요");
            api.setStatus("201");
            api.setData(null);
            return api;
        }

        try{

            userService.checkUser(data);
//            userService.signupUser(data);
            api.setServer("회원가입 성공");
            api.setData(null);
            api.setStatus("200");
            return api;

        }catch (NullPointerException e){

            e.printStackTrace();
            api.setStatus("203");
            api.setServer(e.getMessage());
            api.setData(null);
            return api;

        }

    }

    @GetMapping("/department")
    public Api selectDepartment(){
        Api api = new Api();


        List<String> lecList = lecService.selectDepartment();

//        log.info("lec:", lecList);

        api.setStatus("200");
        api.setData(lecList);
        api.setServer("학과 목록 조회입니다.");

        return api;
    }

    @GetMapping("/review")
    public Api selectReviewPost(){
        Api api = new Api();
        JSONArray resultArray = new JSONArray();

        List<Review_post> postList = reviewPostService.selectPost();


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
    public Api addReviewPost(@RequestBody JSONObject requestAddByPost) {
        Api api = new Api();

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
    public Api updateReviewPost(@RequestBody JSONObject updateData){
        Api api = new Api();

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
    public Api deleteReviewPost(@RequestBody JSONObject data){
        Api api = new Api();

        try{
            reviewPostService.deletePost(data);
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

    @GetMapping("/class")
    public Api selectClassList() throws NullPointerException{
        Api api = new Api();

        List<Class_list> classList = classListService.selectClassList();

        JSONArray resultArray = new JSONArray();

        for(Integer i = 0; i< classList.size(); i++){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("class_number", classList.get(i).getClass_number());
        jsonObject.put("professor", classList.get(i).getProfessor_prof_id().getProf_name());
        jsonObject.put("lec_name", classList.get(i).getLec_lec_number().getLec_name());
        jsonObject.put("lec_type", classList.get(i).getLec_lec_number().getLec_type());
        jsonObject.put("department", classList.get(i).getLec_lec_number().getDepartment());
        jsonObject.put("class_introduction", classList.get(i).getClass_introduction());
        jsonObject.put("star_lating", classList.get(i).getStar_lating());
//        jsonObject.put("image_url", classList.get(i).getCaptain_image().getImage_url());


        resultArray.add(jsonObject);

        }

//        log.info("result : {}", resultArray);

        api.setStatus("200");
        api.setServer("클래스 목록입니다");
        api.setData(resultArray);

        return api;
    }




    @GetMapping("/post/detail")
    public Api postDetail(@RequestParam("id") String id){
        Api api = new Api();

        log.info("!!!!!!!: {}", id);
        try{

        Class_list classList = classListService.selectClassListByParams(Long.valueOf(id));
        // ClassNumber
        List<Review_post> post = reviewPostService.detailPost(classList);

        log.info("post: {}", post.get(1).getPost_title());

        JSONArray totalArray = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        for(Integer i = 0; i< post.size(); i++){
            JSONObject jsonObject3 = new JSONObject();

            jsonObject3.put("post_title", post.get(i).getPost_title());
            jsonObject3.put("post_content", post.get(i).getPost_content());
            jsonObject3.put("post_id", post.get(i).getId());
            jsonObject3.put("likes", post.get(i).getLikes());
            jsonObject3.put("nickname", post.get(i).getUser_user_number().getNickname());
            jsonObject3.put("create_time", post.get(i).getCreateTime());
            jsonObject3.put("star_lating", post.get(i).getStar_lating());

            jsonObject2.put("post"+i, jsonObject3);

        }



        jsonObject.put("class_number", classList.getClass_number());
        jsonObject.put("lec_name", classList.getLec_lec_number().getLec_name());
        jsonObject.put("department", classList.getLec_lec_number().getDepartment());
        jsonObject.put("lec_type", classList.getLec_lec_number().getLec_type());
        jsonObject.put("class_introduction", classList.getClass_introduction());
        jsonObject.put("image_url", classList.getCaptain_image().getImage_url());
        jsonObject.put("star_lating", classList.getStar_lating());
        jsonObject.put("prof_name", classList.getProfessor_prof_id().getProf_name());


        jsonArray.add(jsonObject);
        jsonArray2.add(jsonObject2);
        totalArray.add(jsonArray);
        totalArray.add(jsonArray2);

        api.setStatus("200");
        api.setServer("잘받았습니다"+ id);
        api.setData(totalArray);

        return api;
        }catch (IllegalArgumentException e){
            api.setStatus("203");
            api.setServer(e.getMessage());
            api.setData(null);
            return api;
        }
    }



    static class Api{
        private String status;

        private String server;

        private List<String> data;

        public String getStatus(){
            return status;
        }

        public String getServer(){
            return server;
        }

        public  List<String> getData(){
            return data;
        }

        public void setStatus(String status){
            this.status = status;
        }

        public void setServer(String server){
            this.server = server;
        }

        public void setData(List<String> data){
            this.data = data;
        }

    }
}
