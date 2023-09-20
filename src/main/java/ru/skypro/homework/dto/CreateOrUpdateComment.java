package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class CreateOrUpdateComment {
    private String text; //minLength: 8, maxLength: 64 текст комментария
}
