package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.dto.CommentDTO;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Comments {
    private final Integer count;
    private final List<CommentDTO> results;
}
