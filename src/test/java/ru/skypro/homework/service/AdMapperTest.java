package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AdMapperTest {
    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Test
    void adToDto_ShouldMapAdEntityToAdDto() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);
        adEntity.setTitle("Test Ad");
        adEntity.setDescription("Test Description");
        adEntity.setPrice(100);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setLogin("testUser");
        adEntity.setAuthor(userEntity);

        Ad adDto = adMapper.adToDto(adEntity);

        assertEquals(adEntity.getId(), adDto.getPk());
        assertEquals(adEntity.getTitle(), adDto.getTitle());
        assertEquals(adEntity.getAuthor().getId(), adDto.getAuthor());
        assertEquals(adEntity.getPrice(), adDto.getPrice());
    }

    @Test
    void adToDtoList_ShouldMapCollectionOfAdEntitiesToAdsDto() {
        AdEntity adEntity1 = new AdEntity();
        adEntity1.setId(1);
        adEntity1.setTitle("Ad 1");
        adEntity1.setPrice(50);
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1);
        adEntity1.setAuthor(userEntity1);

        AdEntity adEntity2 = new AdEntity();
        adEntity2.setId(2);
        adEntity2.setTitle("Ad 2");
        adEntity2.setPrice(75);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2);
        adEntity2.setAuthor(userEntity2);

        Collection<AdEntity> adEntityList = Arrays.asList(adEntity1, adEntity2);

        Ads adsDto = adMapper.adToDtoList(adEntityList);

        Collection<Ad> adDtoList = adsDto.getResults();
        assertEquals(adEntityList.size(), adsDto.getCount());
        assertEquals(adEntityList.size(), adDtoList.size());
    }

    @Test
    void dtoToAd_ShouldMapCreateOrUpdateAdToAdEntity() {
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        createOrUpdateAd.setTitle("Test Ad");
        createOrUpdateAd.setDescription("Test Description");
        createOrUpdateAd.setPrice(100);

        AdEntity adEntity = adMapper.dtoToAd(createOrUpdateAd);

        assertEquals(createOrUpdateAd.getTitle(), adEntity.getTitle());
        assertEquals(createOrUpdateAd.getDescription(), adEntity.getDescription());
        assertEquals(createOrUpdateAd.getPrice(), adEntity.getPrice());
    }

    @Test
    void adToExtDto_ShouldMapAdEntityToExtendedAdDto() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);
        adEntity.setTitle("Test Ad");
        adEntity.setDescription("Test Description");
        adEntity.setPrice(100);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setLogin("testUser");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setPhone("123-456-7890");
        adEntity.setAuthor(userEntity);

        ExtendedAd extendedAdDto = adMapper.adToExtDto(adEntity);

        assertEquals(adEntity.getId(), extendedAdDto.getPk());
        assertEquals(adEntity.getTitle(), extendedAdDto.getTitle());
        assertEquals(adEntity.getDescription(), extendedAdDto.getDescription());
        assertEquals(adEntity.getPrice(), extendedAdDto.getPrice());
        assertEquals(userEntity.getFirstName(), extendedAdDto.getAuthorFirstName());
        assertEquals(userEntity.getLastName(), extendedAdDto.getAuthorLastName());
        assertEquals(userEntity.getLogin(), extendedAdDto.getEmail());
        assertEquals(userEntity.getPhone(), extendedAdDto.getPhone());
    }
}
