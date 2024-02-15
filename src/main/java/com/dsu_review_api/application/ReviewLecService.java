package com.dsu_review_api.application;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Review_lec_list;
import com.dsu_review_api.infrastructure.persistence.ReviewRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor
public class ReviewLecService {

    private  final ReviewRecordRepository reviewRecordRepository;

//    public Review_lec_list selectReviewPostAndReviewLecList(Long post_id){
//        return reviewRecordRepository.PostList(post_id);
//    }

    @Transactional
    public List<Review_lec_list> findByClassNumber(Class_list Class_number){
        List<Review_lec_list> record = reviewRecordRepository.findByNumber(Class_number);
//        log.info("record!!: {}", record.get(0).getPost_id().getLec_lec_name());


        return record;

    }

    @Transactional
    public void saveRecord(Review_lec_list record){

        reviewRecordRepository.save(record);
    }
}
