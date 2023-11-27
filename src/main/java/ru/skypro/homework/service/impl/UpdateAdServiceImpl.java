package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateAd;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.UpdateAdService;

import java.util.Optional;
import java.util.logging.Logger;
@Service
public class UpdateAdServiceImpl implements UpdateAdService {
    private UpdateAdRepositiry updateAdRepositiry;
    private Logger LoggerFactory;
    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(UpdateAdServiceImpl.class));
    private Integer id;
    private boolean add;

    public <UpdateAdRepositiry> UpdateAdServiceImpl(UpdateAdRepositiry updateAdRepositiry) {
        this.updateAdRepositiry = this.updateAdRepositiry;
    }
    @Override
    public UpdateAd get(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        UpdateAd updateAdDB = (UpdateAd) UpdateAdRepositiry.findByPk(id).
                orElse(null);
        UpdateAd updateAd = new UpdateAd(UpdateAd.getPk(),
                UpdateAd.getAuthor(),
                UpdateAd.getImage(),
                UpdateAd.getPrice(),
                UpdateAd.getTitle());
        UpdateAd.setPk(updateAd.getPk());
        UpdateAd.setPrice(updateAd.getPrice());
        UpdateAd.setAuthor(updateAd.getAuthor());
        UpdateAd.setImage(updateAd.getImage());
        UpdateAd.setTitle(updateAd.getTitle());
        return updateAd;
    }

    @Override
    public boolean UpdateAd(Integer id, boolean add) {
        return false;
    }

    @Override
    public boolean UpdateAd(boolean add, Integer id) {
        return false;
    }

    @Override
    public UpdateAd update(Integer id, boolean add) {
        logger.info("Method get was invoked!");
        UpdateAd adDB = (UpdateAd) UpdateAdRepositiry.findByPk(id).
                orElse(null);
        UpdateAd updateAd = new UpdateAd(updateAdRepositiry.getPk(),
                UpdateAd.getAuthor(),
                UpdateAd.getImage(),
                UpdateAd.getPrice(),
                UpdateAd.getTitle());
        UpdateAd.setPk(updateAd.getPk());
        UpdateAd.setPrice(updateAd.getPrice());
        UpdateAd.setAuthor(updateAd.getAuthor());
        UpdateAd.setImage(updateAd.getImage());
        UpdateAd.setTitle(updateAd.getTitle());
        return updateAd;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(Register register) {
        return false;
    }

    private static class UpdateAdRepositiry {
        public static Optional<Object> findByPk(Integer id) {
            return null;
        }

        public Object getPk() {
            return null;
        }
    }
}
