package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

import java.nio.file.AccessDeniedException;

public interface CommentService {

    /**
     * Добавление комментария к объявлению. Права имеет только аутентифицированный пользователь.
     * @param id - ID объявления
     * @param createOrUpdateCommentDto - dto для создания комментария
     * @param authentication пользователь авторизованной сессии
     * @return CommentDto - dto комментария
     */
    CommentDTO addComment(long id, CreateOrUpdateCommentDTO createOrUpdateCommentDto, Authentication authentication);

    /**
     * Получение комментариев объявления. Права имеет только аутентифицированный пользователь.
     * @param id - ID объявления
     * @return CommentsDto - dto для передачи списка комментариев объявления.
     */
    CommentsDTO getComments(long id);

    /**
     * Удаление комментария. Права имеют хозяин объявления и пользователь с ролью ADMIN.
     * @param idAd - ID объявления
     * @param idComment - ID комментария
     * @param authentication пользователь авторизованной сессии
     */
    void deleteComment(long idAd, long idComment, Authentication authentication) throws AccessDeniedException;

    /**
     * Обновление комментария. Права имеют хозяин объявления и пользователь с ролью ADMIN.
     * @param idAd - ID объявления
     * @param idComment - ID комментария
     * @param createOrUpdateCommentDto  - dto для редактирования комментария
     * @param authentication пользователь авторизованной сессии
     * @return CommentDto - dto комментария
     */
    CommentDTO updateComment(long idAd, long idComment, CreateOrUpdateCommentDTO createOrUpdateCommentDto, Authentication authentication) throws AccessDeniedException;


}
