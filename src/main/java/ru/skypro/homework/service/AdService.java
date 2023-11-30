package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final AdMapper adMapping;
    private final UserService userService;

    public AdService(AdRepository adRepository, CommentRepository commentRepository, AdMapper adMapping, UserService userService) {
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
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

    public AdsDTO getAll() {
        List<Ad> adList = (List<Ad>) adRepository.findAll();
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        AdsDTO dto = new AdsDTO ();
        dto.setCount(adList.size());
        dto.setResults(adDTOList);
        return dto;
    }

    public ExtendedAdDTO findAd(int id) {
        return adRepository.findById(id).
                map(adMapping::mapToExtendedAdDTO).orElseThrow();
    }

    public  boolean deleteAd(int id, Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        Ad ad = adRepository.findByPk(id);;
        if (author.equals(ad.getAuthor())
                //|| author.getRole() == Role.ADMIN
                ) {
         //   Image image = ad.getImage();
            commentRepository.deleteAll(ad.getComments());
            adRepository.delete(ad);
           // imageService.deleteImage(image);
            return true;
        } else {
            return false;
        }
    }

    public CreateOrUpdateAd updateAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        Ad updateAd = adRepository.findByPk(id);
        updateAd.setTitle(createOrUpdateAd.getTitle());
        updateAd.setPrice(createOrUpdateAd.getPrice());
        updateAd.setDescription(createOrUpdateAd.getDescription());
        adRepository.save(updateAd);
        return adMapping.mapToCreateOrUpdateAdDTO(updateAd);
    }


    public AdsDTO getAdsMe(Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        List<Ad> adList = adRepository.findAllByAuthor_username(author.getUsername());
        List<AdDTO> adDTOList = new ArrayList<>(adList.size());
        for (Ad a : adList) {
            adDTOList.add(adMapping.mapToAdDto(a));
        }
        AdsDTO dto = new AdsDTO ();
        dto.setResults(adDTOList);
        return dto;
    }
}


