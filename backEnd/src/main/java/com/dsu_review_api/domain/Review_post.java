package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "review_post",uniqueConstraints = @UniqueConstraint(columnNames = {"lec_id", "user_number"}))
public class Review_post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lec_id", nullable = false, unique = false)
    private Lec lec_lec_name;

    @Column(nullable = false, length = 45, unique = false)
    private String post_title;

    @Column(nullable = false, length = 45, unique = false)
    private Long star_lating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_number", nullable = false, unique = false)
    private User user_user_number;


    @Column(nullable = false, length = 255, unique = false)
    private String post_content;

    @Column(nullable = false, length = 45, unique = false)
    private Integer likes;

    public void modify(String post_title, String post_content){
        this.post_title = post_title;
        this.post_content = post_content;
    }

//    public User updateCreateDate(){
//        this.onPreUpdate();
//        return this;
//    }
}
