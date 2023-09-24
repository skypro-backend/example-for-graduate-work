package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс DTO для передачи информации о комментарии
 */
@Data
public class CommentDto {
      private Integer pk; // идентификатор комментария
      private Long author; // идентификатор автора комментария
      private String authorFirstName;  //имя автора комментария
      private String authorImage;  //ссылка на аватар автора комментария
      private LocalDateTime createdAt; // дата и время создания комментария
      private String text;  //текст комментария
}
