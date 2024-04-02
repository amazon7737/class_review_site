package com.dsu_review_api.application.dto;

import lombok.Builder;


@Builder
public class UserLogindto {


    private Long user_number;

    private String user_name;

    private String department;


}
