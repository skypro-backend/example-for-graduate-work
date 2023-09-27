package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Класс, представляющий комментарий к объявлению.
 */
@Entity // сущность
@Table (name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
      private Integer pk; // идентификатор комментария
      private Long author; // идентификатор автора комментария
      private String authorFirstName;  //имя автора комментария
      private String authorImage;  //ссылка на аватар автора комментария
      private LocalDateTime createdAt; // дата и время создания комментария
      private String text;  //текст комментария
}
