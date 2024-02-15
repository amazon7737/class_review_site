package com.dsu_review_api.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "image_url")
public class ImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_number;

    @Column(nullable = true, length = 255, unique = false)
    private String image_name;

    @Column(nullable = true, length = 255, unique = false)
    private String image_url;


}
