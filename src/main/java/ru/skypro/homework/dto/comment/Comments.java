package ru.skypro.homework.dto.comment;

import lombok.Data;
import ru.skypro.homework.dto.comment.Comment;

import java.util.List;

@Data
public class Comments {
    private int count;
    private List<Comment> results;

}
