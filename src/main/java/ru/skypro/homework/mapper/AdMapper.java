package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;

@Service
public class AdMapper {

    /**
     * Entity -> dto mapping
     * @param entity AdEntity entity class
     * @return Ad dto class
     */
    public Ad mapToAdDto(AdEntity entity){
        Ad dto = new Ad();
        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(entity.getPhoto().getFilePath()); // надо продумать этот момент
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    /**
     * AdEntity entity -> ExtendedAd dto mapping
     * @param entity AdEntity entity class
     * @return ExtendedAd dto class
     */
    public ExtendedAd mapToExtendedAdDto(AdEntity entity) {
        ExtendedAd dto = new ExtendedAd();
        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUserName());
        dto.setImage(entity.getPhoto().getFilePath()); // надо продумать этот момент
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getPhoto().getFilePath()); // надо продумать этот момент
        return dto;
    }
}
