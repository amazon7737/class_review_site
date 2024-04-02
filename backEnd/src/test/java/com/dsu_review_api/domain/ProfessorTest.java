package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ProfessorTest {


    @Autowired
    ProfessorRepository professorRepository;

    @Test
    void resetProfessor() {
        professorRepository.deleteAll();
    }

    @Test
    void addProfessor() {
//        List<String> profListByName = new ArrayList<>(List.of("문미경", "박승민", "조대수", "박준석", "김동현", "배정수", "인문학과 어떤교수", "캐트", "서재희", "문재영", "김흥배", "류성경", "장지경", "최원일", "권예경"));
//        List<String> department = new ArrayList<>(List.of("소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "경영학과", "시각디자인학과", "경영학과", "경영학과", "경영학과", "경영학과", "경영학과", "경영학과", "경영학과"));

//        log.info("dd : {}", profListByName.size());

//        for(Integer i = 0; i<profListByName.size(); i++){
//            Professor profData = Professor.builder()
//                            .prof_name(profListByName.get(i))
//                            .department(department.get(i))
//                            .build();
//
//            professorRepository.save(profData);


        Professor profData = Professor.builder()
                .prof_name("문미경")
                .department("소프트웨어학과")
                .build();

        Professor profData2 = Professor.builder()
                .prof_name("조대수")
                .department("소프트웨어학과")
                .build();

        Professor profData3 = Professor.builder()
                .prof_name("박준석")
                .department("소프트웨어학과")
                .build();

        Professor profData4 = Professor.builder()
                .prof_name("박승민")
                .department("소프트웨어학과")
                .build();


        Professor profData5 = Professor.builder()
                .prof_name("문재영")
                .department("경영학과")
                .build();


        Professor profData6 = Professor.builder()
                .prof_name("박외병")
                .department("경찰행정학과")
                .build();


        Professor profData7 = Professor.builder()
                .prof_name("전대한")
                .department("건출학과")
                .build();


        Professor profData8 = Professor.builder()
                .prof_name("이미영")
                .department("국제물류학과")
                .build();


        Professor profData9 = Professor.builder()
                .prof_name("안정숙")
                .department("일본어학과")
                .build();

        Professor profData10 = Professor.builder()
                .prof_name("제점숙")
                .department("캠퍼스아시아학과")
                .build();

        Professor profData11 = Professor.builder()
                .prof_name("홍정화")
                .department("호텔경영학과")
                .build();

        Professor profData12 = Professor.builder()
                .prof_name("류도상")
                .department("광고홍보학과")
                .build();

        Professor profData13 = Professor.builder()
                .prof_name("주우석")
                .department("게임학과")
                .build();


        Professor profData14 = Professor.builder()
                .prof_name("윤태수")
                .department("게임학과")
                .build();



                professorRepository.save(profData);
                professorRepository.save(profData2);
                professorRepository.save(profData3);
                professorRepository.save(profData4);
                professorRepository.save(profData5);
                professorRepository.save(profData6);
                professorRepository.save(profData7);
                professorRepository.save(profData8);
                professorRepository.save(profData9);
                professorRepository.save(profData10);
                professorRepository.save(profData11);
                professorRepository.save(profData12);
                professorRepository.save(profData13);
                professorRepository.save(profData14);


    }

}
//}
