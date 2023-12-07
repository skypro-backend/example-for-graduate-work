package ru.skypro.homework.service;

import ru.skypro.homework.entity.CommentEntity;

public interface CommentService {
CommentEntity create(CommentEntity comment);
CommentEntity read(long id);
CommentEntity update(CommentEntity comment);
CommentEntity delete(long id);
}
