package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;

    public CommentsDto(List<CommentDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
