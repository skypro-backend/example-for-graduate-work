package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;
import java.util.List;

/**
 * Interface CommentMapper
 * The mapper is used to map the CommentDTO fields to the Comment entity
 */

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

    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(image(comment))")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDTO toCommentDTO(CommentEntity commentEntity);

    default String image(CommentEntity commentEntity) {
        int id = commentEntity.getAuthor().getId().intValue();
        return "/users/" + id + "/image";
    }

    CommentEntity toComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
    List<CommentDTO> toCommentsListDto(Collection<CommentEntity> commentCollection);
}
