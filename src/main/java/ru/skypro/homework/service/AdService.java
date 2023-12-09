package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final AdMapper adMapping;
    private final UserService userService;
    private final ImageService imageService;

    public AdService(AdRepository adRepository, CommentRepository commentRepository, AdMapper adMapping, UserService userService, ImageService imageService) {
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.adMapping = adMapping;
        this.userService = userService;
        this.imageService = imageService;
    }

    /**
     * Создание нового объявления.
     */
    public AdDTO createAd(CreateOrUpdateAd createOrUpdateAd,
                          Authentication authentication,
                          MultipartFile imageFile) throws IOException {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad newAd = adMapping.mapToAdFromCreateOrUpdateAd(createOrUpdateAd);
        newAd.setAuthor(author);
        newAd.setImage(imageService.uploadImage(imageFile));
        adRepository.save(newAd);
        return adMapping.mapToAdDto(newAd);
    }

    /**
     * Получение всех объявлений.
     */
    public AdsDTO getAll() {
        List<Ad> adList = adRepository.findAll();
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        AdsDTO dto = new AdsDTO();
        dto.setCount(adList.size());
        dto.setResults(adDTOList);
        return dto;
    }

    /**
     * Получение информации об объявлении.
     */
    public ExtendedAdDTO findAd(int id) {
        return adRepository.findById(id).
                map(adMapping::mapToExtendedAdDTO).orElseThrow();
    }

    /**
     * Удаление объявления.
     */
    public boolean deleteAd(int id, Authentication authentication) throws IOException {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad ad = adRepository.findByPk(id);
        ;
        if (author.equals(ad.getAuthor()) || author.getRole() == RoleDTO.ADMIN
        ) {
            Image image = ad.getImage();
            commentRepository.deleteAll(ad.getComments());
            adRepository.delete(ad);
            imageService.deleteImage(image);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Обновление информации об объявлении.
     */
    public AdDTO updateAd(int id, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad updateAd = adRepository.findByPk(id);
        if (author.equals(updateAd.getAuthor()) || author.getRole() == RoleDTO.ADMIN) {
            updateAd.setTitle(createOrUpdateAd.getTitle());
            updateAd.setPrice(createOrUpdateAd.getPrice());
            updateAd.setDescription(createOrUpdateAd.getDescription());
            adRepository.save(updateAd);
        }
        return adMapping.mapToAdDto(updateAd);
    }

    /**
     * Получение объявлений авторизованного пользователя.
     */
    public AdsDTO getAdsMe(Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        List<Ad> adList = adRepository.findAllByAuthor_username(author.getUsername());
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        AdsDTO dto = new AdsDTO();
        dto.setResults(adDTOList);
        return dto;
    }

    /**
     * Обновление картинки объявления.
     */
    public void editAdImage(int id, MultipartFile image, Authentication authentication) throws IOException {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad updateAd = adRepository.findByPk(id);
        Image oldImage = updateAd.getImage();
        updateAd.setImage(imageService.uploadImage(image));
        adRepository.save(updateAd);
        imageService.deleteImage(oldImage);
    }
}


