package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.Post_likes;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PostLikesRepository extends JpaRepository<Post_likes, Long> {


    Post_likes findByUserAndPost(User user, Review_post post);

    @Modifying
    @Query("delete from Post_likes m where m.post.id = :post_id")
    void deleteAllByPostId(@Param("post_id") Long post_id);
}
