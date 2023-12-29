package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {
}
