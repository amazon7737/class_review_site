package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
