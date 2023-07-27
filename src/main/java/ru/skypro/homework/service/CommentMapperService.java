package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Service
public class CommentMapperService {

    public Comment mapToEntity(CommentDto commentDTO, User user, Ad ad) {
        Comment commentEntity = new Comment();
        commentEntity.setCommentId(commentDTO.getPk());
        commentEntity.setAuthor(user);
        commentEntity.setAd(ad);
        commentEntity.setText(commentDTO.getText());
        commentEntity.setCreatedTime(commentDTO.getCreatedAt());
        return commentEntity;
    }

    public CommentDto mapToDto(Comment commentEntity) {
        CommentDto commentDTO = new CommentDto();
        commentDTO.setPk(commentEntity.getCommentId());
        User author = Optional.ofNullable(commentEntity.getAuthor()).orElse(new User());
        commentDTO.setAuthor(author.getId());
        commentDTO.setAuthorFirstName(author.getFirstName());
        commentDTO.setAuthorImage(author.getImagePath());
        commentDTO.setCreatedAt(commentEntity.getCreatedTime());
        commentDTO.setText(commentEntity.getText());
        return commentDTO;
    }
}
