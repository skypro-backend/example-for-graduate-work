package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO создания комментария.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateComment {

    private String text; // текст комментария
}
