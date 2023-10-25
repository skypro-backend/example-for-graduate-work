package ru.skypro.homework.dto;


import lombok.Data;
import java.time.Instant;


@Data
public class CommentInfoDTO {

        private String userName;


        private String authorImage;


        private String firstName;


        private Instant createdAt;


        private Long pk;


        private String text;
    }
