package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    private int count = 0;
    private List<CommentDTO> results = List.of(
            new CommentDTO()
    );

}
