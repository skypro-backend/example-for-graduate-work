package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdMapper {
    public AdDTO toAdDTO(Ad ad) {
        User author = ad.getAuthor();
        Image image = ad.getImage();
        return new AdDTO(
                ad.getId(),
                ad.getTitle(),
                ad.getPrice(),
                image != null ? "/images/" + ad.getImage().getId() : null,
                author != null ? author.getId() : null
        );
    }

    public ExtendedAd toExtendedAd(Ad ad) {
        User author = ad.getAuthor();
        Image image = ad.getImage();
        return new ExtendedAd(
                ad.getId(),
                author != null ? author.getFirstName() : null,
                author != null ? author.getLastName() : null,
                ad.getDescription(),
                author != null ? author.getEmail() : null,
                image != null ? "/images/" + ad.getImage().getId() : null,
                author != null ? author.getPhone() : null,
                ad.getPrice(),
                ad.getTitle()
        );
    }

    public Ads toAds(List<Ad> ads) {
        return new Ads(
                (long) ads.size(),
                ads.stream().map(this::toAdDTO)
                        .collect(Collectors.toList())
        );
    }

    public Ad toAd(CreateOrUpdateAd dto) {
        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        return ad;
    }
}
