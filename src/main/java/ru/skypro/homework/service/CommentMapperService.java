package ru.skypro.homework.service;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommentMapperService {

    public Comment mapToEntity(CommentDto commentDTO, User user, Ad ad) {
        Comment commentEntity = new Comment();
        commentEntity.setCommentId(commentDTO.getPk());
        commentEntity.setAuthor(user);
        commentEntity.setAd(ad.getPk());
        commentEntity.setText(commentDTO.getText());
        commentEntity.setCreatedTime(commentDTO.getCreatedAt());
        return commentEntity;
    }

    public CommentDto mapToDto(Comment commentEntity) {
//        Integer author;
//        String authorImage;
//        String authorFirstName;
//        Long createdAt;
//        Integer pk;
//        String text;
        CommentDto commentDTO = new CommentDto();
       commentDTO.setAuthor(commentEntity.getAuthor().getId());
       commentDTO.setAuthorImage(commentEntity.getAuthor().getImagePath());
       commentDTO.setAuthorFirstName(commentEntity.getAuthor().getFirstName());
       commentDTO.setCreatedAt(commentEntity.getCreatedTime());
       commentDTO.setPk(commentEntity.getCommentId());
       commentDTO.setText(commentEntity.getText());


        return commentDTO;
    }
    public List CommentListToCommentDtoToList(List<Comment>comments) {
        List<CommentDto> commentDtoList =comments.stream().map(this::mapToDto).collect(Collectors.toList());
return commentDtoList;
    }
}
