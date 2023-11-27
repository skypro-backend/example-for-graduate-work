package ru.skypro.homework.mapper;

import lombok.Builder;
import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Mapper(componentModel = "spring")
public class AdMapper {
    public Ad AdEntityToAd(AdEntity adEntity) {
        if (adEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании Ad! AdEntity == null!");
        }
        return Ad.builder()
                .pk(adEntity.getPk())
                .author(adEntity.getUserEntity().getId())
                .price(adEntity.getPrice())
                .title(adEntity.getTitle())
                .image(adEntity.getImageEntity().getFilePath())
                .build();
    }

    //    public AdEntity AdEntityToCreateOrUpdateAd(CreateOrUpdateAd adEntity) {
//        if ( adEntity == null ) {
//            throw new NullPointerException("Ошибка маппера при создании CreateOrUpdateAd! AdEntity == null!");
//        }
//        return AdEntity.builder()
//                .price(adEntity.getPrice())
//                .title(adEntity.getTitle())
//                .description(adEntity.getDescription())
//                .build();
//    }
    public ExtendedAd AdEntityToExtendedAd(AdEntity adEntity) {
        if (adEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании ExtendedAd! AdEntity == null!");
        }
        return ExtendedAd.builder()
                .pk(adEntity.getPk())
                .authorFirstName(adEntity.getUserEntity().getFirstName())
                .authorFirstName(adEntity.getUserEntity().getLastName())
                .description(adEntity.getDescription())
                .email(adEntity.getUserEntity().getEmail())
                .price(adEntity.getPrice())
                .title(adEntity.getTitle())
                .image(adEntity.getImageEntity().getFilePath())
                .build();
    }

    public AdEntity AdToAdEntity(CreateOrUpdateAd dto) {
        if (dto == null) {
            throw new NullPointerException(" Ошибка маппера при создании AdEntity! AdDto == null! ");
        }
        return AdEntity.builder()
                .price(dto.getPrice())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    List<Ad> adEntityListToAdList(List<AdEntity> adEntityList) {
        if (adEntityList == null) {
            throw new NullPointerException("Ошибка маппера при создании List<Ad>! AList<AdEntity> == null!");
        }
        return adEntityList.stream()
                .map(this::AdEntityToAd)
                .collect(Collectors.toList());
    }

}
