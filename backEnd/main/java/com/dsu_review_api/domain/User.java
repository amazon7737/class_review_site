package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(nullable = false, length = 8, unique = true)
    private String user_number;

    @Column(nullable = false, length = 45, unique = false)
    private String password;

    @Column(nullable = false, length = 45, unique = false)
    private String user_name;

    @Column(nullable = false, length = 45, unique = false)
    private String department;

}
