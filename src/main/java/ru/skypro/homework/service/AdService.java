package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapping;
    private final UserService userService;

    public AdService(AdRepository adRepository, AdMapper adMapping, UserService userService) {
        this.adRepository = adRepository;
        this.adMapping = adMapping;
        this.userService = userService;
    }

    public AdDTO createAd(CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad newAd = adMapping.mapToAdFromCreateOrUpdateAd(createOrUpdateAd);
        newAd.setAuthor(author);
        adRepository.save(newAd);
        return adMapping.mapToAdDto(newAd);
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

    public void deleteAd(int id) {
        adRepository.deleteById(id);
    }

    public CreateOrUpdateAd updateAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        Ad updateAd = adRepository.findByPk(id);
        updateAd.setTitle(createOrUpdateAd.getTitle());
        updateAd.setPrice(createOrUpdateAd.getPrice());
        updateAd.setDescription(createOrUpdateAd.getDescription());
        adRepository.save(updateAd);
        return adMapping.mapToCreateOrUpdateAdDTO(updateAd);
    }


    public Collection<AdDTO>  getAdsMe(Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        List<Ad> adList = adRepository.findAllByAuthor_username(author.getUsername());
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        return adDTOList;
    }
}


