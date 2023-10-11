package ru.skypro.homework.dto;


import lombok.Data;
import java.time.Instant;


@Data
public class CommentInfoDTO {

        private String userName;


        private Long avatarId;


        private String firstName;


        private Instant timeStamp;


        private Long pk;


        private String text;
    }
