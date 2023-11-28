package ru.skypro.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.CommentDto;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    private Integer count;
    private List<CommentDto> results;
}
