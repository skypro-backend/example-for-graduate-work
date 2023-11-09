package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateOrUpdateCommentMapper {
    @Mapping(target = "comment.text", source = "text")
    Comment toModel(CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
