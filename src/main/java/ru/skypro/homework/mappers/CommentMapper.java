package ru.skypro.homework.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.adsDTO.CommentDTO;
import ru.skypro.homework.dto.adsDTO.CommentsDTO;
import ru.skypro.homework.dto.adsDTO.CreateCommentDTO;
import ru.skypro.homework.service.entities.AdsEntity;
import ru.skypro.homework.service.entities.CommentEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    List<CommentDTO> toListDto(List<CommentEntity> commentList);
    CommentEntity toCommentFromCreateComment(CreateCommentDTO createComment);
    @Mapping(target = "author", source = "userEntity.id")
    @Mapping(target = "authorImage", source = "userEntity.image")
    @Mapping(target = "authorFirstName", source = "userEntity.firstName")
    @Mapping(target = "pk", source = "id")
    CommentDTO toCommentDtoFromComment(CommentEntity comment);
    @Mapping(target = "userEntity.id", ignore = true)
    @Mapping(target = "userEntity.image",ignore = true)
    @Mapping(target = "userEntity.firstName",ignore = true)
    void updateCommentFromCommentDto(CommentDTO commentDto,@MappingTarget CommentEntity comment);
}
