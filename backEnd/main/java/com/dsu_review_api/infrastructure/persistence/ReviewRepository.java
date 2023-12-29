package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Review_post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review_post, Long> {


    @Query("select m from Review_post m where m.lec_lec_name.lec_id = :id")
    List<Review_post> selectfromId(@Param("id") Long id);

//    @Query("select m from Review_post ")
//    findByPostId(@Param("id") Long id);

}
