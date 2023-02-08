package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;

/**
 * Сервис для объявлений
 */
@Service
public class AdsServiceImpl implements AdsService {

    /**
     * Получение всех комментариев объявления
     * @param pk
     * @return
     */
    @Override
    public Collection<Comment> getAdsComments(Integer pk) {
        return null;
    }

    /**
     * Добавление коментария к объявлению
     * @param pk
     */
    @Override
    public void addAdsComments(Integer pk) {

    }

    /**
     * Удаление комментария конкретного пользователя у объявления
     * @param pk
     * @param id
     */
    @Override
    public void deleteAdsComment(Integer pk, Integer id) {

    }
}
