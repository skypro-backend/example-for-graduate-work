package ru.skypro.homework.mapper;


import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdMapper {



    public AdDto toAdDto(Ad ad) {
        AdDto adDto = new AdDto();

        adDto.setPk(ad.getPk());
        adDto.setAuthor(ad.getUser().getId());
        adDto.setImage("/" + ad.getPk() + "/image");
        adDto.setPrice((ad.getPrice()));
        adDto.setTitle(ad.getTitle());

        return adDto;
    }

    public AdsDto toAdsDto(List<Ad> ads) {
        AdsDto adsDto = new AdsDto();
        List<AdDto> adDtoList = ads.stream()
                .map(this::toAdDto)
                .collect(Collectors.toList());

        adsDto.setCount(adDtoList.size());
        adsDto.setResult(adDtoList);

        return adsDto;
    }

    public Ad toEntity (CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = new Ad();

        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        ad.setPrice(createOrUpdateAdDto.getPrice());

        return ad;

    }

    public ExtendedAdDto toExtendedAdDto(Ad ad) {
        ExtendedAdDto extendedAdDto = new ExtendedAdDto();

        extendedAdDto.setPk(ad.getPk());
        extendedAdDto.setAuthorFirstName(ad.getUser().getFirstName());
        extendedAdDto.setAuthorLastName(ad.getUser().getLastName());
        extendedAdDto.setDescription(ad.getDescription());
        extendedAdDto.setEmail(ad.getUser().getEmail());
        extendedAdDto.setImage("/" + ad.getPk() + "/image");
        extendedAdDto.setPhone(ad.getUser().getPhone());
        extendedAdDto.setPrice(ad.getPrice());
        extendedAdDto.setTitle(ad.getTitle());

        return extendedAdDto;
    }
}
