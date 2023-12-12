package ru.skypro.homework.dto;

import lombok.Data;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
public class CommentsDTO {
    Integer count;
    List<Comment> results;
}
