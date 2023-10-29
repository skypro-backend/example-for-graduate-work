package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {
    private final ModelMapper mapper;

    private CommentEntity convertToCommentEntity(Comment comment){
        return mapper.map(comment,CommentEntity.class);
    }
    private Comment convertToCommentDto(CommentEntity commentEntity, UserEntity userEntity){
        return mapper.map(commentEntity, Comment.class);
    }
    private CommentEntity convertToCommentEntity(CreateOrUpdateComment comment){
        return mapper.map(comment, CommentEntity.class);
    }

}
