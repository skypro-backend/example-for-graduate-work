package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {
    private Long count;
    private List<CommentsDto> results;
}
