package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "ad", source = "ad")
    CommentEntity createOrUpdateCommentDTOAndAdEntityToCommentEntity (CreateOrUpdateCommentDTO createOrUpdateCommentDTO, AdEntity ad, UserEntity author);

    @Mapping(target = "id", source = "commentDto.pk")
    CommentEntity commentDtoAndUsersEntityAndAdsEntityToCommentsEntity(UserEntity user, AdEntity ad, CommentDTO commentDto);

    @Mapping(target = "authorImage", source = "commentEntity.user.photoEntity.path")
    @Mapping(target = "authorFirstName", source = "commentEntity.user.firstName")
    @Mapping(target = "author", source = "commentEntity.user.id")
    CommentDTO commentsEntityToCommentDTO(CommentEntity commentEntity);

    List<CommentDTO> listCommentEntityToListCommentDto(List<CommentEntity> commentEntityList);
}
