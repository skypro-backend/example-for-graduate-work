package ru.skypro.homework.dto.comment;

import lombok.Data;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
public class Comments {
    private Integer count;
    private List<Comment> results;
}
