package com.dsu_review_api.domain;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "class_list")
public class Class_list {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_number;

    @Column(nullable = true, length = 255, unique = false)
    private String class_introduction;

    @Column(nullable = true, length = 45, unique = false)
    private Long star_lating;

    @JoinColumn(name = "lec_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lec lec_lec_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    private Professor professor_prof_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_number")
    private ImageUrl captain_image;


}
