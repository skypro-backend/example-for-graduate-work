package com.skypro.service;

import com.skypro.repository.AdRepository;
import com.skypro.repository.CommentRepository;
import org.springframework.stereotype.Component;

/**
 * Компонент для проверки прав доступа к ресурсам.
 * <p>
 * Используется в аннотациях {@code @PreAuthorize} для проверки,
 * является ли текущий пользователь владельцем объявления или комментария.
 * </p>
 */
@Component("securityService")
public class SecurityService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    public SecurityService(AdRepository adRepository, CommentRepository commentRepository) {
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Проверяет, является ли пользователь владельцем объявления.
     *
     * @param adId     идентификатор объявления
     * @param username логин пользователя
     * @return {@code true}, если пользователь является автором, иначе {@code false}
     */
    public boolean isAdOwner(Integer adId, String username) {
        return adRepository.findById(adId)
                .map(ad -> ad.getAuthor().getUsername().equals(username))
                .orElse(false);
    }

    /**
     * Проверяет, является ли пользователь владельцем комментария.
     *
     * @param commentId идентификатор комментария
     * @param username  логин пользователя
     * @return {@code true}, если пользователь является автором, иначе {@code false}
     */
    public boolean isCommentOwner(Integer commentId, String username) {
        return commentRepository.findById(commentId)
                .map(c -> c.getAuthor().getUsername().equals(username))
                .orElse(false);
    }
}