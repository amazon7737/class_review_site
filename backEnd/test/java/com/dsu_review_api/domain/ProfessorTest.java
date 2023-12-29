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
    void resetProfessor(){
        professorRepository.deleteAll();
    }

    @Test
    void addProfessor(){
        List<String> profListByName = new ArrayList<>(List.of("문미경", "박승민", "조대수", "김종우", "김동현", "배정수", "인문학과 어떤교수"));
        List<String> department = new ArrayList<>(List.of("소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과", "소프트웨어학과" , "소프트웨어학과", "소프트웨어학과", "경영학과"));

        for(Integer i = 0; i<profListByName.size(); i++){
            Professor profData = Professor.builder()
                            .prof_name(profListByName.get(i))
                            .department(department.get(i))
                            .build();

            professorRepository.save(profData);
        }

    }

}
