package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ExtendedAdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public abstract class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;
    private Logger LoggerFactory;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdsServiceImpl.class));
    private Integer id;
    private boolean add;

    public <AdsRepository> AdsServiceImpl(AdsRepository AdsRepository) {
        this.adsRepository = this.adsRepository;
    }

    @Override
    public Ads get(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        Ads adsDB = (Ads) AdsServiceImpl.findByPk(id).
                orElse(null);
        Ads ads = new Ads(Ads.getPk(),
                Ads.getAuthor(),
                Ads.getImage(),
                Ads.getPrice(),
                Ads.getTitle());
        Ads.setPk(adsDB.getPk());
        Ads.setPrice(adsDB.getPrice());
        Ads.setAuthor(adsDB.getAuthor());
        Ads.setImage(adsDB.getImage());
        Ads.setTitle(adsDB.getTitle());
        return ads;
    }

    private static Optional<Object> findByPk(Integer id) {
        return null;
    }

    @Override
    public Ads update(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        Ads adsDB = (Ads) AdsServiceImpl.findByPk(id).
                orElse(null);
        Ads ads = new Ads(adsRepository.getPk(),
                Ads.getAuthor(),
                Ads.getImage(),
                Ads.getPrice(),
                Ads.getTitle());
        Ads.setPk(adsDB.getPk());
        Ads.setPrice(adsDB.getPrice());
        Ads.setAuthor(adsDB.getAuthor());
        Ads.setImage(adsDB.getImage());
        Ads.setTitle(adsDB.getTitle());
        return ads;
    }

}
