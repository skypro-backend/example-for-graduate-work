package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentsService {
    CommentsDTO getComments(int id);
    CommentsDTO addComment(int id);
    void deleteComment(int id, int commentsId);
    CreateOrUpdateCommentDTO updateComment(int id, int commentsId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);


}
