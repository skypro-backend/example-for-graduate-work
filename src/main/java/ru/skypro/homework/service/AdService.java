package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;


    public AdService(
            AdRepository adRepository,
            UserRepository userRepository,
            AdMapper adMapper,
            ImageService imageService
    ) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    public Ads getAllAds() {
        List<Ad> ads = adRepository.findAll()
                .stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);

        return result;
    }

    public Ad addAd(
            String username,
            CreateOrUpdateAd dto,
            MultipartFile image
    ) {
        UserEntity user = getUser(username);

        AdEntity entity = new AdEntity();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setAuthor(user);

        String fileName = imageService.saveImage(image);

        entity.setImage("/images/" + fileName);

        return adMapper.toDto(
                adRepository.save(entity)
        );
    }

    public ExtendedAd getAd(Integer id) {
        return adMapper.toExtendedDto(getAdEntity(id));
    }

    public Ad updateAd(
            Integer id,
            CreateOrUpdateAd dto,
            String username
    ) {
        AdEntity entity = getAdEntity(id);
        UserEntity currentUser = getUser(username);

        checkAccess(entity, currentUser);

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return adMapper.toDto(adRepository.save(entity));
    }

    public void deleteAd(Integer id, String username) {
        AdEntity entity = getAdEntity(id);
        UserEntity currentUser = getUser(username);

        checkAccess(entity, currentUser);

        adRepository.delete(entity);
    }

    public Ads getAdsByUser(String username) {
        UserEntity user = getUser(username);

        List<Ad> ads = adRepository
                .findAllByAuthorId(user.getId())
                .stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());

        Ads result = new Ads();
        result.setCount(ads.size());
        result.setResults(ads);

        return result;
    }

    private AdEntity getAdEntity(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Ad not found")
                );
    }

    private UserEntity getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );
    }

    private void checkAccess(
            AdEntity ad,
            UserEntity user
    ) {
        boolean isOwner =
                ad.getAuthor().getId().equals(user.getId());

        boolean isAdmin =
                user.getRole() == Role.ADMIN;

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("Access denied");
        }
    }

    public byte[] updateImage(
            Integer id,
            MultipartFile image,
            String username
    ) {
        AdEntity entity = getAdEntity(id);
        UserEntity currentUser = getUser(username);

        checkAccess(entity, currentUser);

        String fileName = imageService.saveImage(image);

        entity.setImage("/images/" + fileName);

        adRepository.save(entity);

        return imageService.getImage(fileName);
    }
}