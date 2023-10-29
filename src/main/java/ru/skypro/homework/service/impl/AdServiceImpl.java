package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;

import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log
@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
      private final AdMapper adMapper;
      private final AdRepository adRepository;
      private final UserRepository userRepository;
      private final CommentRepository commentRepository;
      private final ImageRepository imageRepository;
      private final SecurityCheck securityCheck;

      @Override
      public List<AdDto> getAllAds () {
            log.info("Был вызван метод для получения всех объявлений");
            return adMapper.toAdsDto (adRepository.findAll());
      }

      @Override
      public AdDto addAd (CreateOrUpdateAdDto createOrUpdateAdDto , MultipartFile image) {
            log.info("Был вызван метод для создания объявления");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User currentUser = userRepository.findByEmail(username).orElseThrow();
            Ad ad = adMapper.toAdCreate(createOrUpdateAdDto);
            ad.setImage(null);
            ad.setAuthor(currentUser);
            return adMapper.toAdDto(adRepository.save(ad));
      }

      @Override
      public ExtendedAdDto getAdInformation (Integer id) {
            log.info("Был вызван метод для получения информации об объявлении по идентификатору");
            Ad ad = adRepository.findById(id).orElseThrow();
            return adMapper.toExtendedAdDto(ad);
      }

   @Override
      public void deleteAd (Integer id , Authentication authentication) {
         log.info("Был вызван метод для удаления объявления по идентификатору");
         User user = userRepository.findByEmail (authentication.getName ()).orElseThrow ();
         Ad ad = adRepository.findById (id).orElseThrow ();
         securityCheck.checkAd (id);

            if (securityCheck.isAdmin (user) || securityCheck.isAuthorAd (user, ad)) {
                  commentRepository.deleteById (id);
                  adRepository.deleteById (id);
                  imageRepository.deleteById (ad.getImage().getId());
         }
      }

      @Override
      public AdDto updateAdInformation (Integer id , CreateOrUpdateAdDto createOrUpdateAdDto ,
                                        Authentication authentication) {
            log.info ("Был вызван метод для обновления объявления по идентификатору");
            User user = userRepository.findByEmail (authentication.getName ()).orElseThrow ();
            Ad ad = adRepository.findById (id).orElseThrow ();
            securityCheck.checkAd (id);

            if (securityCheck.isAdmin (user) || securityCheck.isAuthorAd (user , ad)) {
            ad.setDescription (createOrUpdateAdDto.getDescription ());
            ad.setTitle (createOrUpdateAdDto.getTitle ());
            ad.setPrice (createOrUpdateAdDto.getPrice ());
           }
            return adMapper.toAdDto (adRepository.save (ad));
      }


      public List<AdDto> getAuthorizedUserAds (Authentication authentication) {
            log.info("Был вызван метод для получения объявлений авторизованного пользователя");
            User user = userRepository.findByEmail (authentication.getName()).orElseThrow ();
            return adMapper.toAdsDto(adRepository.findAllByAuthorId(user.getId ()));
      }

     @Override
      public void updateImageAd (Integer adsId , MultipartFile imageFile ,
                                 Authentication authentication) throws Exception {
            log.info("завершено обновление изображения объявления");
            User userAd = userRepository.findUserByUserName (authentication.getName()).orElseThrow ();
            Ad adImage = adRepository.findById (adsId).orElseThrow ();
            if (securityCheck.isAdmin(userAd) || securityCheck.isAuthorAd(userAd, adImage)) {
                  Image imageAd = imageRepository.findById(adImage.getImage().getId()).orElseThrow ();
                  Path filePath = Paths.get(imageAd.getMediaType ());
                  Files.write(filePath, imageFile.getBytes());
                  imageRepository.save (imageAd);
            }  throw new Exception ();
      }

}
