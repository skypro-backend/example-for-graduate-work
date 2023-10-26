package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import ru.skypro.homework.dto.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {
    CommentDto toDTO(CommentDto model);
    CommentDto toModel(CommentDto dto);
}