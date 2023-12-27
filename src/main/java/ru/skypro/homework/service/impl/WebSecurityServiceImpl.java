package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.CommentRepository;
import ru.skypro.homework.service.WebSecurityService;
import ru.skypro.homework.util.exceptions.NotFoundException;

@Service
public class WebSecurityServiceImpl implements WebSecurityService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    public WebSecurityServiceImpl(AdRepository adRepository, CommentRepository commentRepository){
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean canAccessInAd(Integer id, String username) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        return adEntity.getAuthor().getLogin().equals(username);
    }

    @Override
    public boolean canAccessInComment(Integer id, String username) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        return commentEntity.getAuthor().getLogin().equals(username);
    }
}
