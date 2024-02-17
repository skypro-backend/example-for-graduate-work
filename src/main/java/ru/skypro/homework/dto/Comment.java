package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.UserEntity;

@Data

public class Comment {

    private Integer id;

    private String text;

    private Long createdAT;

    private UserEntity author;

    private AdEntity ad;
}