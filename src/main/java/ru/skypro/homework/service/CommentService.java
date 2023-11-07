package ru.skypro.homework.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;

public interface CommentService {

    /**
     * Получение всех комментариев объявления по его идентификатору из базы данных.
     */
    List<CommentDto> getCommentByIdAd(Integer id);

    /**
     * Создание комментария к объявлению и сохранение его в базу данных.
     * id                       идентификатор объявления, не может быть {@code null}.
     * createOrUpdateCommentDto содержит текст комментария, не может быть {@code null}.
     * authentication           объект аутентификации, представляющий текущего пользователя.
     */
    CommentDto createAdComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication);

    /**
     * Удаление комментария по его идентификатору из базы данных.
     */
    void deleteCommentById(Integer commentId);

    /**
     * Обновление комментария у объявления и сохранение его в базу данных.
     * adId                     идентификатор объявления, не может быть {@code null}.
     * commentId                идентификатор комментария, не может быть {@code null}.
     * createOrUpdateCommentDto содержит текст комментария, не может быть {@code null}.
     * authentication           объект аутентификации, представляющий текущего пользователя.
     */
    CommentDto updateComment(Integer adId,
                             Integer commentId,
                             CreateOrUpdateCommentDto createOrUpdateCommentDto,
                             Authentication authentication);
}