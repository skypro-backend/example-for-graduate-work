package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CommentsDto {
    private Integer count; //($int32) общее кол-во комментариев
    private List<CommentDto> results;
    public List<CommentDto> fromCommentsList(List<Comment> commentList) {
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment comment : commentList) {
            commentsDto.add(CommentDto.fromComment(comment));
        }
        return commentsDto;
    }
}
