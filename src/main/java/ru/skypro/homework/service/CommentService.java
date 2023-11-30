package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.GetCommentDTO;

import java.util.List;

/**
 * Интерфейс, содержащий методы для работы с классом {@link ru.skypro.homework.model.Comment}
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
public interface CommentService {

    GetCommentDTO getCommentsByAdsId(Integer id);

    CommentDTO addComment(Integer adsId, CreateCommentDTO createCommentDTO);

    void deleteComment(Integer adsId, Integer commentId);

    CommentDTO updateComment (Integer adsId, Integer commentId, CreateCommentDTO createCommentDTO);
}
