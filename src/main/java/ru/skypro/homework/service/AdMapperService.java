package ru.skypro.homework.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Component
public class AdMapperService {
    // Метод для преобразования DTO в сущность для сохранения в базе данных.
    public Ad mapToEntity(AdDto adDTO, User user) {
        Ad adEntity = new Ad();
        adEntity.setPk(adDTO.getPk());
        adEntity.setUser(user);
        adEntity.setImageAddress(adDTO.getImage());
        adEntity.setPrice(adDTO.getPrice());
        adEntity.setTitle(adDTO.getTitle());
                adEntity.setDescription("default description");
        return adEntity;
    }
    // Метод для преобразования сущности из базы данных в DTO для ответа клиенту.
    public AdDto mapToDto(Ad ad) {
        AdDto adDTO = new AdDto();
        adDTO.setPk(ad.getPk());
        adDTO.setAuthor(ad.getUser().getId());
        adDTO.setImage(ad.getImageAddress());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }
    public ExtendedAdDto mapToExtendedDto (Ad ad){
        ExtendedAdDto extendedAdDto = new ExtendedAdDto();
        extendedAdDto.setPk(ad.getPk());
        extendedAdDto.setAuthorFirstName(ad.getUser().getFirstName());
        extendedAdDto.setAuthorLastName(ad.getUser().getLastName());
        extendedAdDto.setDescription(ad.getDescription());
        extendedAdDto.setEmail(ad.getUser().getEmail());
        extendedAdDto.setImage(ad.getImageAddress());
        extendedAdDto.setPhone(ad.getUser().getPhone());
        extendedAdDto.setPrice(ad.getPrice());
        extendedAdDto.setTitle(ad.getTitle());
        return extendedAdDto;
    }
}
