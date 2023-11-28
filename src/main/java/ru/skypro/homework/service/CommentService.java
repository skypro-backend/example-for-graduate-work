package ru.skypro.homework.service;

import ru.skypro.homework.models.Comment;

public interface CommentService {
   Comment create(Comment comment);
   Comment read(Integer id);
   Comment update(Comment comment);
   Comment delete(Integer id);
}
