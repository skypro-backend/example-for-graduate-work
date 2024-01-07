package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateComment {
    private String text; //minLength: 8 maxLength: 64 текст комментария
}
