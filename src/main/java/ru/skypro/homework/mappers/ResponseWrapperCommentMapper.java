package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.model.ResponseWrapperComment;

@Mapper
public interface ResponseWrapperCommentMapper {
    ResponseWrapperCommentDto toResponseWrapperCommentDto(ResponseWrapperComment responseWrapperComment);
    ResponseWrapperComment toResponseWrapperComment (ResponseWrapperCommentDto responseWrapperCommentDto);
}
