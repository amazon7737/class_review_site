package com.dsu_review_api.application;

import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.infrastructure.persistence.LecRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LecService {

    private final LecRepository lecRepository;


    public List<Lec> selectLec(){
        return lecRepository.findAll();
    }

    public List<String> selectDepartment(){

        List<String> lec1 =  lecRepository.selectDepartment();

//        log.info("서비스안: {}", lec1);

        return (lec1);



    }
}
