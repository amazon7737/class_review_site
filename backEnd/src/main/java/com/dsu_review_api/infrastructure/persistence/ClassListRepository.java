package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Lec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassListRepository extends JpaRepository<Class_list, Long> {


    @Query("select m from Class_list m  where m.lec_lec_number.lec_id = :lec_id")
    Class_list findByLecId(@Param("lec_id") Long lec_id);


    @Query("select m from Class_list m join fetch m.lec_lec_number and join fetch m.professor_prof_id")
    List<Class_list> findByClass();

    @Query("select m from Class_list m join fetch m.lec_lec_number and join fetch m.professor_prof_id join fetch m.captain_image where m.lec_lec_number.lec_id = :lec_id")
    Class_list findByIdFetchJoin(@Param("lec_id") Long lec_id);






}
