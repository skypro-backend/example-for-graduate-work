package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

public interface AdMapper {

    public List<ru.skypro.homework.dto.ads.Ad> adEntityToAdsDto(List<AdEntity> inputAdList);

    public Ad adEntityToAdDto(AdEntity ad);

    public ExtendedAd adEntityToExtendedAdDto(AdEntity ad);

    public AdEntity createOrUpdateAdDtoToAdEntity(CreateOrUpdateAd createOrUpdateAd);

    public Ads userAdsToAdsDto(UserEntity user);
}
