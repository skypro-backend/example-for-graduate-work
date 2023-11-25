package ru.skypro.homework.service;

import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;

public interface CommentsService {
    Comments getComments(int id);
    Comments addComment(int id);
    void deleteComment(int id, int commentsId);
    CreateOrUpdateComment updateComment(int id, int commentsId, CreateOrUpdateComment createOrUpdateComment);


}