package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "review_post")
public class Review_post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @OneToOne
    @JoinColumn(name = "lec_id", nullable = false, unique = true)
    private Lec lec_lec_name;

    @Column(nullable = false, length = 45, unique = false)
    private String post_title;

    @Column(nullable = false, length = 45, unique = false)
    private String post_content;

    @Column(nullable = false, length = 45, unique = false)
    private Integer likes;


    @ManyToOne
    @JoinColumn(name = "user_number", nullable = false, unique = false)
    private User user_user_number;

}
