package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.repository.ExtendedAdRepository;
import ru.skypro.homework.service.ExtendedAdService;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public abstract class ExtendedAdServiceImpl implements ExtendedAdService {

    private ExtendedAdRepository extendedAdRepository;
    private Logger LoggerFactory;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(ExtendedAdServiceImpl.class));
    private Integer id;
    private boolean add;

    public <ExtendedAdRepository> ExtendedAdServiceImpl(ExtendedAdRepository ExtendedAdRepository) {
        this.extendedAdRepository = this.extendedAdRepository;
    }

    @Override
    public ExtendedAd get(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        ExtendedAd extendedAdDB = (ExtendedAd) ExtendedAdServiceImpl.findByPk(id).
                orElse(null);
        ExtendedAd extendedAd = new ExtendedAd(ExtendedAd.getPk(),
                ExtendedAd.getAuthor(),
                ExtendedAd.getImage(),
                ExtendedAd.getPrice(),
                ExtendedAd.getTitle());
        ExtendedAd.setPk(extendedAd.getPk());
        ExtendedAd.setPrice(extendedAd.getPrice());
        ExtendedAd.setAuthor(extendedAd.getAuthor());
        ExtendedAd.setImage(extendedAd.getImage());
        ExtendedAd.setTitle(extendedAd.getTitle());
        return extendedAd;
    }

    private static Optional<Object> findByPk(Integer id) {
        return null;
    }

    @Override
    public boolean ExtendedAd(Integer id, boolean add) {
        return false;
    }

    @Override
    public boolean ExtendedAd(boolean add, Integer id) {
        return false;
    }

    @Override
    public ExtendedAd update(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        ExtendedAd extendedAdDB = (ExtendedAd) ExtendedAdServiceImpl.findByPk(id).
                orElse(null);
        ExtendedAd extendedAd = new ExtendedAd(extendedAdRepository.getPk(),
                ExtendedAd.getAuthor(),
                ExtendedAd.getImage(),
                ExtendedAd.getPrice(),
                ExtendedAd.getTitle());
        ExtendedAd.setPk(extendedAdDB.getPk());
        ExtendedAd.setPrice(extendedAdDB.getPrice());
        ExtendedAd.setAuthor(extendedAdDB.getAuthor());
        ExtendedAd.setImage(extendedAdDB.getImage());
        ExtendedAd.setTitle(extendedAdDB.getTitle());
        return extendedAdDB;
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

