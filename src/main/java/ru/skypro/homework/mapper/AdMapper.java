package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdvertisementEntity;

@Component
public class AdMapper {

    public Ad toDto(AdvertisementEntity entity) {
        Ad dto = new Ad();
        dto.setPk(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setImage(entity.getImage());
        if (entity.getAuthor() != null) {
            dto.setAuthor(entity.getAuthor().getId());
        }
        return dto;
    }
    public AdvertisementEntity toEntity(CreateOrUpdateAd dto) {
        AdvertisementEntity entity = new AdvertisementEntity();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
