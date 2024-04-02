package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Lec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecRepository extends JpaRepository<Lec, Long> {


    // 강의목록에서 중복된 학과 제거 후 전체 조회
    @Query("select distinct m.department from Lec m")
    List<String> selectDepartment();


    @Query("select m from Lec m where m.lec_name = :lec_name")
    Lec findByLecName(@Param("lec_name") String lec_name);


}
