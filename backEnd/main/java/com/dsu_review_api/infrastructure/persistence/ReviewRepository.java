package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Review_post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review_post, Long> {

}
