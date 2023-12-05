package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {


    private Logger LoggerFactory;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdServiceImpl.class));

    @Override
    public boolean createOrUpdateAd(CreateOrUpdateAd createOrUpdateAd, User user, Image image) {
        logger.info("Обьявление - " + createOrUpdateAd + ", пользователь - " + user);
        Ad ad = adMapper.mapFromCreateOrUpdateAd(createOrUpdateAd, user);
        ad.setImage(image);
        logger.info("Созданное обьявление - " + ad);
        adRepository.save(ad);
        return true;
    }

}
