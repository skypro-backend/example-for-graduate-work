package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentsDto {
    private int count;
    private List<CommentDto> results;

    public CommentsDto(List<CommentDto> list) {
        count = list == null ? 0 : list.size();
        results = list;
    }
}