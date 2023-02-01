package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.model.entity.ResponseWrapperComment;

@Mapper
public interface ResponseWrapperCommentMapper {
    ResponseWrapperCommentDto toResponseWrapperCommentDto(ResponseWrapperComment responseWrapperComment);
    ResponseWrapperComment toResponseWrapperComment (ResponseWrapperCommentDto responseWrapperCommentDto);
}
