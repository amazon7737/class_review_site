package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Class_list;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassListRepository extends JpaRepository<Class_list, Long> {

}
