package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.UserRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
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
        User user = userRepository.save(User.builder()

                .user_number("20191585")
                .user_name("정규환")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("뀨토리짱짱")
                .build());


        User user1 = userRepository.save(User.builder()

                .user_number("20190987")
                .user_name("병아리")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("아이스크림샌드위치")
                .build());

        User user2 = userRepository.save(User.builder()

                .user_number("20196544")
                .user_name("김디비")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("원주민123")
                .build());

        User user3 = userRepository.save(User.builder()

                .user_number("20192345")
                .user_name("이하나")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("하얀색")
                .build());

        User user4 = userRepository.save(User.builder()

                .user_number("20194324")
                .user_name("삼둘이")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("겐지여친")
                .build());

        User user5 = userRepository.save(User.builder()

                .user_number("20191111")
                .user_name("하루부")
                .password("1234")
                .department("소프트웨어학과")
                .nickname("원주민122")
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
