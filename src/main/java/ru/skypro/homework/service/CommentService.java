package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Comment;

public interface CommentService {

    CommentsDto getComments(int id);

    CommentDto add(int id, CreateOrUpdateCommentDto comment, String name);
    void delete(int commentId);

    CommentDto update(int commentId, CommentDto comment, String email);

    Comment getEntity(int commentId);

}