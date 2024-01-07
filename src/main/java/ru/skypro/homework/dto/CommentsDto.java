package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CommentsDto {
    private int count;
    private List<CommentDto> results= new ArrayList<>();
    public CommentsDto(int count, List<CommentDto> results) {
        this.count = count;
        this.results = results;
    }
}
