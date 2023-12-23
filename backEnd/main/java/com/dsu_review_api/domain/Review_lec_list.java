package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "review_lec_list")
public class Review_lec_list {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_number")
    private User user_user_number;


    @Id
    @ManyToOne
    @JoinColumn(name = "lec_id")
    private Lec lec_lec_number;

}
