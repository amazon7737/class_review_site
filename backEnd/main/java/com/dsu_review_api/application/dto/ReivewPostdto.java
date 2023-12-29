package com.dsu_review_api.application.dto;

import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.domain.Lec;
import com.dsu_review_api.domain.Review_post;
import com.dsu_review_api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReivewPostdto {


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        private String post_content;

        private String post_title;

        private User user_number;

        private Lec lec_lec_name;



        public Review_post toEntity(){

            Review_post post = Review_post.builder()
                    .post_title(post_title)
                    .post_content(post_content)
                    .lec_lec_name(lec_lec_name)
                    .user_user_number(user_number)
                    .build();

            return post;
        }



    }

}
