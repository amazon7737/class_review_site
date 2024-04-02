package com.dsu_review_api.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class JwtTokenTest {

    @Autowired
    JwtUtil jwtUtil;


    @Test
    void 토큰_만료_확인(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjIwMTkxNDM0IiwiaWF0IjoxNzA0NTIyMDIzLCJleHAiOjE3MDQ1NTgwMjN9.WGPUQbU2Qg76_bRfoyS3SOIXAQyOQ_bB5ORuL0xS3CU";
        jwtUtil.getExpirationDate(token);
        jwtUtil.isValidToken(token);


    }
}
