package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {
    Comments getFullComments(int id);
    Comments addComments(int id);
    void removeComments(int id, int commentsId);
    CreateOrUpdateComment updateComments(int id, int commentsId);


}