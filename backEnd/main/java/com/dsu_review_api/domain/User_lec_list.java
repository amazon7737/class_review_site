package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "user_lec_list")
public class User_lec_list {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_number")
    private User user_user_number;

    @Id
    @ManyToOne
    @JoinColumn(name = "lec_id")
    private Lec lec_lec_id;



}
