package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.entities.CommentEntity;

@AllArgsConstructor
@Service
public class CommentServiceImpl {
    private final CommentMapper commentMapper;


    public CommentDTO receivingAdComments(CommentEntity commentEntity) {
        if (commentEntity != null) {
            commentMapper.toCommentDto(commentEntity);
            return commentMapper.toCommentDto(commentEntity);
        } else {
            throw new RuntimeException();
        }
    }


}
