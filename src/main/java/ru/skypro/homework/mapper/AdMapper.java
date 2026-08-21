package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

@Component
public class AdMapper {

    public Ad toAdDto(AdEntity entity) {
        if (entity == null) {
            return null;
        }

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

    public ExtendedAd toExtendedAdDto(AdEntity entity) {
        if (entity == null) {
            return null;
        }

        ExtendedAd dto = new ExtendedAd();
        dto.setPk(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());

        if (entity.getAuthor() != null) {
            dto.setAuthorFirstName(entity.getAuthor().getFirstName());
            dto.setAuthorLastName(entity.getAuthor().getLastName());
            dto.setEmail(entity.getAuthor().getEmail());
            dto.setPhone(entity.getAuthor().getPhone());
        }

        return dto;
    }

    public AdEntity toEntity(CreateOrUpdateAd dto) {
        if (dto == null) {
            return null;
        }

        AdEntity entity = new AdEntity();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
