package ru.skypro.homework.mapper;


import lombok.Builder;
import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
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
                .pk(commentEntity.getPk())
                .text(commentEntity.getText())
                .adId(commentEntity.getAdId().getPk())
                .build();
    }

    public CommentEntity AdToAdEntity(CreateOrUpdateComment dto) {
        if (dto == null) {
            throw new NullPointerException(" Ошибка маппера при создании CommentEntity! CreateOrUpdateComment == null! ");
        }
        return CommentEntity.builder()
                .text(dto.getText())
                .build();
    }

    List<Comment> adEntityListToAdList(List<CommentEntity> commentEntityList) {
        if (commentEntityList == null) {
            throw new NullPointerException("Ошибка маппера при создании List<Comment>! List<CommentEntity> == null!");
        }
        return commentEntityList.stream()
                .map(this::commentEntityToComment)
                .collect(Collectors.toList());
    }
}

