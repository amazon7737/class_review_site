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


    @Transactional(readOnly = true)
    public List<Class_list> selectClassList(){


//        List<Class_list> classList = classListRepository.findAll();
        List<Class_list> classList = classListRepository.findByClass();

//        List<Class_list> classList = classListRepository.findByClassList();

//        log.info("classList: {}", classList.get(0).getCaptain_image());

        return classList;

    }



    // readOnly 설정으로 조회속도 개선
    @Transactional(readOnly = true)
    public Class_list selectClassListByParams(Long id){

//        Class_list classList = classListRepository.findById(id).orElseThrow(()
//                ->  new IllegalArgumentException("해당 강의가 없습니다."));
        Class_list classList = classListRepository.findByIdFetchJoin(id);
        if(classList.equals(null)){
            new IllegalArgumentException("해당 강의가 없습니다.");
        }

        return classList;
    }

    @Transactional(readOnly = true)
    public Class_list selectClass_list(Long lec_id){

        return classListRepository.findByLecId(lec_id);
    }

    // 평균 평점 업데이트
    @Transactional
    public void updateStar(Long lec_id, Long newStar){

        Class_list classList = classListRepository.findById(lec_id).get();

        if(classList.getStar_lating() == 0 ){
            Long updateStar = (newStar + classList.getStar_lating());

            Class_list update = Class_list.builder()
                    .lec_lec_number(classList.getLec_lec_number())
                    .class_number(classList.getClass_number())
                    .star_lating(updateStar)
                    .class_introduction(classList.getClass_introduction())
                    .professor_prof_id(classList.getProfessor_prof_id())
                    .captain_image(classList.getCaptain_image())
                    .build();
            classListRepository.save(update);

        }else{
            Long updateStar = (newStar + classList.getStar_lating())/2;

            Class_list update = Class_list.builder()
                    .lec_lec_number(classList.getLec_lec_number())
                    .class_number(classList.getClass_number())
                    .star_lating(updateStar)
                    .class_introduction(classList.getClass_introduction())
                    .professor_prof_id(classList.getProfessor_prof_id())
                    .captain_image(classList.getCaptain_image())
                    .build();
            classListRepository.save(update);
        }






    }








}
