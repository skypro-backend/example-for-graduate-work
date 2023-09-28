package ru.skypro.homework.mappers;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;

import ru.skypro.homework.service.entities.CommentEntity;

@Component
public class CommentMapper {

    public CommentDTO toCommentDto(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(commentEntity.getId());
        commentDTO.setAuthorImage(commentEntity.getUser().getImage());
        commentDTO.setAuthorFirstName(commentEntity.getUser().getFirstName());
        commentDTO.setCreatedAt(commentEntity.getCreatedAt());
        commentDTO.setPk(commentEntity.getAdEntity().getPk());
        commentDTO.setText(commentEntity.getText());
        return commentDTO;
    }

}

