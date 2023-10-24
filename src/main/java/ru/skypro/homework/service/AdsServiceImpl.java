package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdInfoDTO;
import ru.skypro.homework.pojo.Ad;
import ru.skypro.homework.pojo.Image;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.AdRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;


    private final ImageService imageService;

    public AdsServiceImpl(AdRepository adRepository, ImageService imageService) {
        this.adRepository = adRepository;

        this.imageService = imageService;
    }


    @Override
    public AdDTO createAd(AdDTO adDTO, MultipartFile imageFile) {
        User user = new User();
        user.setUserID(user.getUserID());

        Ad ad = new Ad();
        ad.setUser(user);
        ad.setDescription(adDTO.getDescription());
        ad.setPrice(adDTO.getPrice());
        ad.setTitle(adDTO.getTitle());

        try {
            // Вызываем метод uploadImage и передаем в него imageFile
            Image uploadedImage = imageService.uploadImage(imageFile);

            // Связываем изображение с объявлением
            ad.setImage(uploadedImage);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки загрузки изображения
            throw new RuntimeException("Failed to upload image", e);
        }

        // Сохраняем объявление в базе данных
        Ad createdAd = adRepository.save(ad);

        AdDTO createdAdDTO = new AdDTO();
        createdAdDTO.setUserId(createdAd.getUser().getUserID());
        createdAdDTO.setDescription(createdAd.getDescription());
        createdAdDTO.setPrice(createdAd.getPrice());
        createdAdDTO.setTitle(createdAd.getTitle());
        createdAdDTO.setPk(createdAd.getPk());
        return createdAdDTO;
    }

    @Override
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll(); // Извлекаем все объявления из базы данных
        List<AdDTO> adsRequestDTOs = new ArrayList<>();

        for (Ad ad : ads) {
            AdDTO adsRequestDTO = new AdDTO();
            adsRequestDTO.setUserId(ad.getUser().getUserID());
            adsRequestDTO.setDescription(ad.getDescription());
            adsRequestDTO.setPk(ad.getPk());
            adsRequestDTO.setPrice(ad.getPrice());
            adsRequestDTO.setTitle(ad.getTitle());


            adsRequestDTOs.add(adsRequestDTO);
        }

        return adsRequestDTOs;
    }

    @Override
    public AdInfoDTO getAdsInfo(Long pk) {
        // инфа об объявлении по pk из базы данных
        Optional<Ad> adOptional = adRepository.findById(pk);

        if (adOptional.isPresent()) {
            Ad ad = adOptional.get();

            // Создание объекта AdsInfoDTO и заполнение его данными из объявления
            AdInfoDTO adInfoDTO = new AdInfoDTO();
            adInfoDTO.setPk(ad.getPk());
            adInfoDTO.setAuthorFirstName(ad.getUser().getFirstName());
            adInfoDTO.setAuthorLastName(ad.getUser().getLastName());
            adInfoDTO.setDescription(ad.getDescription());
            adInfoDTO.setEmail(ad.getUser().getEmail());
            adInfoDTO.setImage(ad.getImage());
            adInfoDTO.setPhone(ad.getUser().getPhone());
            adInfoDTO.setPrice(ad.getPrice());
            adInfoDTO.setTitle(ad.getTitle());


            return adInfoDTO;
        } else {
            // Если объявление не найдено, возвращаем null, потом допилим исключение
            return null;
        }
    }

    @Override
    public String deleteAd(Long pk) {
        // Находим по pk
        Optional<Ad> adOptional = adRepository.findById(pk);

        if (adOptional.isPresent()) {
            // Если объявление найдено, сносим
            adRepository.deleteById(pk);
            return "Объявление успешно удалено";
        } else {
            // Если объявление не найдено, вернем сообщение об ошибке
            return "Объявление с указанным ID не найдено";
        }
    }

    @Override
    public AdDTO updateAd(Long pk, AdDTO adDTO) {
        Optional<Ad> optionalAd = adRepository.findById(pk);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();

            if (adDTO.getTitle() != null) {
                ad.setTitle(adDTO.getTitle());
            }
            if (adDTO.getPrice() != null) {
                ad.setPrice(adDTO.getPrice());
            }
            if (adDTO.getDescription() != null) {
                ad.setDescription(adDTO.getDescription());
            }

            // Сохраняем обновленное объявление в базу
            adRepository.save(ad);

            // Создаем объект AdsDTO для ответа с нужными полями
            AdDTO responseAd = new AdDTO();
            responseAd.setUserId(ad.getUser().getUserID());
            responseAd.setPk(ad.getPk());
            responseAd.setPrice(ad.getPrice());
            responseAd.setTitle(ad.getTitle());
            responseAd.setDescription(ad.getDescription());

            return responseAd;
        } else {
            // Если объявление не найдено, возвращаем нулл, потом допилю исключение.
            return null;
        }
    }

    @Override
    public List<AdDTO> getAdsForUser(Long userId) {
        // Используем adRepository для получения объявлений конкретного пользователя
        List<Ad> ads = adRepository.findAdsByUserId(userId);

        // Преобразуем список объявлений в список DTO (AdsDTO)
        List<AdDTO> adDTOS = new ArrayList<>();
        for (Ad ad : ads) {
            AdDTO adDTO = new AdDTO();
            adDTO.setPk(ad.getPk());
            adDTO.setTitle(ad.getTitle());
            adDTO.setPrice(ad.getPrice());
            adDTO.setDescription(ad.getDescription());

            adDTOS.add(adDTO);
        }

        return adDTOS;
    }

    @Override
    public void updateAdImage(Long pk, Image newImage) {
        Ad ad = adRepository.findById(pk).orElse(null);
        if (ad != null) {
            // Сохранение изображения в базе данных
            Image savedImage = imageService.saveImage(newImage);
            ad.setImage(savedImage);
            adRepository.save(ad);
        }
    }
}

