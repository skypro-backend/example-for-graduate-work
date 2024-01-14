package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

@Component
public class AdMapper {

    public AdDto adToDto(Ad ad) {
        return AdDto.builder()
                .pk(ad.getId())
                .author(ad.getAuthor().getId())
                .image("/image/" + ad.getImage().getId())
                .price(ad.getPrice())
                .title(ad.getTittle())
                .build();
    }

    public ExtendedAdDto extAdToDto(Ad ad) {
        return ExtendedAdDto.builder()
                .pk(ad.getId())
                .authorFirstName(ad.getAuthor().getFirstName())
                .authorLastName(ad.getAuthor().getLastName())
                .description(ad.getDescription())
                .email(ad.getAuthor().getEmail())
                .phone(ad.getAuthor().getPhone())
                .image("/image/" + ad.getImage().getId())
                .price(ad.getPrice())
                .title(ad.getTittle())
                .build();
    }

    public CreateOrUpdateAdDto updateAdToDto(Ad ad) {
        return CreateOrUpdateAdDto.builder()
                .title(ad.getTittle())
                .price(ad.getPrice())
                .description(ad.getDescription())
                .build();
    }

    public Ad adToEntity(CreateOrUpdateAdDto dto) {
        return Ad.builder()
                .tittle(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }
}
