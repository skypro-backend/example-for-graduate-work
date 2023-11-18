package ru.skypro.homework.service;

import ru.skypro.homework.model.AdEntity;

public interface AdService {

    /**
     * Метод добавляет товар.
     * @return объект {@link AdEntity}, содержащий информацию о товаре.
     */
    AdEntity createAd(AdEntity createAd);

    /**
     * Метод обновляет информацию о товаре.
     * @param updateAd объект содержащий поля с заголовком, ценой и описанием товара.
     * @return объект {@link AdEntity}, содержащий информацию о товаре.
     */
    AdEntity updateAd(AdEntity updateAd);
}
