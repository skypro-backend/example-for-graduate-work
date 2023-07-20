package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

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
        adDTO.setAuthor(adEntity.getUser().getUserId());
        adDTO.setImage(adEntity.getImageAddress());
        adDTO.setPrice(adEntity.getPrice());
        adDTO.setTitle(adEntity.getTitle());
        return adDTO;
    }
}
