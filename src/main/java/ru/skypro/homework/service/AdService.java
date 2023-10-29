package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import java.util.List;


public interface AdService {
      List <AdDto> getAllAds ();  // метод для получения всех объявлений

      AdDto addAd (CreateOrUpdateAdDto createOrUpdateAdDto , MultipartFile image); // метод добавляет объявление

      ExtendedAdDto getAdInformation (Integer id); // получение информации об объявлении

      /**
       * Удаление объявления с комментарием по его идентификатору из базы данных
       * может быть сделано автором объявления и админом
       *
       * @param id идентификатор объявления, не может быть {@code null}.
       */
      void deleteAd (Integer id , Authentication authentication); // удаление объявления

      AdDto updateAdInformation (Integer id , CreateOrUpdateAdDto createOrUpdateAdDto ,
                                 Authentication authentication); // обновление информации об объявлении

      List <AdDto> getAuthorizedUserAds (Authentication authentication); // получение объявлений авторизованного пользователя void updateImageAd (Integer adsId , MultipartFile imageFile ,


      // обновление картинки объявления
      void updateImageAd (Integer adsId , MultipartFile imageFile, Authentication authentication) throws Exception;
}




