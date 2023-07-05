package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {
    private int authorId;
    private String authorImage;
    private String authorFirstName;
    private LocalDate createAt;
    private int pk;
    private String text;


}
