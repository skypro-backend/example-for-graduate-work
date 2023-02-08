package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public Comment getComments(String adPk, int id) {
        return new Comment(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
    }

    @Override
    public void deleteComments(String adPk, int id) {
    }

    @Override
    public Comment updateComments(String adPk, int id, Comment comment) {
        return comment;
    }

    @Override
    public void removeAds(int id) {
    }
}
