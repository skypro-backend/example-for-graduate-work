package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;

@Service
public class AdService {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public void createAd(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setAuthor(adDTO.getAuthor());
        ad.setImage(adDTO.getImage());
        ad.setPk(adDTO.getPk());
        ad.setPrice(adDTO.getPrice());
        ad.setTitle(adDTO.getTitle());
        adRepository.save(ad);
    }
}
