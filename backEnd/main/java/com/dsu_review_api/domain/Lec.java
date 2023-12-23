package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "lec")
public class Lec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lec_id;

    @Column(nullable = false, length = 45, unique = false)
    private String lec_name;

    @Column(nullable = false, length = 45, unique = false)
    private String department;

    @Column(nullable = false, length = 45, unique = false)
    private String lec_type;


}
