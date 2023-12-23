package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.UserRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;



    // 회원가입?
    @Test
//    @Transactional
    void save() {

        // 빈거
        // 중복된학번인지
        User user1 = userRepository.save(User.builder()

                .user_number("20191434")
                .user_name("김강민")
                .password("1234")
                .department("소프트웨어학과")
                .build());

        assertNotNull(user1);
    }

    // 로그인
    @Test
    @Transactional
    void login(){
        User data = User.builder()
                .user_number("20191434")
                .password("1234")
                .build();


        User userList = userRepository.checkLogin(data.getUser_number(), data.getPassword());




    }



}
