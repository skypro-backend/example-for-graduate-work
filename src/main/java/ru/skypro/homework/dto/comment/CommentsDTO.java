package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
    private int count;
    private List<CommentDTO> results;

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }
}
