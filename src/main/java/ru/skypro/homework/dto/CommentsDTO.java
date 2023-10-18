package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class CommentsDTO {
    private List<CommentDTO> results;
    private int count;
}
