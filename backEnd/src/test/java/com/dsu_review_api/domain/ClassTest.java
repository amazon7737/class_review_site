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
//        Class_list classList = Class_list.builder()
//                .lec_lec_number(lecList.get(1))
//                .professor_prof_id(professorList.get(3))
//                .class_introduction("* 데이터베이스 응용 프로젝트 개발의 전 과정을 경험하고 이해한다.\n" +
//                        "* 요구사항 분석, SQL 사용, ERD 작성 능력, DB 응용 프로그램 개발 능력을 배양한다.\n" +
//                        "* 교재를 통해 습든한 이론을 실제 과제를 통해서 습득한다.")
//                .star_lating(0L)
//                .captain_image(imageList.get(0))
//                .build();
//
//        Class_list classList2 = Class_list.builder()
//                .lec_lec_number(lecList.get(0))
//                .professor_prof_id(professorList.get(1))
//                .class_introduction("컴퓨터시스템을 설계할 수 있는 능력과, 도구 및 프로그래밍 언어를 활용하여 실세계에 존재하는 공학적 문제들을 해결할 수 있는 능력을 배양하기 위하여 프로젝트 계획에서부터 구현까지 소프트웨어 개발의 전 과정을 체계적으로 학습한다.")
//                .star_lating(0L)
//                .captain_image(imageList.get(2))
//                .build();
//
//
//        Class_list classList3 = Class_list.builder()
//                .lec_lec_number(lecList.get(2))
//                .professor_prof_id(professorList.get(6))
//                .class_introduction("국내외 보안 사례(개인 및 기업 정보 유출), 스푸핑, DDos 공격, 네트워크 보안, 웹 보안, 악성코드, 침해 대응 방안 등 관련 자료를 토대로 수업을 진행하며, 칼리리눅스, 백트랙 등 모의 해킹 시뮬레이션을 통해 보안의 중요성을 인지하도록 함.")
//                .star_lating(0L)
//                .captain_image(imageList.get(2))
//                .build();
//
//        Class_list classList4 = Class_list.builder()
//                .lec_lec_number(lecList.get(3))
//                .professor_prof_id(professorList.get(4))
//                .class_introduction("운영체제의 구성요소와 조직형태, 스케쥴링, 프로세스 관리, 메모리 관리, 파일 관리 등의 기본 개념을 파악한다. 또한 컴퓨터 시스템의 여러 자원을 효율적으로 관리하는 운영체제의 개념을 습득한다.")
//                .star_lating(0L)
//                .captain_image(imageList.get(4))
//                .build();
//
//
//        Class_list classList5 = Class_list.builder()
//                .lec_lec_number(lecList.get(4))
//                .professor_prof_id(professorList.get(7))
//                .class_introduction("경찰행정학은 경찰 업무를 체계적이고 전문적으로 수행하도록 돕는 학문이다. 학과명은 경찰 활동을 행정의 일부로 보는 것에서 유래됐다. 경찰행정학과에서는 범죄 현상의 원인·대책, 경찰학, 규범학 등 각종 이론와 함께 무술, 체포술과 같은 무도 기술을 교육한다.")
//                .star_lating(0L)
//                .captain_image(imageList.get(4))
//                .build();
//
//
//        Class_list classList6 = Class_list.builder()
//                .lec_lec_number(lecList.get(5))
//                .professor_prof_id(professorList.get(5))
//                .class_introduction("* 팀별 프로젝트 설계 및 구현 작성\n" +
//                        "* 팀별 프로젝트에 대한 개인평가 방법: 팀평가 결과 * 개인별 기여도(50~100%), 개인별 학습노트 평가")
//                .star_lating(0L)
//                .captain_image(imageList.get(5))
//                .build();
//
//
//        Class_list classList7 = Class_list.builder()
//                .lec_lec_number(lecList.get(6))
//                .professor_prof_id(professorList.get(6))
//                .class_introduction("1. 동서대학교의 핵심역량인 공감소통, 독창성, 문제해결 역량을 함양할 수 있다.\n" +
//                        "2. 대학생활에 필수적인 글쓰기역량뿐만 아니라 기초능력으로서의 말하기, 듣기, 읽기 역량을 향상시킬 수 있다.\n" +
//                        "3. 4차 산업혁명 시대가 요구하는 열린 사고 능력을 바탕으로 창의적인 표현능력과 의사소통 역량을 함양할 수 있다.")
//                .star_lating(0L)
//                .captain_image(imageList.get(6))
//                .build();
//
//        classListRepository.save(classList);
//        classListRepository.save(classList2);
//        classListRepository.save(classList3);
//        classListRepository.save(classList4);
//        classListRepository.save(classList5);
//        classListRepository.save(classList6);
//        classListRepository.save(classList7);

        Class_list classList = Class_list.builder()
                .lec_lec_number(lecList.get(0))
                .professor_prof_id(professorList.get(1))
                .class_introduction(
                      "소프트웨어 공학(-工學, 영어: software engineering)은 소프트웨어의 개발, 운용, 유지보수 등의 생명 주기 전반을 체계적이고 서술적이며 정량적으로 다루는 학문이다; 즉, 공학을 소프트웨어에 적용하는 것이다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(0))
                .build();

        Class_list classList2 = Class_list.builder()
                .lec_lec_number(lecList.get(1))
                .professor_prof_id(professorList.get(2))
                .class_introduction(
                        "① 데이터베이스 기초 이론 : 1장에서 데이터베이스를, 2장에서 DBMS를 소개합니다. 3장에서는 이들을 조합한 데이터베이스 시스템을 소개합니다.\n"
                )
                .star_lating(0L)
                .captain_image(imageList.get(1))
                .build();

        Class_list classList3 = Class_list.builder()
                .lec_lec_number(lecList.get(2))
                .professor_prof_id(professorList.get(3))
                .class_introduction(
                        "운영체제 (Operating System)란? 운영 체제(OS)는 쉽게 이야기해서 하드웨어를 관리하는 프로그램입니다. 운영체제는 컴퓨터를 각종 하드웨어 자원과 소프트웨어 자원을 효율적으로 운영관리함으로써 사용자가 시스템을 이용하는데 편리함을 제공하는 소프트웨어입니다"
                )
                .star_lating(0L)
                .captain_image(imageList.get(2))
                .build();

        Class_list classList4 = Class_list.builder()
                .lec_lec_number(lecList.get(3))
                .professor_prof_id(professorList.get(4))
                .class_introduction(
                        "네트워크를 공부할 때 가장 중요한 점은 새로운 기술의 습득이 아니라 네트워크의 기본을 이해하는 것이다. 기본이 되는 네트워크 이론을 충분히 이해하지 못하면 새로운 기술 또한 이해하기 어렵고 실무 적용도 힘들기 때문이다. 그런 관점에서 『네트워크 개론』 은 네트워크의 기본 이론에 충실하면서 핵심적인 내용만 간추려 담아 누구나 쉽게 이해할 수 있도록 설명하고 있다. 또한 내용의 이해를 돕기 위해 다양한 예제와 그림을 추가했으며, 와이어샤크 실습을 통해 실제 패킷을 분석해봄으로써 네트워크 동작 원리를 쉽게 이해할 수 있도록 구성하였다. 이 책은 대학 강의용 교재로 개발된 도서이며, 연습문제 해답은 제공하지 않고 있다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(3))
                .build();


        Class_list classList5 = Class_list.builder()
                .lec_lec_number(lecList.get(4))
                .professor_prof_id(professorList.get(5))
                .class_introduction(
                        "경영학은 조직행동론, 기업전략, 회계, 금융재무, 마케팅, 영업, 물류, 경제학, 법학, 생산관리, 서비스운영관리, 구매, 품질경영, 기술경영, 녹색경영, 사회학, 심리학, 수학 등 기업의 문제를 해결하기 위해 다양한 학문을 융합시킨 응용과학이다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(4))
                .build();

        Class_list classList6 = Class_list.builder()
                .lec_lec_number(lecList.get(5))
                .professor_prof_id(professorList.get(6))
                .class_introduction(
                        "경찰행정학은 경찰 업무를 체계적이고 전문적으로 수행하도록 돕는 학문이다. 학과명은 경찰 활동을 행정의 일부로 보는 것에서 유래됐다. 경찰행정학과에서는 범죄 현상의 원인·대책, 경찰학, 규범학 등 각종 이론와 함께 무술, 체포술과 같은 무도 기술을 교육한다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(5))
                .build();


        Class_list classList7 = Class_list.builder()
                .lec_lec_number(lecList.get(6))
                .professor_prof_id(professorList.get(7))
                .class_introduction(
                        "건축학(建築學)(건축학과)은 건축과 관련된 학문 분야로서, 건축 및 도시에 관한 역사나 철학을 포함하는 \"건축 및 도시 이론\", 디자인인 \"건축 및 도시 설계\", 환경(친환경, 주거환경, 건조환경)에 관련된 분야 등이 있다. 건축사를 취득할 수 있는 분야는 바로 이 분야이다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(6))
                .build();

        Class_list classList8 = Class_list.builder()
                .lec_lec_number(lecList.get(7))
                .professor_prof_id(professorList.get(8))
                .class_introduction(
                        "재료역학은 응용역학의 분야로서 여러 종류의 하중에 대한 고체의 거동을 다루는 학문이다. 여기서 고체란 보통 공학 재료로 사용되는 축하중(axial load)이나 비틀림을 받는 봉, 굽힘을 받는 보, 압축을 받는 기둥 등을 뜻한다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(7))
                .build();

        Class_list classList9 = Class_list.builder()
                .lec_lec_number(lecList.get(8))
                .professor_prof_id(professorList.get(9))
                .class_introduction(
                    "\n" +
                            "① 미국마케팅협회(1948, AMA : American Marketing Association) : 물적유통이란 생산단계 로부터 소비 또는 이용단계에 이르기까지의 재화 이동 및 취급을 관리하 는 것을 말한다. 물적유통에는 물자유통 이외에 정보유통이 포함\n"
                )
                .star_lating(0L)
                .captain_image(imageList.get(8))
                .build();

        Class_list classList10 = Class_list.builder()
                .lec_lec_number(lecList.get(9))
                .professor_prof_id(professorList.get(10))
                .class_introduction(
                        "\n" +
                                "<일본어기초 2>는 1학기 교재 <일본어기초 1>에 이어서 초급 수준 학습자의 일본어 학습을 위한 교재로, 학습의 용이성과 흥미 유발, 실용성에 주안점을 두고 제작되었다. 구성면에서는 회화문을 중심으로 여기에 등장하는 새로운 단어와 기본적인 문법 사항 및 문형의 학습에 중점을 두었고, 덧붙여 기본 한자의 학습과 일본 사회에 대한 기초적인 이해를 위한 코너를 마련하여 학습자들이 일본어 공부에 재미를 더할 수 있게 하였다.\n"
                )
                .star_lating(0L)
                .captain_image(imageList.get(9))
                .build();

        Class_list classList11 = Class_list.builder()
                .lec_lec_number(lecList.get(10))
                .professor_prof_id(professorList.get(11))
                .class_introduction(
                    "현대인에게 있어 자연은 고향이다. 파란하늘, 두둥실 떠가는 뭉게구름, 맑고 차디찬 계곡물, 반짝이는 나뭇잎. 이 모든 것들을 생각하는 것만으로도 우리는 마음의 안정과 생활의 활력을 느끼게 된다. 이러한 것이 생각이나 상상에서 끝나는 것이 아니고 혼자 혹은 여럿이 준비.계획하여 마음의 고향과도 같은 자연을 향해 떠나는 일. 일상에서 벗어나 자연과 하나로 어우러져 ‘나’를 되돌아 보는 일, 생활중에 누적된 심신의 피로를 씻고 새로운 모습으로 태어나는 일, 바로 이것이 캠프인 것이다. 캠프를 기획하고 있을 때 무엇을 준비해야 할지, 어디로 가야할지, 또 가서는 무엇을 하며 재내야 할지 정리할 수가 없다면 이제 각 단원들을 통해 그 방법을 터득해 보기로 하자."
                )
                .star_lating(0L)
                .captain_image(imageList.get(10))
                .build();

        Class_list classList12 = Class_list.builder()
                .lec_lec_number(lecList.get(11))
                .professor_prof_id(professorList.get(12))
                .class_introduction(
                        "호텔에서 투숙 고객의 편의를 위해 무료로 제공하는 객실 내 비품. 어메니티에는 샴푸, 비누 등의 바디용품, 배스 가운, 수건, 헤어드라이어, 커피 또는 티, 생수, 면봉, 화장솜 등이 있다. 어메니티들은 매일 새것으로 교체되거나 모자란 만큼 채워진다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(11))
                .build();

        Class_list classList13 = Class_list.builder()
                .lec_lec_number(lecList.get(12))
                .professor_prof_id(professorList.get(13))
                .class_introduction(
                    "습 강의는 담당 조교들의 지도로 이론 강의에 배운 개념과 원리를 실제 문제에 적용해본다. 실습 문제가 요구하는 프로그래밍이 모두 이론 강의에서 다룬 개념과 원리가 ..."
                )
                .star_lating(0L)
                .captain_image(imageList.get(12))
                .build();


        Class_list classList14 = Class_list.builder()
                .lec_lec_number(lecList.get(13))
                .professor_prof_id(professorList.get(13))
                .class_introduction(
                    "C언어의 정신은 C99 Rationale에서 다음과 같이 묘사하고 있다.\n" +
                            "프로그래머를 믿어라. (Trust the programmer)\n"
                )
                .star_lating(0L)
                .captain_image(imageList.get(13))
                .build();

        Class_list classList15 = Class_list.builder()
                .lec_lec_number(lecList.get(14))
                .professor_prof_id(professorList.get(13))
                .class_introduction(
                "\n" +
                        "이 모든 것들이 디자인의 요소가 되고 있다는 이유로 컴퓨터 게임 디자인의 정의가 모호하다. 컴퓨터 게임 개발 복잡함은 이러한 요소가 복잡하게 얽히고 있기 때문이다. 이 때문에 한번에 확실한 결단이 제약된다. 이러한 상황은 정도의 차이는 있지만 다른 게임 디자인에도 존재한다. 예를 들면 보드게임의 경우 디자이너는 게임을 보다 즐겁게 할 수 있도록 기획하는 한편 대량생산을 하여 더욱 높은 이익이 존재하도록 해야 한다."
                )
                .star_lating(0L)
                .captain_image(imageList.get(14))
                .build();

        classListRepository.save(classList);
        classListRepository.save(classList2);
        classListRepository.save(classList3);
        classListRepository.save(classList4);
        classListRepository.save(classList5);
        classListRepository.save(classList6);
        classListRepository.save(classList7);
        classListRepository.save(classList8);
        classListRepository.save(classList9);
        classListRepository.save(classList10);
        classListRepository.save(classList11);
        classListRepository.save(classList12);
        classListRepository.save(classList13);
        classListRepository.save(classList14);
        classListRepository.save(classList15);


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

    @Test
    @Transactional
    void fetchJoinTest(){

        List<Class_list> classLists = classListRepository.findByClass();

        log.info("classList: {}", classLists.get(0).getLec_lec_number().getLec_name());


    }




}
