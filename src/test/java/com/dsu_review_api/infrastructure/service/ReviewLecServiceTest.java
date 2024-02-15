package com.dsu_review_api.infrastructure.service;

import com.dsu_review_api.application.ReviewLecService;
import com.dsu_review_api.application.ReviewPostService;
import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Review_lec_list;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.infrastructure.persistence.ClassListRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRecordRepository;
import com.dsu_review_api.infrastructure.persistence.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class ReviewLecServiceTest {

    @Autowired
    ReviewLecService reviewLecService;

    @Autowired
    ReviewRecordRepository reviewRecordRepository;

    @Autowired
    ClassListRepository classListRepository;

    @Transactional
    @Test
    void findByClassNumberTest(){

        List<Review_lec_list> record = reviewRecordRepository.findAll();

        Class_list list = classListRepository.findById(record.get(0).getNumber().getClass_number()).get();

        Class_list classList = Class_list.builder()
                .class_number(list.getClass_number())
                .class_introduction(list.getClass_introduction())
                .star_lating(list.getStar_lating())
                .professor_prof_id(list.getProfessor_prof_id())
                .lec_lec_number(list.getLec_lec_number())
                .captain_image(list.getCaptain_image())
                .build();

        List<Review_lec_list> result = reviewLecService.findByClassNumber(classList);

        assertNotNull(result);

    }

    @Transactional
    @Test
    void saveRecordTest(){

        List<Review_lec_list> list = reviewRecordRepository.findAll();

        Review_lec_list record = Review_lec_list.builder()
                .post_id(list.get(0).getPost_id())
                .id(list.get(0).getId())
                .number(list.get(0).getNumber())
                .build();


    }
}
