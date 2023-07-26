package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<CommentDto> listCommentsAdById(Long id);
    Optional<CommentDto> createComment(String userLogin, Long id, CreateOrUpdateComment createComment);
    boolean deleteById(String userLogin, Long adId, Long commentId);
    Optional<CommentDto> editComment(String userLogin, Long adId, Long commentId, CreateOrUpdateComment updateComment);

}
