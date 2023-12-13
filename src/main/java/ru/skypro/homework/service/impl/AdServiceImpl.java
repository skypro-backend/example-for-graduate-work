package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private Logger LoggerFactory;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final AdMapper adMapper;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdServiceImpl.class));

    /**
     * Getting all ads
     * @return Ads
     */
    @Override
    public Ads findAllAds() {
        return adMapper.mapToListOfDTO(adRepository.findAll());
    }

    /**
     * Adding an ad
     * <br>
     * Creating ad using {@link AdMapper#createFromCreateOrUpdateAd(CreateOrUpdateAd, User)}
     * <br>
     * Saving ad using {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param createOrUpdateAd
     * @param authentication
     * @param multipartFile
     * @return Ad
     */
    @Override
    public AdDTO createAd(CreateOrUpdateAd createOrUpdateAd, Authentication authentication, MultipartFile multipartFile) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        logger.info("Обьявление - " + createOrUpdateAd + ", пользователь - " + user);
        Image imageToDB = imageService.uploadImage(multipartFile);
        Ad ad = adMapper.createFromCreateOrUpdateAd(createOrUpdateAd, user);
        ad.setImage(imageToDB);
        adRepository.save(ad);
        logger.info("Создание обьявления");
        return adMapper.mapToDTO(ad);
    }

    /**
     * Updating ad
     * <br>
     * Saving ad in DB by repository method {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * <br>
     * Using setters and getters from dto CreateOrUpdateAd {@link CreateOrUpdateAd}
     *
     * @param ad
     * @param createOrUpdateAd
     * @return Ad
     */
    @Override
    public Ad updateAd(Ad ad, CreateOrUpdateAd createOrUpdateAd) {
        ad.setTitle(createOrUpdateAd.getTitle());
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setDescription(createOrUpdateAd.getDescription());
        return adRepository.save(ad);
    }

}
