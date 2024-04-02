package com.dsu_review_api.presentation;

import com.dsu_review_api.application.UserService;
import com.dsu_review_api.domain.User;
import com.dsu_review_api.infrastructure.config.Api;
import com.dsu_review_api.infrastructure.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


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
        } else if(data.getUser_number() == null || data.getPassword() == null || data.getUser_number().length() == 0 || data.getPassword().length() == 0){
            api.setServer("아이디 및 비밀번호를 모두 입력해주세요");
            api.setStatus("201");
            api.setData(null);
            return api;
        }

        try{
            User user = userService.loginUser(data);
            String token = jwtUtil.generateToken(String.valueOf(data.getUser_number()));

            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();

            object.put("token", token);
            object.put("userNickname", user.getNickname());
            object.put("userId", user.getUser_number());

            array.add(object);

            api.setStatus("200");
            api.setServer("로그인성공");
            api.setData(array);

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

        }catch (DataIntegrityViolationException e){
            api.setStatus("401");
            api.setServer("중복된 닉네임입니다.");
            api.setData(null);
            return api;
        }


    }
}
