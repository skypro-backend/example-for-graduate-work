package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;

import java.util.Date;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public Comment getComments(String adPk, int id) {
        return new Comment(1, "20.02.2023", Integer.parseInt(adPk), "Еще продается?");
    }
}
