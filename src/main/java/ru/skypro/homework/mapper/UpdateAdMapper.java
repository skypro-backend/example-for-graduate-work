package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateAd;

@Component
public class UpdateAdMapper {
    public UpdateAd mapToUpdateAd(UpdateAd updateAd) {
        return new UpdateAd(
                UpdateAd.getPk(),
                UpdateAd.getAuthor(),
                UpdateAd.getImage(),
                UpdateAd.getPrice(),
                UpdateAd.getTitle());
    }
}
