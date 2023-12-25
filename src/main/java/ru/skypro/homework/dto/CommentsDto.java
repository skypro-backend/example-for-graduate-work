package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {
    private int count;
    private List<CommentDto> results;

    public CommentsDto(int count, List<CommentDto> results) {
        this.count = count;
        this.results = results;
    }

}
