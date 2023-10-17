package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;

import java.util.List;

public interface AdService {
      List<AdDto> getAllAds ();  // метод для получения всех объявлений

      AdDto addAd (CreateOrUpdateAdDto createOrUpdateAdDto , MultipartFile image); // метод добавляет объявление

      ExtendedAdDto getAdInformation (Integer id); // получение информации об объявлении

      void deleteAd (Integer id); // удаление объявления

      AdDto updateAdInformation (Integer id , CreateOrUpdateAdDto createOrUpdateAdDto); // обновление информации об объявлении

      AdsDto getAuthorizedUserAds (); // получение объявлений авторизованного пользователя
}
