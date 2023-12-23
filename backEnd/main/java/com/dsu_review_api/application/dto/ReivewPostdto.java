package com.dsu_review_api.application.dto;

import com.dsu_review_api.domain.User;
import lombok.Builder;

@Builder
public class ReivewPostdto {

    private Long post_id;

    private String post_title;

    private String post_content;

    private String lec_lec_name;

    private Integer likes;

    private User user_user_number;

}
