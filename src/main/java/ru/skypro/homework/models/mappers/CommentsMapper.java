package ru.skypro.homework.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.models.dto.AdsCommentDto;
import ru.skypro.homework.models.entity.Comments;

import java.sql.Timestamp;
import java.time.Instant;


@Mapper(componentModel = "spring")
public interface CommentsMapper {
    @Mapping(target = "author", source = "author.id")
    AdsCommentDto toCommentsDto(Comments comments);


    @Mapping(target = "author.id", source = "author")
    Comments toComments(AdsCommentDto adsCommentDto);

    //Created at should be like:'2009-06-08T10:10:10Z'
    default Timestamp map(Instant instant) {
        return instant == null ? null : Timestamp.from(instant);
    }
}
