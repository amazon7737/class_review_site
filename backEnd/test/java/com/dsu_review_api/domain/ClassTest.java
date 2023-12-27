package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.ClassListRepository;
import com.dsu_review_api.infrastructure.persistence.ImageUrlRepository;
import com.dsu_review_api.infrastructure.persistence.LecRepository;
import com.dsu_review_api.infrastructure.persistence.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ClassTest {

    @Autowired
    ClassListRepository classListRepository;

    @Autowired
    LecRepository lecRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ImageUrlRepository imageUrlRepository;


    @Test
    void resetClassList(){
        classListRepository.deleteAll();

    }


    @Test
//    @Transactional
    void ClassInputDataTest(){
        // 교수 데이터 불러오기
        List<Lec> lecList = lecRepository.findAll();

        // 강의 데이터 불러오기
        List<Professor> professorList = professorRepository.findAll();

        // 대표 이미지 데이터 불러오기
        List<ImageUrl>  imageList = imageUrlRepository.findAll();

//        for(Integer i = 0; i< lecList.size(); i++){
//            log.info("lecList: {}" , lecList.get(i));
//        }
//        for(Integer i = 0; i< professorList.size(); i++){
//            log.info("professorList: {}", professorList.get(i));
//        }
//
        // 데이터 서로 붙이기(7개) => 인덱스 안맞음
//        for(Integer i = 0; i< professorList.size(); i++){
//            for(Integer j = 0; j < lecList.size(); j++){
//            if(lecList.get(i).getDepartment() == professorList.get(j).getDepartment()){
//                Class_list classList = Class_list.builder()
//                    .lec_lec_number(lecList.get(j))
//                    .professor_prof_id(professorList.get(i))
//                    .build();
//                classListRepository.save(classList);
//            }
//            }
//        }

        // 데이터 7개
        Class_list classList = Class_list.builder()
                .lec_lec_number(lecList.get(0))
                .professor_prof_id(professorList.get(0))
                .class_introduction("* 데이터베이스 응용 프로젝트 개발의 전 과정을 경험하고 이해한다.\n" +
                        "* 요구사항 분석, SQL 사용, ERD 작성 능력, DB 응용 프로그램 개발 능력을 배양한다.\n" +
                        "* 교재를 통해 습든한 이론을 실제 과제를 통해서 습득한다.")
                .star_lating(0L)
                .captain_image(imageList.get(0))
                .build();

        Class_list classList2 = Class_list.builder()
                .lec_lec_number(lecList.get(1))
                .professor_prof_id(professorList.get(1))
                .class_introduction("컴퓨터시스템을 설계할 수 있는 능력과, 도구 및 프로그래밍 언어를 활용하여 실세계에 존재하는 공학적 문제들을 해결할 수 있는 능력을 배양하기 위하여 프로젝트 계획에서부터 구현까지 소프트웨어 개발의 전 과정을 체계적으로 학습한다.")
                .star_lating(0L)
                .captain_image(imageList.get(1))
                .build();


        Class_list classList3 = Class_list.builder()
                .lec_lec_number(lecList.get(2))
                .professor_prof_id(professorList.get(2))
                .class_introduction("국내외 보안 사례(개인 및 기업 정보 유출), 스푸핑, DDos 공격, 네트워크 보안, 웹 보안, 악성코드, 침해 대응 방안 등 관련 자료를 토대로 수업을 진행하며, 칼리리눅스, 백트랙 등 모의 해킹 시뮬레이션을 통해 보안의 중요성을 인지하도록 함.")
                .star_lating(0L)
                .captain_image(imageList.get(2))
                .build();

        Class_list classList4 = Class_list.builder()
                .lec_lec_number(lecList.get(3))
                .professor_prof_id(professorList.get(3))
                .class_introduction("운영체제의 구성요소와 조직형태, 스케쥴링, 프로세스 관리, 메모리 관리, 파일 관리 등의 기본 개념을 파악한다. 또한 컴퓨터 시스템의 여러 자원을 효율적으로 관리하는 운영체제의 개념을 습득한다.")
                .star_lating(0L)
                .captain_image(imageList.get(3))
                .build();


        Class_list classList5 = Class_list.builder()
                .lec_lec_number(lecList.get(4))
                .professor_prof_id(professorList.get(4))
                .class_introduction("프로그래밍 언어를 사용하여 데이터 처리를 할 수 있도록 한다. R을 사용하여 코딩할 수 있도록 하며 데이터 처리, 분석에 흥미를 가질 수 있도록 한다.")
                .star_lating(0L)
                .captain_image(imageList.get(4))
                .build();


        Class_list classList6 = Class_list.builder()
                .lec_lec_number(lecList.get(5))
                .professor_prof_id(professorList.get(5))
                .class_introduction("* 팀별 프로젝트 설계 및 구현 작성\n" +
                        "* 팀별 프로젝트에 대한 개인평가 방법: 팀평가 결과 * 개인별 기여도(50~100%), 개인별 학습노트 평가")
                .star_lating(0L)
                .captain_image(imageList.get(5))
                .build();


        Class_list classList7 = Class_list.builder()
                .lec_lec_number(lecList.get(6))
                .professor_prof_id(professorList.get(6))
                .class_introduction("1. 동서대학교의 핵심역량인 공감소통, 독창성, 문제해결 역량을 함양할 수 있다.\n" +
                        "2. 대학생활에 필수적인 글쓰기역량뿐만 아니라 기초능력으로서의 말하기, 듣기, 읽기 역량을 향상시킬 수 있다.\n" +
                        "3. 4차 산업혁명 시대가 요구하는 열린 사고 능력을 바탕으로 창의적인 표현능력과 의사소통 역량을 함양할 수 있다.")
                .star_lating(0L)
                .captain_image(imageList.get(6))
                .build();

        classListRepository.save(classList);
        classListRepository.save(classList2);
        classListRepository.save(classList3);
        classListRepository.save(classList4);
        classListRepository.save(classList5);
        classListRepository.save(classList6);
        classListRepository.save(classList7);




    }

    @Test
    void selectClassList(){
        Optional<Class_list> classList = classListRepository.findById(8L);
//        List<Class_list> classList = classListRepository.findAll();

        log.info("classList : {}", classList.get().getClass_number());
        log.info("classList : {}", classList.get().getLec_lec_number());
        log.info("classList : {}", classList.get().getProfessor_prof_id());
//        log.info("classList : {}", classList.get(0).getProfessor_prof_id());


    }




}
