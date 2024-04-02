package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "review_lec_list")
public class Review_lec_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Review_post post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_number" , nullable = false, unique = false)
    private Class_list number;


}
