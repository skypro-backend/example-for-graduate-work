package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

public interface AdMapper {

    public List<ru.skypro.homework.dto.ads.Ad> adEntityToAdsDto(List<Ad> inputAdList);

    ru.skypro.homework.dto.ads.Ad adEntityToAdDto(Ad ad);

    public ExtendedAd adEntityToExtendedAdDto(Ad ad);

    public Ad createOrUpdateAdDtoToAdEntity(CreateOrUpdateAd createOrUpdateAd);

    public Ads userAdsToAdsDto(UserEntity user);
}
