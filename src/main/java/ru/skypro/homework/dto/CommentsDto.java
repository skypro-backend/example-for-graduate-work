package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
// vse commenty ot odnogo cheloveka
public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;
}
