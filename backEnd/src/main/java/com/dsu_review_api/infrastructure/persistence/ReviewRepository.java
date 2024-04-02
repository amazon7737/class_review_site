package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.domain.Review_post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review_post, Long> {


    // create_time : 날짜 최신순으로 정렬
    @Query("select m from Review_post m  join fetch m.user_user_number and join fetch m.lec_lec_name where m.lec_lec_name.lec_id = :id order by m.createTime desc ")
    List<Review_post> selectfromId(@Param("id") Long id);

//    @Query("select m from Review_post ")
//    findByPostId(@Param("id") Long id);

    @Query("select m from Review_post m where m.lec_lec_name.lec_id = :lec_id and m.user_user_number.user_number = :user_number")
    Optional<Review_post> findByLecAndUser_Number(@Param("lec_id") Long lec_id , @Param("user_number") String user_number);


//    Optional<Review_post> findByLec_lec_nameOrderByCreateTime(@Param("lec_lec_name") String lec_lec_name);

    @Modifying
    @Query("update Review_post p set p.likes = p.likes+1 where p.id = :id")
    int updateLikes(@Param("id") Long id);

    @Query("select m from Review_post m where m.lec_lec_name = :lec_lec_name order by m.likes desc limit 3")
    List<Review_post> findBylec_idOrderByLikes(@Param("lec_lec_name") Lec lec_lec_name);

    @Modifying
    @Query("update Review_post m set m.createTime = :create_time where m.id = :id")
    void updateCreate_timById(@Param("create_time") Date create_time, @Param("id") Long id);




}
