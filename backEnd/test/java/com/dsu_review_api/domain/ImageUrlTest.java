package com.dsu_review_api.domain;

import com.dsu_review_api.infrastructure.persistence.ImageUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageUrlTest {

    @Autowired
    ImageUrlRepository imageUrlRepository;

    @Test
    void resetImageUrl(){
        imageUrlRepository.deleteAll();

    }

    @Test
    void saveImage(){
        // 7개

        ImageUrl image = ImageUrl.builder()
                        .image_name("dasoo")
                        .image_url("https://uni.dongseo.ac.kr/_Data/UnivProfessor/27e8a69ed1945e0deaea0ecd1f59ed15.jpg")
                        .build();

        ImageUrl image2 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        ImageUrl image3 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        ImageUrl image4 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        ImageUrl image5 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        ImageUrl image6 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        ImageUrl image7 = ImageUrl.builder()
                .image_name("")
                .image_url("/")
                .build();

        imageUrlRepository.save(image);
        imageUrlRepository.save(image2);
        imageUrlRepository.save(image3);
        imageUrlRepository.save(image4);
        imageUrlRepository.save(image5);
        imageUrlRepository.save(image6);
        imageUrlRepository.save(image7);

    }




}
