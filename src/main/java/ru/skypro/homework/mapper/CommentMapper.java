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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "ad", source = "ad")
    CommentEntity createOrUpdateCommentDTOAndAdEntityToCommentEntity (CreateOrUpdateCommentDTO createOrUpdateCommentDTO, AdEntity ad, UserEntity author);

    @Mapping(target = "id", source = "commentDto.pk")
    @Mapping(target = "author.id", source = "user.id")
    CommentEntity commentDtoAndUsersEntityAndAdsEntityToCommentsEntity(UserEntity user, AdEntity ad, CommentDTO commentDto);

    @Mapping(target = "author", source = "commentEntity.author.id")
    CommentDTO commentsEntityToCommentDTO(CommentEntity commentEntity);

    List<CommentDTO> listCommentEntityToListCommentDto(List<CommentEntity> commentEntityList);

    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(image(commentEntity))")

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDTO toCommentDTO(CommentEntity commentEntity);

    default String image(CommentEntity commentEntity) {
        int id = commentEntity.getAuthor().getId().intValue();
        return "/users/" + id + "/image";
    }

    CommentEntity toComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

    Collection<CommentsDTO> toCommentsListDto(Collection<CommentEntity> commentCollection);

}
