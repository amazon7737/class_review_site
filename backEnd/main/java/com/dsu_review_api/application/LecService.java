package com.dsu_review_api.application;

import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.infrastructure.persistence.LecRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Lec findByLec_name(String lec_name){
        return lecRepository.findByLecName(lec_name);
    }

    public Optional<Lec> findById(Long id){
        return lecRepository.findById(id);
    }
}
