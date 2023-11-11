package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    @Override
    public List<AdsDTO> getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        List<AdsDTO> adsDTOList = new ArrayList<>();
        for (Ads ads:adsList) {
            adsDTOList.add(Ads.mapToAdsDto(ads));
        }
        return adsDTOList;
    }

    @Override
    public AdsDTO addAds(MultipartFile image, CreateAdsDTO properties) {
        Ads ads = new Ads(properties.getDescription(),
                properties.getPrice(),
                properties.getTitle());

        adsRepository.save(ads);
        return Ads.mapToAdsDto(ads);
    }

    @Override
    public AdsInfoDTO getAdsById(long id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        return Ads.mapToAdsInfoDto(ads);
    }

    @Override
    public void deleteAds(long id) {
        adsRepository.deleteById(id);
    }

    @Override
    public AdsDTO updateAds(long id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElse(null);
        ads.setDescription(createAdsDTO.getDescription());
        ads.setPrice(createAdsDTO.getPrice());
        ads.setTitle(createAdsDTO.getTitle());

        adsRepository.save(ads);

        return Ads.mapToAdsDto(ads);
    }

    @Override
    public List<AdsDTO> getUserAds() {
        UserInfo user = new UserInfo();
        List<Ads> adsList = user.getAds();
        List<AdsDTO> adsDTOList = new ArrayList<>();
        for (Ads ads:adsList) {
            adsDTOList.add(Ads.mapToAdsDto(ads));
        }
        return adsDTOList;
    }

    @Override
    public String updateAdsImage(long id, MultipartFile image) {
        Ads ads = adsRepository.findById(id).orElse(null);

        ads.setImage("Test");
        adsRepository.save(ads);

        return "Test";
    }


}
