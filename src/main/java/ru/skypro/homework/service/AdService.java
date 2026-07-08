package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.Ads;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import org.springframework.security.core.Authentication;

import java.util.Optional;

     //* Интерфейс бизнес-логики для управления объявлениями.
     //* Обеспечивает CRUD-операции над сущностью {@link ru.skypro.homework.entity.AdEntity} и преобразование данных в DTO для передачи на клиент.

public interface AdService {

     //* Возвращает пагинированный список всех объявлений.
     //* @param offset смещение (номер страницы) для пагинации
     //* @param limit количество элементов на странице
     //* @return коллекцию объявлений Ads

    Ads getAllAds(int offset, int limit);

     //* Получает подробную информацию об объявлении по идентификатору.
     //* @param id уникальный идентификатор объявления (pk)
     //* @return Optional с расширенной информацией ExtendedAd

    Optional<ExtendedAd> getAdById(long id);

     //* Возвращает список объявлений текущего авторизованного пользователя.
     //* @param auth объект аутентификации Spring Security
     //* @return коллекцию собственных объявлений пользователя

    Ads getMyAds(Authentication auth);

     //* Создает новое объявление от имени текущего пользователя.
     //* Сохраняет изображение во временную директорию сервера, если файл передан.
     //* @param data параметры объявления (заголовок, цена, описание)
     //* @param image бинарные данные картинки (может быть null)
     //* @param auth данные аутентификации автора
     //* @return созданный объект Ad

    Ad updateAd(long id, CreateOrUpdateAd data, Authentication auth);

     //* Обновляет существующее объявление.
     //* Проверяет права доступа: изменить может только владелец или ADMIN.
     //* @param id идентификатор редактируемого объявления
     //* @param data новые значения полей
     //* @param auth данные аутентификации
     //* @return обновленный объект Ad

    void updateImage(long id, MultipartFile image, Authentication auth);

    //* Обновляет основное изображение объявления.
    //* @param id идентификатор объявления
    //* @param image новый файл изображения
    //* @param auth данные аутентификации владельца

    void deleteAd(long id);

    //* Удаляет объявление.
    //* Проверяет права доступа: удалить может только владелец или ADMIN.
    //* @param id идентификатор удаляемого объявления

    Ad createAd(
            CreateOrUpdateAd data,
            MultipartFile image,
            Authentication auth
    );
}