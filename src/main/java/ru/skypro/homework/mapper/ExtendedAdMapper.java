package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.UpdateAd;

@Component
public class ExtendedAdMapper {
    public ExtendedAd mapToExtendedAd(ExtendedAd extendedAd) {
        return new ExtendedAd(
                ExtendedAd.getPk(),
                ExtendedAd.getAuthor(),
                ExtendedAd.getImage(),
                ExtendedAd.getPrice(),
                ExtendedAd.getTitle());
    }
}

