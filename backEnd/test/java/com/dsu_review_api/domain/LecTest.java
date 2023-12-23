package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.LecRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/* 강의 목록 */

@SpringBootTest
@Slf4j
public class LecTest {

    @Autowired
    LecRepository lecRepository;

    // 임의의 강의 목록 추가
    @Test
    void addLec(){
        List<String> lecListByName = new ArrayList<>(List.of("소프트웨어공학", "데이터베이스 개론", "ui ux 설계", "경찰행정학", "경영학", "영어1", "건축학"));
        List<String> lecListByType = new ArrayList<>(List.of("전공선택", "전공선택", "전공선택", "전공필수", "전공필수", "교양필수", "전공필수"));
        List<String> lecListByDepartment = new ArrayList<>(List.of("소프트웨어학과", "소프트웨어학과", "시각디자인학과", "경찰행정학과", "경영학과", "영어학과", "건축학과"));

        for(Integer i = 0; i<lecListByName.size(); i++){
            Lec lec = Lec.builder()
                    .lec_name(lecListByName.get(i))
                    .lec_type(lecListByType.get(i))
                    .department(lecListByDepartment.get(i))
                    .build();
            lecRepository.save(lec);
        }

//        Lec lec = Lec.builder()
//                .lec_name("소프트웨어공학")
//                .lec_type("전공필수")
//                .department("소프트웨어학과")
//                .build();
//
//        Lec lec2 = Lec.builder()
//                .lec_name("데이터베이스 개론")
//                .lec_type("전공필수")
//                .department("소프트웨어학과")
//                .build();
//
//        Lec lec3 = Lec.builder()
//                .lec_name("아이데이션")
//                .lec_type("산학과목")
//                .department("시각디자인학과")
//                .build();
//
//        Lec lec4 = Lec.builder()
//                .lec_name("인문과목1")
//                .lec_type("전공선택")
//                .department("경영학과")
//                .build();

    }


    @Test
    void selectDepartment(){
       List<String> lec1 =  lecRepository.selectDepartment();

       for(Integer i =0; i<lec1.size(); i++){

           log.info("lec1: {}", String.valueOf(lec1.get(i)));

       }



    }

}
