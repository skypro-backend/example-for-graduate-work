package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO создания или обновления комментария
 */

/**
 * Свойства:
 * 1) text - текст комментария
 */
@Data
public class CreateOrUpdateCommentDTO {

    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}