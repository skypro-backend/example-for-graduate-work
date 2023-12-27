package ru.skypro.homework.mapper;


import lombok.Builder;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Component
public class CommentMapper {
    public Comment commentEntityToComment(CommentEntity commentEntity) {
        if (commentEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании Comment! CommentEntity == null!");
        }
        return Comment.builder()
                .author(commentEntity.getUserEntity().getId())
                .authorImage(commentEntity.getUserEntity().getImageEntity().getFilePath())
                .authorFirstName(commentEntity.getUserEntity().getFirstName())
                .createdAt(commentEntity.getCreatedAt())
                .pk(commentEntity.getId())
                .text(commentEntity.getText())
                .adId(commentEntity.getAdId().getId())
                .build();
    }

    public CommentEntity commentToCommentEntity(CreateOrUpdateComment dto) {
        if (dto == null) {
            throw new NullPointerException(" Ошибка маппера при создании CommentEntity! CreateOrUpdateComment == null! ");
        }
        return CommentEntity.builder()
                .text(dto.getText())
                .build();
    }

    public List<Comment> commentEntityListToCommentList(List<CommentEntity> commentEntityList) {
        if (commentEntityList == null) {
            throw new NullPointerException("Ошибка маппера при создании List<Comment>! List<CommentEntity> == null!");
        }
        return commentEntityList.stream()
                .map(this::commentEntityToComment)
                .collect(Collectors.toList());
    }
}

