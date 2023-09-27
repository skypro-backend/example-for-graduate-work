package ru.skypro.homework.dto.model_dto;

import lombok.Data;

/**
 * Класс, представляющий DTO (Data Transfer Object) для создания или обновления комментария к объявлению.
 */
@Data
public class CreateOrUpdateCommentDto {
      private String text; // текст комментария
}
