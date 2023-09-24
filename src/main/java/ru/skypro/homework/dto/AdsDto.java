package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class AdsDto {
    private Integer count; //($int32) общее кол-во объявлений
    private List<AdDto> results;

    public AdsDto fromAdsList(List<Ad> adList) {
        List<AdDto> adDtos = new ArrayList<>();
        Integer count = 0;
        for (Ad ad : adList) {
            adDtos.add(AdDto.fromAd(ad));
            count++;
        }
        AdsDto adsDto = new AdsDto(count, adDtos);
        return adsDto;
    }
}
