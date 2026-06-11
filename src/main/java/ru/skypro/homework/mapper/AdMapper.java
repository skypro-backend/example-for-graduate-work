package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ads.AdDto;
import ru.skypro.homework.dto.Ads.AdsDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdMapper {

    public AdDto toDto(Ad ad) {
        if (ad == null) {
            return null;
        }

        AdDto dto = new AdDto();
        dto.setPk(toInt(ad.getId()));
        dto.setTitle(ad.getTitle());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImage());

        if (ad.getAuthor() != null) {
            dto.setAuthor(toInt(ad.getAuthor().getId()));
        }

        return dto;
    }

    public ExtendedAdDto toExtendedDto(Ad ad) {
        if (ad == null) {
            return null;
        }

        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(toInt(ad.getId()));
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImage());

        User author = ad.getAuthor();
        if (author != null) {
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorLastName(author.getLastName());
            dto.setEmail(author.getEmail());
            dto.setPhone(author.getPhone());
        }

        return dto;
    }

    public Ad toEntity(CreateOrUpdateAdDto adDto) {
        if (adDto == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setDescription(adDto.getDescription());
        ad.setPrice(adDto.getPrice());

        return ad;
    }

    public void updateEntity(CreateOrUpdateAdDto adDto, Ad ad) {
        if (adDto == null || ad == null) {
            return;
        }

        ad.setTitle(adDto.getTitle());
        ad.setDescription(adDto.getDescription());
        ad.setPrice(adDto.getPrice());
    }

    public List<AdDto> toDtoList(List<Ad> ads) {
        if (ads == null) {
            return null;
        }
        return ads.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AdsDto toAdsDto(List<Ad> ads) {
        List<AdDto> adDtos = toDtoList(ads);
        int count = adDtos != null ? adDtos.size() : 0;
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(count);
        adsDto.setResults(adDtos != null ? adDtos : List.of());
        return adsDto;
    }

    private Integer toInt(Long value) {
        return value == null ? null : value.intValue();
    }
}
