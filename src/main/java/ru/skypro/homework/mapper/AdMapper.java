package ru.skypro.homework.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

@Component
public class AdMapper {

    public ExtendedAdDto toDto(@NonNull Ad ad) {
        ExtendedAdDto adDto = new ExtendedAdDto();

        adDto.setPk(ad.getPk());
        adDto.setTitle(ad.getTitle());
        adDto.setDescription(ad.getDescription());
        adDto.setPrice(ad.getPrice());
        adDto.setImage(ad.getImage());
        adDto.setAuthorFirstName(ad.getAuthor().getFirstName());
        adDto.setAuthorLastName(ad.getAuthor().getLastName());

        return adDto;
    }

    public Ad toEntity(ExtendedAdDto adDto) {
        Ad ad = new Ad();

        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setDescription(adDto.getDescription());
        ad.setImage(adDto.getImage());

        return ad;
    }

}
