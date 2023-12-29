package com.dsu_review_api.application;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.infrastructure.persistence.ClassListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassListService {


    private final ClassListRepository classListRepository;


    @Transactional
    public List<Class_list> selectClassList(){

        log.info("!!! : findAll");

        List<Class_list> classList = classListRepository.findAll();

//        List<Class_list> classList = classListRepository.findByClassList();

//        log.info("classList: {}", classList.get(0).getCaptain_image());

        return classList;

    }



    @Transactional
    public Class_list selectClassListByParams(Long id){

        Class_list classList = classListRepository.findById(id).orElseThrow(()
                ->  new IllegalArgumentException("해당 강의가 없습니다."));

        return classList;
    }

    @Transactional
    public Class_list selectClass_list(Long lec_id){
        return classListRepository.findByLecId(lec_id);
    }






}
