package ru.skypro.homework.service;

import ru.skypro.homework.model.Comment;

public interface CommentsService {
    Comment addComment(Comment commentDTO, Long adId);
    Comment getComment(Long id);
}
