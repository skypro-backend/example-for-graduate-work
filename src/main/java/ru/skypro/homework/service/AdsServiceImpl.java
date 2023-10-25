package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdInfoDTO;
import ru.skypro.homework.dto.AdUpdateDTO;
import ru.skypro.homework.dto.AllAdDTO;
import ru.skypro.homework.pojo.Ad;
import ru.skypro.homework.pojo.Image;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;


    private final ImageService imageService;

    private final UserRepository userRepository;

    public AdsServiceImpl(AdRepository adRepository, ImageService imageService, UserRepository userRepository) {
        this.adRepository = adRepository;

        this.imageService = imageService;
        this.userRepository = userRepository;
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
            Image uploadedImage = imageService.uploadImage(imageFile, ad.getPk());

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
        createdAdDTO.setAuthor(createdAd.getUser().getUserID());
        createdAdDTO.setDescription(createdAd.getDescription());
        createdAdDTO.setPrice(createdAd.getPrice());
        createdAdDTO.setTitle(createdAd.getTitle());
        createdAdDTO.setPk(createdAd.getPk());
        return createdAdDTO;
    }

    @Override
    public List<AllAdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll(); // Извлекаем все объявления из базы данных
        List<AllAdDTO> adsRequestDTOs = new ArrayList<>();

        for (Ad ad : ads) {
            AllAdDTO adsRequestDTO = new AllAdDTO();
            adsRequestDTO.setAuthor(ad.getUser().getUserID());
            adsRequestDTO.setPk(ad.getPk());
            adsRequestDTO.setPrice(ad.getPrice());
            adsRequestDTO.setTitle(ad.getTitle());
            adsRequestDTO.setImage(ad.getImage().getImagePath());


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
            adInfoDTO.setImage(ad.getImage().getImagePath());
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
    public AdUpdateDTO updateAd(Long pk, AdUpdateDTO adUpdateDTO) {
        Optional<Ad> optionalAd = adRepository.findById(pk);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();

            if (adUpdateDTO.getTitle() != null) {
                ad.setTitle(adUpdateDTO.getTitle());
            }
            if (adUpdateDTO.getPrice() != null) {
                ad.setPrice(adUpdateDTO.getPrice());
            }
            if (adUpdateDTO.getDescription() != null) {
                ad.setDescription(adUpdateDTO.getDescription());
            }

            // Сохраняем обновленное объявление в базу
            adRepository.save(ad);

            // Создаем объект AdsDTO для ответа с нужными полями
            AdUpdateDTO responseAd = new AdUpdateDTO();
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
    public List<AllAdDTO> getAdsForUser(String userName) {
        User user = userRepository.findUserByUserName(userName);
        Long userId = user.getUserID();
        List<Ad> userAds = adRepository.findAdsByUserUserID(userId);

        List<AllAdDTO> allAdDTOs = new ArrayList<>();

        for (Ad ad : userAds) {
            AllAdDTO adDTO = new AllAdDTO();
            adDTO.setAuthor(ad.getUser().getUserID()); // Устанавливаем айдишник объявления
            adDTO.setImage(ad.getImage().getImagePath()); // Устанавливаем путь к изображению
            adDTO.setPk(ad.getPk());
            adDTO.setPrice(ad.getPrice());
            adDTO.setTitle(ad.getTitle());

            allAdDTOs.add(adDTO);
        }

        return allAdDTOs;
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

