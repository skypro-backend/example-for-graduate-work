package ru.skypro.homework.dto.model_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
      private Integer count; // количество комментариев в списке
      private List <CommentDto> results; // список комментариев
}
