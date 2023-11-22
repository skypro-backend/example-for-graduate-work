package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public Collection<AdDTO> getAll() {
        List<Ad> adList = (List<Ad>) adRepository.findAll();
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        return adDTOList;
    }

    public AdDTO findAd(int id) {
        return adRepository.findById(id).
                map(adMapping::mapToAdDto).orElseThrow();
    }
}


