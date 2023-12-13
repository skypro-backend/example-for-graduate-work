package ru.skypro.homework.service.mapping.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapping.AdMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdMapperImpl  implements AdMapper {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public AdMapperImpl(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ru.skypro.homework.dto.ads.Ad adEntityToAdDto(AdEntity ad) {
        if (ad == null) {
            return null;
        }
        ru.skypro.homework.dto.ads.Ad assembledAdDto = new ru.skypro.homework.dto.ads.Ad();
        assembledAdDto.setAuthor(ad.getUserRelated().getId());
        assembledAdDto.setPk(ad.getId());
        if (ad.getImageAd() != null) {
            assembledAdDto.setImage("/ads/" + ad.getImageAd().getId() + "/adPicture");
        } else {
            assembledAdDto.setImage(null);
        }
        assembledAdDto.setPrice(ad.getPrice());
        assembledAdDto.setTitle(ad.getTitle());
        return assembledAdDto;
    }

    @Override
    public ExtendedAd adEntityToExtendedAdDto(AdEntity ad) {
        if (ad == null) {
            return null;
        }
        ExtendedAd finalExtendedAdDto = new ExtendedAd();
        finalExtendedAdDto.setPk(ad.getId());
        finalExtendedAdDto.setAuthorFirstName(ad.getUserRelated().getFirstName());
        finalExtendedAdDto.setAuthorLastName(ad.getUserRelated().getLastName());
        finalExtendedAdDto.setDescription(ad.getDescription());
        finalExtendedAdDto.setEmail(ad.getUserRelated().getUsername());
        if (ad.getImageAd() != null) {
            finalExtendedAdDto.setImage("/ads/" + ad.getImageAd().getId() + "/adPicture");
        } else {
            finalExtendedAdDto.setImage(null);
        }
        finalExtendedAdDto.setPhone(ad.getUserRelated().getPhone());
        finalExtendedAdDto.setPrice(ad.getPrice());
        finalExtendedAdDto.setTitle(ad.getTitle());
        return finalExtendedAdDto;
    }

    @Override
    public AdEntity createOrUpdateAdDtoToAdEntity(CreateOrUpdateAd createOrUpdateAd) {
        if (createOrUpdateAd == null) {
            return null;
        }
        AdEntity modifiedAd = new AdEntity();
        modifiedAd.setPrice(createOrUpdateAd.getPrice());
        modifiedAd.setTitle(createOrUpdateAd.getTitle());
        modifiedAd.setDescription(createOrUpdateAd.getDescription());
        return modifiedAd;
    }

    @Override
    public List<ru.skypro.homework.dto.ads.Ad> adEntityToAdsDto(List<AdEntity> inputAdList) {
        if (inputAdList == null) {
            return null;
        }
        List<ru.skypro.homework.dto.ads.Ad> mapperList = new ArrayList<ru.skypro.homework.dto.ads.Ad>(inputAdList.size());
        for (AdEntity ad : inputAdList) {
            mapperList.add(adEntityToAdDto(ad));
        }
        return mapperList;
    }

    @Override
    public Ads userAdsToAdsDto(UserEntity user) {
        if (user == null) {
            return null;
        }

        Ads ads = new Ads();
        ads.setResults(adEntityToAdsDto(user.getAd()));
        ads.setCount(user.getAd().size());
        return ads;
    }
}
