package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.projection.CommentView;


public class CommentMapper {
    public static CommentDTO fromComment(Comment comment) {
        return new CommentDTO()
                .setUserDTO(UserMapper.toDTO(comment.getUser()))
                .setPk(comment.getPk())
                .setText(comment.getText())
                .setCreatedAt(comment.getCreatedAt())
                .setAd(comment.getAd());
    }
    public static CommentView toView(Comment comment){
        return new CommentView()
                .setAuthorImage(comment.getUser().getImage())
                .setAuthor(comment.getUser().getId())
                .setText(comment.getText())
                .setAuthorFirstName(comment.getUser().getFirstName())
                .setCreatedAt(comment.getCreatedAt().toEpochMilli())
                .setPk(comment.getPk());
    }

}
