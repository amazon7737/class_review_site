package com.dsu_review_api.domain;
import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "lec_id")
    @ManyToOne
    private Lec lec_lec_number;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Professor professor_prof_id;
}
