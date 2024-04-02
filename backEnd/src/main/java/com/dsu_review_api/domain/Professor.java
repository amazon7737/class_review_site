package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prof_id;

    @Column(nullable = false, length = 45, unique = false)
    private String prof_name;

    @Column(nullable = false, length = 45, unique = false)
    private String department;

}
