package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Comments {

    private Integer count;
    private List<CommentDTO> results;

}
