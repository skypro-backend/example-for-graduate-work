package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.CommentDto;
import ru.skypro.homework.model.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "id", target = "pk")
    CommentDto commentToCommentDto (Comment comment);
    @Mapping(target = "id", source = "pk")
    Comment commentDtoToComment(CommentDto commentDto);
//
//    default ResponseWrapperCommentDto adsToResponseWrapperCommentDto(List<Comment> comments) {
//        ResponseWrapperCommentDto wrapperComment = new ResponseWrapperCommentDto();
//        List<CommentDto> resultComment = comments.stream()
//                .map(this::commentToCommentDto).collect(Collectors.toList());
//        wrapperComment.setResults(resultComment);
//        wrapperComment.setCount(resultComment.size());
//        return wrapperComment;
//    }
}
