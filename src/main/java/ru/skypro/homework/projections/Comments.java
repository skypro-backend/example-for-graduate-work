package ru.skypro.homework.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.CommentDTO;

import java.util.List;

@Data
@NoArgsConstructor
public class Comments {
    private List<CommentDTO> results;
    private int count;

    public Comments(List<CommentDTO> results, int count) {
        this.results = results;
        this.count = count++;
    }
}
