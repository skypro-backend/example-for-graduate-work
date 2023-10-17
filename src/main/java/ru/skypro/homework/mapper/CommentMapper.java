package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Mapper (componentModel = "spring")
public interface CommentMapper {
      List <CommentDto> toCommentsDto(List<Comment> comments);
      @Mapping(target = "id", source = "pk")
      @Mapping(target = "author", ignore = true)
      @Mapping (target = "createdAt", ignore = true)
      @Mapping (target = "ad", ignore = true)
      Comment toCommenty (CommentDto commentDto); // конвертация DTO в сущность

      @Mapping(source = "id", target = "pk")
      @Mapping(target = "author", source = "author.id")
      @Mapping(target = "createdAt", qualifiedByName = "instantToInteger")
      CommentDto toCommentDto (Comment comment); // конвертация сущности в DTO

      @Mapping(target = "id", ignore = true)
      @Mapping(target = "author", ignore = true)
      @Mapping(target = "createdAt", ignore = true)
      @Mapping (target = "ad", ignore = true)
      Comment toCommentyCr (CreateOrUpdateCommentDto dto); // конвертация новых комментарий

      @Named("instantToInteger")
      default long instantToInteger(Instant instant) {
            return instant.atZone(ZoneOffset.ofHours(3)).toInstant().toEpochMilli();
      }

}
