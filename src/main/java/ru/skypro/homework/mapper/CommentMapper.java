package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import ru.skypro.homework.dto.CommentDto;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.CommentModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {
    CommentDto toDTO(CommentModel model);

    CommentDto toDTO(CommentDto model);

    CommentDto toModel(CommentDto dto);

}