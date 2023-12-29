package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Review_lec_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRecordRepository extends JpaRepository<Review_lec_list, Long> {

//    @Query("select m.id from Review_lec_list m where m.lec_lec_id.lec_id = :lec_id and m.user_user_number.user_number = :user_number")
//    Long findByLecIdAndUserNumber(Long lec_id , String user_number);

    List<Review_lec_list> findByNumber(@Param("number")Class_list class_number);



}
