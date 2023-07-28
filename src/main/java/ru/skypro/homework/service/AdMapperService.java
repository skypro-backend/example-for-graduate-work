package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public AdDto mapToDto(Ad adEntity) {
        AdDto adDTO = new AdDto();
        adDTO.setPk(adEntity.getPk());
        adDTO.setAuthor(adEntity.getUser().getId());
        adDTO.setImage(adEntity.getImageAddress());
        adDTO.setPrice(adEntity.getPrice());
        adDTO.setTitle(adEntity.getTitle());
        return adDTO;
    }
    public ExtendedAdDto mapToExtendedDto (Ad ad){
        ExtendedAdDto extendedAdDto = new ExtendedAdDto();
        extendedAdDto.setPk(ad.getPk());
        extendedAdDto.setAuthorFirstName(ad.getUser().getFirstName());
        extendedAdDto.setAuthorLastName(ad.getUser().getLastName());
        extendedAdDto.setDescription(ad.getDescription());
        extendedAdDto.setEmail(ad.getUser().getUsername());
        extendedAdDto.setImage(ad.getImageAddress());
        extendedAdDto.setPhone(ad.getUser().getPhone());
        extendedAdDto.setPrice(ad.getPrice());
        extendedAdDto.setTitle(ad.getTitle());
        return extendedAdDto;
    }
    public List<AdDto> adListToAdDTOList(List<Ad> adList) {
        List<AdDto> adDTOList = adList.stream().map(this:: mapToDto).collect(Collectors.toList());
        return adDTOList;
    }
}
