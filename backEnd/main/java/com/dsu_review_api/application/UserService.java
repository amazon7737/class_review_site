package com.dsu_review_api.application;

import com.dsu_review_api.domain.User;
import com.dsu_review_api.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> selectUser(){
        return userRepository.findAll();
    }

    public void loginUser(User data){

        /**
         * Optional은 get() 한번 해서 벗겨주고 getuser.. 해야 값이 나온다ㅏ. 이것도 따로 정리해야함 -> findById User 객체 반환으로 변경
         */

//        User userList  = userRepository.findById(data.getUser_number()).orElseThrow(() ->
//                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));;

        User userList = userRepository.checkLogin(data.getUser_number(), data.getPassword());

//        log.info(String.valueOf(data.getPassword() != userList.getPassword()));

//                || String.valueOf(userList.getUser_number() != data.getUser_number()) == "true")


    }

    public void signupUser(User data){


        User userList = userRepository.save(User.builder()
                .user_number(data.getUser_number())
                .user_name(data.getUser_name())
                .password(data.getPassword())
                .department(data.getDepartment())
                .build());

    }

    public void checkUser(User data){

        try{
        Optional<User> userCheck = userRepository.findById(data.getUser_number());

            log.info(String.valueOf(userCheck.get().getUser_number()));

        if(userCheck.get().getUser_number().length() != 0 ){
            throw new NullPointerException("이미 있는 회원입니다");
        }
        }catch(NoSuchElementException e){
            signupUser(data);
        }

    }







}
