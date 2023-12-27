package com.dsu_review_api.application;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.infrastructure.persistence.ClassListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassListService {


    private final ClassListRepository classListRepository;

    public List<Class_list> selectClassList(){

        List<Class_list> classList = classListRepository.findAll();

        log.info("classList: {}", classList.get(0).getCaptain_image());

        return classList;

    }




}
