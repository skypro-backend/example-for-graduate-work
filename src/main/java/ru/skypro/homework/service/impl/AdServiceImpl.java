package ru.skypro.homework.service.impl;


import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepositiry;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AdService;

import java.util.logging.Logger;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepositiry adRepositiry;
    private Logger LoggerFactory;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AdServiceImpl.class));

    public AdServiceImpl(AdRepositiry adRepositiry) {
        this.adRepositiry = adRepositiry;
    }
@Override
    public Ad get(Integer id, boolean ad) {
        logger.info("Method get was invoked!");
        Ad adDB = (Ad) AdRepositiry.findByPk(id).
                orElse(null);
        Ad ad = new Ad(ad.getPk(),
                ad.getAuthor(),
                ad.getImage(),
                ad.getPrice(),
                ad.getTitle(),
                ad.getClass());
        ad.setPk(ad.getPk());
        ad.setAuthor(adDB.getAuthor());
        ad.setImage(ad.getImage());
        ad.setTitle(adDB.getTitle());
        return ad;
    }

    @Override
    public boolean AdDto(Integer id, boolean add) {
        return false;
    }

    @Override
    public Ad update(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        Ad adDB = (Ad) AdRepositiry.findByPk(id).
                orElse(null);
        Ad ad = new Ad(ad.getPk(), ad.getAuthor(), ad.getImage(), ad.getPrice(), ad.getTitle(), ad.getClass());
        ad.setPk(ad.getPk());
        ad.setAuthor(adDB.getAuthor());
        ad.setImage(ad.getImage());
        ad.setTitle(adDB.getTitle());
        return ad;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(Register register) {
        return false;
    }
}
