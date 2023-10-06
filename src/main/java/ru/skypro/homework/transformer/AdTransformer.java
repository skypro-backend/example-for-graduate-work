package ru.skypro.homework.transformer;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Component
public class AdTransformer {

    public Ad adEntityToAd(AdEntity adEntity) {
        if (adEntity == null) {
            return null;
        }
        Ad ad = new Ad();
        ad.setAuthor(adEntity.getAuthor().getId());
        ad.setImage(null);//todo: change to returning image url
        ad.setPk(adEntity.getId());
        ad.setPrice(adEntity.getPrice());
        ad.setTitle(adEntity.getTitle());
        return ad;
    }

    public AdEntity adToAdEntity(Ad ad, UserEntity author) {

        if (ad == null) {
            return null;
        }
        AdEntity adEntity = new AdEntity();
        adEntity.setPrice(ad.getPrice());
        adEntity.setTitle(ad.getTitle());
        adEntity.setAuthor(author);
        adEntity.setImage(null);//todo: change to returning image url
        return adEntity;
    }

    public ExtendedAd adEntityToExtendedAd(AdEntity adEntity) {
        if (adEntity == null) {
            return null;
        }
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(adEntity.getId());
        extendedAd.setAuthorFirstName(adEntity.getAuthor().getFirstName());
        extendedAd.setAuthorLastName(adEntity.getAuthor().getLastName());
        extendedAd.setDescription(adEntity.getDescription());
        extendedAd.setEmail(adEntity.getAuthor().getUsername());
        extendedAd.setImage(null);//todo: change to returning image url
        extendedAd.setPhone(adEntity.getAuthor().getPhone());
        extendedAd.setPrice(adEntity.getPrice());
        extendedAd.setTitle(adEntity.getTitle());
        return extendedAd;
    }

    public AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd, UserEntity author) {

        if (createOrUpdateAd == null) {
            return null;
        }
        AdEntity adEntity = new AdEntity();
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setAuthor(author);
        adEntity.setImage(null);//todo: change to returning image url
        adEntity.setDescription(createOrUpdateAd.getDescription());
        return adEntity;
    }
}

