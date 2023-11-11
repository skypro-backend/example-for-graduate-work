package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.GetCommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByAdsId(long id);

    CommentDTO addComment(long adsId, String text);

    void deleteComment(long adsId, long commentId);

    CommentDTO updateComment (long adsId, long commentId, String text);
}
