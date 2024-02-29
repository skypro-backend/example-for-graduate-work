package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.ExtendedAdDTO;
import ru.skypro.sitesforresaleofthings.entity.Ad;
import ru.skypro.sitesforresaleofthings.entity.User;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */
@Service
public class AdMapper {

    public Ad toEntity(CreateOrUpdateAdDTO dto, User author) {
        Ad ad = new Ad();
        ad.setDescription(dto.getDescription());
        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setAuthor(author);
        return ad;
    }

    public AdDTO toDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setPk(ad.getPk());
        adDTO.setAuthor(ad.getAuthor().getId());
        adDTO.setImage(ad.getImage());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        if (ad.getImage() != null) {
            adDTO.setImage(String.format("/ads/image/%s", ad.getImage()));
        } else {
            adDTO.setImage(null);
        }
        return adDTO;
    }

    public ExtendedAdDTO extendedAdDTOtoDTO(Ad ad) {
        ExtendedAdDTO extendedAdDTO = new ExtendedAdDTO();
        extendedAdDTO.setPk(ad.getPk());
        extendedAdDTO.setAuthorFirstName(ad.getAuthor().getFirstName());
        extendedAdDTO.setAuthorLastName(ad.getAuthor().getLastName());
        extendedAdDTO.setDescription(ad.getDescription());
        extendedAdDTO.setEmail(ad.getAuthor().getEmail());
        extendedAdDTO.setPhone(ad.getAuthor().getPhone());
        extendedAdDTO.setPrice(ad.getPrice());
        extendedAdDTO.setTitle(ad.getTitle());
        if (ad.getImage() != null) {
            extendedAdDTO.setImage(String.format("/ads/image/%s", ad.getImage()));
        } else {
            extendedAdDTO.setImage(null);
        }
        return extendedAdDTO;
    }
}