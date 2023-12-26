package ru.skypro.homework.service;
import org.mapstruct.Mapper;

import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    /*method will map
    entity to dto*/
    default Comment commentEntityToCommentDTO(CommentEntity commentEntity){
        if(commentEntity == null){
            return null;
        }
        UserEntity user = commentEntity.getAuthor();
        Comment commentDTO = new Comment();
        LocalDateTime now = LocalDateTime.now();
        long milliseconds = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        commentDTO.setAuthor(user.getId());
        commentDTO.setAuthorFirstName(user.getFirstName());
        commentDTO.setCreatedAt(milliseconds);
        commentDTO.setText(commentEntity.getText());
        commentDTO.setPk(commentEntity.getId());
        return commentDTO;
    }
    /*method will map
    Comments list to dto*/
    default Comments commentsToListDTO(List<CommentEntity> comments){
                List<Comment> commentList = comments
                .stream()
                .map(comment -> commentEntityToCommentDTO(comment))
                .collect(Collectors.toList());
                Comments result = new Comments(commentList.size(), commentList);
                return result;
    }
}
