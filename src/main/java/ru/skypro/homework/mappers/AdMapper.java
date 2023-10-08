package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.entities.AdEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class AdMapper{

    public AdDTO toAdDto(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        AdDTO.AdDTOBuilder adDTO = AdDTO.builder();

        adDTO.pk( ad.getPk() );
        adDTO.price( ad.getPrice() );
        adDTO.title( ad.getTitle() );
        //adDTO.image( ad.getImage() );

        return adDTO.build();
    }

    public CreateOrUpdateAdDTO toCreateOrUpdateDto(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        CreateOrUpdateAdDTO.CreateOrUpdateAdDTOBuilder createOrUpdateAdDTO = CreateOrUpdateAdDTO.builder();

        createOrUpdateAdDTO.title( ad.getTitle() );
        createOrUpdateAdDTO.price( ad.getPrice() );
        createOrUpdateAdDTO.description( ad.getDescription() );

        return createOrUpdateAdDTO.build();
    }

    public ExtendedAdDTO toExtendedAdDTO(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        ExtendedAdDTO.ExtendedAdDTOBuilder extendedAdDTO = ExtendedAdDTO.builder();

        extendedAdDTO.pk( ad.getPk() );
        extendedAdDTO.description( ad.getDescription() );
        extendedAdDTO.image( ad.getImage() );
        extendedAdDTO.phone( ad.getPhone() );
        extendedAdDTO.price( ad.getPrice() );
        extendedAdDTO.title( ad.getTitle() );

        return extendedAdDTO.build();
    }

    public List<ExtendedAdDTO> toListExtendedAdDTO(List<AdEntity> ad) {
        if ( ad == null ) {
            return null;
        }

        List<ExtendedAdDTO> list = new ArrayList<ExtendedAdDTO>( ad.size() );
        for ( AdEntity adEntity : ad ) {
            list.add( toExtendedAdDTO( adEntity ) );
        }

        return list;
    }

    public AdsDTO toAdsDto(List<AdEntity> ad) {
        if ( ad == null ) {
            return null;
        }
        List<AdDTO> adDTO = new ArrayList<>(ad.size());
        for (AdEntity adEntity : ad) {
            adDTO.add(toAdDto(adEntity));
        }
        return new AdsDTO(ad.size(),adDTO);
    }
}
