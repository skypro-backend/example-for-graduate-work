package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentsService {
    CommentsDTO getFullComments(int id);
    CommentsDTO addComments(int id);
    void removeComments(int id, int commentsId);
    CreateOrUpdateCommentDTO updateComments(int id, int commentsId);


}
