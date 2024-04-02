package com.dsu_review_api.presentation;

import com.dsu_review_api.application.LecService;
import com.dsu_review_api.infrastructure.config.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
public class LecController {

    private final LecService lecService;

    @GetMapping("/department")  // 학과 조회
    public Api selectDepartment(){
        Api api = new Api();

        List<String> lecList = lecService.selectDepartment();

//        log.info("lec:", lecList);

        api.setStatus("200");
        api.setData(lecList);
        api.setServer("학과 목록 조회입니다." );

        log.info("api: {}", api);

        return api;

    }


}

