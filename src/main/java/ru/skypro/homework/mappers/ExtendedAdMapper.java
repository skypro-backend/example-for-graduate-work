package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
@Component
public class ExtendedAdMapper {
    public static ExtendedAdDto toDto(Ad ad) {
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(ad.getId());
        dto.setAuthorFirstName(ad.getAuthor().getFirstName());
        dto.setAuthorLastName(ad.getAuthor().getLastName());
        dto.setDescription(ad.getDescription());
        dto.setEmail(ad.getAuthor().getEmail());
        dto.setPhone(ad.getAuthor().getPhone());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());
        dto.setImage("/image/" + ad.getImageUrl());
        return dto;
    }
}
