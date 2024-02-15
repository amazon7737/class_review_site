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

    @Test
    void resetDataLec(){
        lecRepository.deleteAll();
    }

    // 임의의 강의 목록 추가
    @Test
    void addLec(){
//        List<String> lecListByName = new ArrayList<>(List.of("소프트웨어공학", "데이터베이스 개론", "운영체제", "경찰행정학", "경영학", "영어1", "건축학", "우리몸의이해", "영미문학과소통", "고급프로그래밍", "소프트웨어개발실습2", "소프트웨어개발실습3", "컴퓨터구조", "사용자인터페이스설계"));
//        List<String> lecListByType = new ArrayList<>(List.of("전공선택", "전공선택", "전공선택", "전공필수", "전공필수", "교양필수", "전공필수", "교선균형", "교선균형", "전공필수" , "전공선택", "전공선택", "전공선택", "전공선택"));
//        List<String> lecListByDepartment = new ArrayList<>(List.of("소프트웨어학과", "소프트웨어학과", "시각디자인학과", "경찰행정학과", "경영학과", "영어학과", "건축학과", "작업치료학과", "영어학과", "소프트웨어학과" , "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과"));
//
//        for(Integer i = 0; i<lecListByName.size(); i++){
//            Lec lec = Lec.builder()
//                    .lec_name(lecListByName.get(i))
//                    .lec_type(lecListByType.get(i))
//                    .department(lecListByDepartment.get(i))
//                    .build();
//            lecRepository.save(lec);
//        }

        Lec lec = Lec.builder()
                .lec_name("소프트웨어공학")
                .lec_type("전공선택")
                .department("소프트웨어학과")
                .build();

        Lec lec2 = Lec.builder()
                .lec_name("데이터베이스 개론")
                .lec_type("전공선택")
                .department("소프트웨어학과")
                .build();


        Lec lec3 = Lec.builder()
                .lec_name("운영체제")
                .lec_type("전공선택")
                .department("소프트웨어학과")
                .build();


        Lec lec4 = Lec.builder()
                .lec_name("네트워크개론")
                .lec_type("전공선택")
                .department("소프트웨어학과")
                .build();


        Lec lec5 = Lec.builder()
                .lec_name("경영학")
                .lec_type("전공선택")
                .department("경영학과")
                .build();


        Lec lec6 = Lec.builder()
                .lec_name("경찰행정학")
                .lec_type("전공선택")
                .department("경찰행정학과")
                .build();


        Lec lec7 = Lec.builder()
                .lec_name("건축학")
                .lec_type("전공선택")
                .department("건축학과")
                .build();

        Lec lec8 = Lec.builder()
                .lec_name("재료역학")
                .lec_type("전공선택")
                .department("건축학과")
                .build();


        Lec lec9 = Lec.builder()
                .lec_name("물류관리론")
                .lec_type("전공선택")
                .department("국제물류학과")
                .build();


        Lec lec10 = Lec.builder()
                .lec_name("기초일본어회화 2")
                .lec_type("전공선택")
                .department("일본어학과")
                .build();


        Lec lec11 = Lec.builder()
                .lec_name("캠핑이론")
                .lec_type("전공선택")
                .department("캠퍼스아시아학과")
                .build();


        Lec lec12 = Lec.builder()
                .lec_name("호텔실무용어")
                .lec_type("전공선택")
                .department("호텔경영학과")
                .build();


        Lec lec13 = Lec.builder()
                .lec_name("웹진실습")
                .lec_type("전공선택")
                .department("광고홍보학과")
                .build();


        Lec lec14 = Lec.builder()
                .lec_name("C프로그래밍")
                .lec_type("전공선택")
                .department("게임학과")
                .build();


        Lec lec15 = Lec.builder()
                .lec_name("게임컨셉디자인")
                .lec_type("전공선택")
                .department("게임학과")
                .build();




            lecRepository.save(lec);
            lecRepository.save(lec2);
            lecRepository.save(lec3);
            lecRepository.save(lec4);
            lecRepository.save(lec5);
            lecRepository.save(lec6);
            lecRepository.save(lec7);
            lecRepository.save(lec8);
            lecRepository.save(lec9);
            lecRepository.save(lec10);
            lecRepository.save(lec11);
            lecRepository.save(lec12);
            lecRepository.save(lec13);
            lecRepository.save(lec14);
            lecRepository.save(lec15);




    }


    @Test
    void selectDepartment(){
       List<String> lec1 =  lecRepository.selectDepartment();

       for(Integer i =0; i<lec1.size(); i++){

           log.info("lec1: {}", String.valueOf(lec1.get(i)));

       }



    }

    @Test
    @Transactional
    void selectLecName(){

        Lec lecList = lecRepository.findByLecName("데이터베이스 개론");
        log.info("lecList: {}", lecList);

        assertNotNull(lecList);


    }

}
