package ru.skypro.homework.mapper.impl;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.CommentModel;

public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto toDTO(CommentModel model) {
        return null;
    }

    @Override
    public CommentDto toDTO(CommentDto model) {
        if ( model == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        return commentDto;
    }

    @Override
    public CommentDto toModel(CommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        return commentDto;
    }
}