package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
public class CommentsDTO {

    private Integer count;
    private List<CommentDTO> results;

}
