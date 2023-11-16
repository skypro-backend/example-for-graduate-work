package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapping;

    public AdService(AdRepository adRepository, AdMapper adMapping) {
        this.adRepository = adRepository;
        this.adMapping = adMapping;
    }

    public void createAd(AdDTO adDTO) {
        adRepository.save(adMapping.mapToAd(adDTO));
    }
}
