package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.GetCommentDTO;

import java.util.List;

public interface CommentService {

    GetCommentDTO getCommentsByAdsId(Integer id);

    CommentDTO addComment(Integer adsId, CreateCommentDTO createCommentDTO);

    void deleteComment(Integer adsId, Integer commentId);

    CommentDTO updateComment (Integer adsId, Integer commentId, CreateCommentDTO createCommentDTO);
}
