package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.AdsRepository;
import ru.skypro.homework.service.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.homework.AuthorizationUtils.isUserAdAuthorOrAdmin;

@Service
public class AdsServiceImpl implements AdsService {

    @Value("${path.to.ad.images}/") private String pathToAdImages;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserService userService;


    public AdsServiceImpl(
            AdsRepository adsRepository, UserRepository userRepository, ImageService imageService,
            UserService userService)
    {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.userService = userService;
    }

    /**
     * Метод создания обьявлений
     */
    @Override
    public AdsDTO createAds(CreateAds createAds, MultipartFile file) throws UserNotFoundException {
        User user = userService.getAuthUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
            Ad ad = new Ad(user, createAds.getDescription(), null, createAds.getPrice(),
                           createAds.getTitle());
            Ad savedAd = adsRepository.save(ad);
            imageService.updateAdImage(savedAd.getId(), file);
            return adsToDTO(savedAd);
    }



    /**
     * Получения обьявления по id
     */

    @Override
    public Ad getAdById(int id) {
            return adsRepository.findById(id);
    }

    /**
     * Удаление обьявления
     */
    @Override
    public void deleteAd(int id) {
        adsRepository.deleteById(id);

    }
    /**
     * Получение всех обьявлений
     */
    @Override
    public ResponseWrapperAds getAllAds() {
        List<AdsDTO> ads = adsRepository.findAll().stream().map(this::adsToDTO).collect(Collectors.toList());
        return new ResponseWrapperAds(ads.size(), ads);
    }
    /**
     * Получение всех обьявлений авторизованного пользователя
     */
    @Override
    public ResponseWrapperAds getAllUserAds() {
        User user = userService.getAuthUser();
        List<AdsDTO> adsDTOs = user.getAds().stream().map(this::adsToDTO).collect(Collectors.toList());
        return new ResponseWrapperAds(user.getAds().size(), adsDTOs);
    }

    public AdsDTO adsToDTO(Ad ad) {
        return new AdsDTO(ad.getUser().getId(), "/ads/image/" + ad.getId(), ad.getId(), ad.getPrice(), ad.getTitle());
    }

    /**
     * Обновление обьявления по id
     */
    @Override
    public AdsDTO updateAd(int id, CreateAds createAds) {
        Ad ad = adsRepository.findById(id);
        ad.setDescription(createAds.getDescription());
        ad.setPrice(createAds.getPrice());
        ad.setTitle(createAds.getTitle());
        adsRepository.saveAndFlush(ad);
        return adsToDTO(ad);
    }

    /**
     * Обновление фото для обьявления
     */
    @Override
    public AdsDTO updateAdImage(Integer adId, MultipartFile file) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotFoundException();
        }
        String name = authentication.getName();
        User user = userRepository.findUserByUserName(name);

        Ad ad = adsRepository.findById(adId).orElseThrow();
        if (isUserAdAuthorOrAdmin(ad, user)) {
            File tempFile = new File(pathToAdImages, ad.getImage().getImageName());
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found!");
            } catch (IOException e) {
                throw new RuntimeException();
            }
            return adsToDTO(ad);
        } else throw new RuntimeException("You can not change this!");

    }

    /**
     * Получение всех обьявлений
     */
    @Override
    public FullAds getFullAdById(int adId) {
        Ad ad = adsRepository.findById(adId);
        return  adsToFullAds(ad);
    }

    private FullAds adsToFullAds(Ad ad){
        return new FullAds(
                ad.getId(),
                ad.getUser().getFirstName(),
                ad.getUser().getLastName(),
                ad.getDescription(),
                ad.getUser().getUserName(),
                "/ads/image/"+ad.getId(),
                ad.getUser().getPhone(),
                ad.getPrice(),
                ad.getTitle());
    }
}
