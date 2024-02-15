package com.dsu_review_api.presentation;

import com.dsu_review_api.application.ClassListService;
import com.dsu_review_api.domain.Class_list;
import com.dsu_review_api.infrastructure.config.Api;
import com.dsu_review_api.infrastructure.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
public class ClassController {

    private final ClassListService classListService;
    private final JwtUtil jwtUtil;


    @GetMapping("/class") // 클래스목록 전체 조회
//    public Api selectClassList() throws NullPointerException{
    public Api selectClassList(@RequestHeader("Authorization") String token) throws NullPointerException{
        Api api = new Api();

        try{
            jwtUtil.isValidToken(token);
            jwtUtil.getUsernameFromToken(token);
        }catch (Exception e){
            e.printStackTrace();

            api.setStatus("401");
            api.setServer("로그인이 유효하지 않습니다.");
            api.setData(null);
            return api;
        }

        List<Class_list> classList = classListService.selectClassList();

        JSONArray resultArray = new JSONArray();

        for(Integer i = 0; i< classList.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("class_number", classList.get(i).getClass_number());
            jsonObject.put("professor", classList.get(i).getProfessor_prof_id().getProf_name());
            jsonObject.put("lec_name", classList.get(i).getLec_lec_number().getLec_name());
            jsonObject.put("lec_type", classList.get(i).getLec_lec_number().getLec_type());
            jsonObject.put("department", classList.get(i).getLec_lec_number().getDepartment());
            jsonObject.put("class_introduction", classList.get(i).getClass_introduction());
            jsonObject.put("star_lating", classList.get(i).getStar_lating());
//        jsonObject.put("image_url", classList.get(i).getCaptain_image().getImage_url());


            resultArray.add(jsonObject);

        }

//        log.info("result : {}", resultArray);

        api.setStatus("200");
        api.setServer("클래스 목록입니다");
        api.setData(resultArray);

        return api;
    }
}
