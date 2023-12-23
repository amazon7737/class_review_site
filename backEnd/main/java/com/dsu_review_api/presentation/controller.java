package com.dsu_review_api.presentation;


import com.dsu_review_api.application.LecService;
import com.dsu_review_api.application.ReviewPostService;
import com.dsu_review_api.application.UserService;
import com.dsu_review_api.application.dto.UserLogindto;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.domain.User;
import jakarta.servlet.ServletException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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

    @PostMapping("/signin")
    public Api login(@RequestBody User data) throws IllegalArgumentException{

        Api api = new Api();
//        log.info("loginData: {}", data.getUser_number());
//        log.info("loginData: {}", data.getDepartment());
//        log.info("loginData: {}", data.getUser_name());

        log.info(" ------ 로그인 요청함 --------- {}님",data.getUser_number() );

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

        }catch (IllegalArgumentException e){
            e.printStackTrace();
            api.setServer("회원정보가 없습니다.");
            api.setStatus("201");
            api.setData(null);
            return api;

        }catch(NullPointerException e){
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
    public Api selectReviewPost(Pageable pageable){
        Api api = new Api();

        Page<Review_post> postList = reviewPostService.selectPost(pageable);

        List<String> result = new ArrayList<>();
//        for(Integer i =0 ; i<postList.size(); i++){
//            result.add(String.valueOf(postList.get(i)));
//        }

//        log.info(String.valueOf(postList));


        api.setStatus("200");
        api.setServer("수강후기 목록입니다.");
//        api.setData(postList);

        return api;

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
