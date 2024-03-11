package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.Instant;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CommentMapper {
    @Named("idToUrl")
    static String idToUrl(int id) {
        return "/image/" + id;
    }

    @Named("instantToLong")
    static long instantToLong(Instant createdAt){
        return createdAt.toEpochMilli();
    }

    @Mapping(source = "author.pk", target = "author")
    @Mapping(source = "author.imageEntity.id", target = "authorImage", qualifiedByName = "idToUrl")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "instantToLong")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    Comment commentToCommentDTO(CommentEntity commentEntity);
}