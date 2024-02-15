package com.dsu_review_api.presentation;


import com.dsu_review_api.infrastructure.config.Api;
import com.dsu_review_api.infrastructure.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final JwtUtil jwtUtil;


    @GetMapping("/")
    public Api Home(){

        Api api = new Api();


        api.setStatus("200");
        api.setServer("안녕하세요 수업리뷰사이트 api 서버 입니다 ^_^");
//        api.setData(List.of(String.valueOf(date.getTime())));
        api.setData(List.of(String.valueOf(LocalDateTime.now())));
        return api;

    }


    // 토큰 테스트
    @PostMapping("/auth")
    public Api authenticateTest(@RequestBody JSONObject request) {
        Api api = new Api();

        log.info("auth 호출-------");
        try {
            String token = jwtUtil.generateToken(String.valueOf(request.get("user_number")));
            Date date = jwtUtil.getExpirationDate(token);
            api.setStatus("200");
            api.setServer(token);
            api.setData(List.of(String.valueOf(date)));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("인증 실패");
        }

        return api;

    }
}
