package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Lec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassListRepository extends JpaRepository<Class_list, Long> {

    @Query("select m.class_number AS class_number," +
            "m.lec_lec_number.lec_name AS lec_name,"+
            "m.class_introduction AS class_introduction, " +
            "m.star_lating AS star_lating,"+
            "m.professor_prof_id.prof_name AS prof_name," +
            "m.professor_prof_id.department AS department "+
            "from Class_list m ")
    List<Class_list> findByClassList();


    @Query("select m from Class_list m where m.lec_lec_number.lec_id = :lec_id")
    Class_list findByLecId(@Param("lec_id") Long lec_id);



}
