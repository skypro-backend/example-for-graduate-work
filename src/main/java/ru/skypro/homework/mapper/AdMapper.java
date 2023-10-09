package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.projection.AdView;

public class AdMapper {

    public static AdDTO fromAd(Ad ad) {
        return new AdDTO()
                .setPk(ad.getPk())
                .setAuthor(UserMapper.toDTO(ad.getUser()))
                .setImage(ad.getImage())
                .setPrice(ad.getPrice())
                .setTitle(ad.getTitle())
                .setDescription(ad.getDescription());
    }

    public static Ad toAd(AdDTO adDTO) {
        return new Ad()
                .setPk(adDTO.getPk())
                .setUser(UserMapper.fromDTO(adDTO.getAuthor()))
                .setImage(adDTO.getImage())
                .setPrice(adDTO.getPrice())
                .setTitle(adDTO.getTitle())
                .setDescription(adDTO.getDescription());
    }

    public static AdView toView(Ad ad) {
        return new AdView()
                .setAuthor(ad.getUser().getId())
                .setPk(ad.getPk())
                .setTitle(ad.getTitle())
                .setPrice(ad.getPrice())
                .setImage(ad.getImage());
    }
}
