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
                .image_name("moon")
                .image_url("https://uni.dongseo.ac.kr/_Data/UnivProfessor/e06405182b941366a0e6c9b22ecefc50.jpg")
                .build();

        ImageUrl image3 = ImageUrl.builder()
                .image_name("seung")
                .image_url("https://uni.dongseo.ac.kr/_Data/UnivProfessor/538c8e0051b259c70e225f8a113174da.jpg")
                .build();

        ImageUrl image4 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image5 = ImageUrl.builder()
                .image_name("donghyun")
                .image_url("https://uni.dongseo.ac.kr/_Data/UnivProfessor/a23c74601633d973690f9d2fd35f1c9c.jpg")
                .build();


        ImageUrl image6 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image7 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image8 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image9 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image10 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image11 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image12 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image13 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image14 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();

        ImageUrl image15 = ImageUrl.builder()
                .image_name("who")
                .image_url("https://img.freepik.com/premium-photo/card-with-a-question-mark-on-the-human-face_488220-21267.jpg?w=1380")
                .build();


        imageUrlRepository.save(image);
        imageUrlRepository.save(image2);
        imageUrlRepository.save(image3);
        imageUrlRepository.save(image4);
        imageUrlRepository.save(image5);
        imageUrlRepository.save(image6);
        imageUrlRepository.save(image7);
        imageUrlRepository.save(image8);
        imageUrlRepository.save(image9);
        imageUrlRepository.save(image10);
        imageUrlRepository.save(image11);
        imageUrlRepository.save(image12);
        imageUrlRepository.save(image13);
        imageUrlRepository.save(image14);
        imageUrlRepository.save(image15);


    }




}
