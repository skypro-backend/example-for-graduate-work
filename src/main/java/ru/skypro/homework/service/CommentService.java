package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentsDto getCommentsByAdId(Integer adId);

    CommentDto addCommentToAd(Integer adId, CreateOrUpdateCommentDto dto);

    void deleteComment(Integer id, Integer adId);

    CommentDto updateComment(Integer id, Integer adId, CreateOrUpdateCommentDto dto);

    boolean existsCommentByIdAndUsername(Integer id, String username);

}

