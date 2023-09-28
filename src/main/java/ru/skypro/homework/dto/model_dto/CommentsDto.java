package ru.skypro.homework.dto.model_dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Data
public class CommentsDto {
      private Integer count; // количество комментариев в списке
      private List <Comment> results; // список комментариев
}
