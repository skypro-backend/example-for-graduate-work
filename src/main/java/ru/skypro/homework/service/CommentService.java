package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentService {

    CommentDto create (Integer id, CommentDto commentDto,
                             Authentication authentication);

    CommentDto update(Integer adId, Integer commentId, CommentDto commentDto);

    List<CommentDto> get(Integer id);

    Comment getById(Integer id);

    void remove(Integer adId, Integer commentId);
}
