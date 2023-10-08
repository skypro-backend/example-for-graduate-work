package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.IncorrectArgumentException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final UserServiceImpl userService;
    private final AdsRepository adsRepository;
    private final ImageServiceImpl imageService;
    private final UserRepository userRepository;

    @Override
    public AdsDto create(MultipartFile imageFiles, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) throws IOException {
        log.debug("Adding ads");

        if (createOrUpdateAd.getTitle() == null || createOrUpdateAd.getTitle().isBlank()
                || createOrUpdateAd.getDescription() == null || createOrUpdateAd.getDescription().isBlank()
                || createOrUpdateAd.getPrice() == null) throw new IncorrectArgumentException();

        Ads ads = AdsMapper.INSTANCE.toEntity(createOrUpdateAd);
        User user = userService.getByUsername(authentication.getName());
        ads.setAuthor(user);
        Image image = imageService.uploadImage(imageFiles);
        ads.setImage(image);
        return AdsMapper.INSTANCE.toDto(adsRepository.save(ads));
    }

    @Override
    public AdsDto update(Integer id, CreateOrUpdateAd createOrUpdateAd) {

        log.debug("Updating ads by id: {}", id);

        if (createOrUpdateAd.getTitle() == null || createOrUpdateAd.getTitle().isBlank()
                || createOrUpdateAd.getDescription() == null || createOrUpdateAd.getDescription().isBlank()
                || createOrUpdateAd.getPrice() == null) throw new IncorrectArgumentException();

        Ads ads = findAdsById(id);
        ads.setTitle(createOrUpdateAd.getTitle());
        ads.setDescription(createOrUpdateAd.getDescription());
        ads.setPrice(createOrUpdateAd.getPrice());
        adsRepository.save(ads);
        log.info("Ads details updated for ads: {}", ads.getTitle());
        return AdsMapper.INSTANCE.toDto(ads);

    }

    @Override
    public void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException {

        log.debug("Updating ads image by id: {}", id);
        Ads ads = findAdsById(id);
        if (ads.getImage() != null) {
            imageService.remove(ads.getImage());
        }
        ads.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ads);
        log.debug("Avatar updated for ads: {}", ads.getTitle());
    }

    @Override
    public List<AdsDto> get(Authentication authentication) {
        log.debug("Getting ads by author {}", authentication.getName());
        return adsRepository.
                findAllByAuthorId(userService.getByUsername(authentication.getName()).getId())
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExtendedAd getAdsById(Integer id) {
        log.debug("Getting ads by id: {}", id);
        return AdsMapper.INSTANCE.toExtendedAds(findAdsById(id));
    }

    @Override
    public List<AdsDto> getAllAds() {
        log.debug("Getting all ads");
        return adsRepository.findAll()
                .stream()
                .map(AdsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Integer id) {

        log.debug("Removing ads by id: {}", id);
        Ads ads = findAdsById(id);
        adsRepository.delete(ads);
        log.info("Ads removed successfully");
    }

    public Ads findAdsById(Integer id) {
        log.debug("Finding ads by id: {}", id);
        return adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
    }

}
