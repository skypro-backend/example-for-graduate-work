package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Класс DTO для передачи информации о комментарии
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
      private Integer pk; // идентификатор комментария
      private Integer author; // идентификатор автора комментария
      private String authorImage;  //ссылка на аватар автора комментария
      private String authorFirstName;  //имя автора комментария
      private long createdAt;// дата и время создания комментария

      @NotBlank
      @Size (min = 8)
      private String text;  //текст комментария
}
