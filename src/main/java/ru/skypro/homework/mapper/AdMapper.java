package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

public class AdMapper {
    public static AdDTO fromAd(Ad ad) {
        return new AdDTO()
                .setPk(ad.getPk())
                .setAuthor(ad.getUser().getId())
                .setImage(ad.getImage())
                .setPrice(ad.getPrice())
                .setTitle(ad.getTitle())
                .setDescription(ad.getDescription());
    }

}
