package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDto;

/**
 * Сервис объявлений
 */
public interface AdsService {
    /**
     * Возвращает объявление
     * @param id    - идентификатор объявления
     * @return      - комментарий
     */
    AdsDto getAds(int id);
    /**
     * Обновляет объявление
     * @param id      - идентификатор объявления
     * @return          - обнволенный комментарий
     */
    AdsDto updateAds( int id);
}
