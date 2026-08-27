package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

import java.util.Optional;

@Component
public class AdMapper {

    public Optional<Ad> toAdDto(AdEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    Ad dto = new Ad();
                    dto.setPk(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setPrice(e.getPrice());
                    if (e.getImage() != null) {
                        dto.setImage("/images/" + e.getImage().getId());
                    } else {
                        dto.setImage(null);
                    }
                    if (e.getAuthor() != null) {
                        dto.setAuthor(e.getAuthor().getId());
                    }
                    return dto;
                });
    }

    public Optional<ExtendedAd> toExtendedAdDto(AdEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    ExtendedAd dto = new ExtendedAd();
                    dto.setPk(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setPrice(e.getPrice());
                    dto.setDescription(e.getDescription());
                    if (e.getImage() != null) {
                        dto.setImage("/images/" + e.getImage().getId());
                    } else {
                        dto.setImage(null);
                    }
                    if (e.getAuthor() != null) {
                        dto.setAuthorFirstName(e.getAuthor().getFirstName());
                        dto.setAuthorLastName(e.getAuthor().getLastName());
                        dto.setEmail(e.getAuthor().getEmail());
                        dto.setPhone(e.getAuthor().getPhone());
                    }
                    return dto;
                });
    }


    public Optional<AdEntity> toEntity(CreateOrUpdateAd dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    AdEntity entity = new AdEntity();
                    entity.setTitle(d.getTitle());
                    entity.setPrice(d.getPrice());
                    entity.setDescription(d.getDescription());
                    return entity;
                });
    }
}
