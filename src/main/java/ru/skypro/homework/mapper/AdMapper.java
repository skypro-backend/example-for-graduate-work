package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
@Component
public class AdMapper {
    public Ad AdEntityToAd(AdEntity adEntity) {
        if (adEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании Ad! AdEntity == null!");
        }
        return Ad.builder()
                .pk(adEntity.getId())
                .author(adEntity.getUserEntity().getId())
                .price(adEntity.getPrice())
                .title(adEntity.getTitle())
                .image("/image/"+ adEntity.getImageEntity().getId())
                .build();
    }

    public ExtendedAd AdEntityToExtendedAd(AdEntity adEntity) {
        if (adEntity == null) {
            throw new NullPointerException("Ошибка маппера при создании ExtendedAd! AdEntity == null!");
        }
        return ExtendedAd.builder()
                .pk(adEntity.getId())
                .authorFirstName(adEntity.getUserEntity().getFirstName())
                .authorFirstName(adEntity.getUserEntity().getLastName())
                .description(adEntity.getDescription())
                .email(adEntity.getUserEntity().getUsername())
                .phone(adEntity.getUserEntity().getPhone())
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

    public List<Ad> adEntityListToAdList(List<AdEntity> adEntityList) {
        if (adEntityList == null) {
            throw new NullPointerException("Ошибка маппера при создании List<Ad>! AList<AdEntity> == null!");
        }
        return adEntityList.stream()
                .map(this::AdEntityToAd)
                .collect(Collectors.toList());
    }

}
