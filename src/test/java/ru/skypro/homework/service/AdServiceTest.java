package ru.skypro.homework.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.util.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AdServiceTest {
    @Mock
    AdRepository repository;
    @Mock
    UserRepo userRepository;
    @Mock
    AdMapper mapper;
    @Mock
    WebSecurityService webSecurityService;

    @InjectMocks
    AdServiceImpl adService;

    @Mock
    MultipartFile image;

    @Test
    void getAllAdsWithAds() {
        AdEntity ad1 = new AdEntity();
        AdEntity ad2 = new AdEntity();
        when(repository.findAll()).thenReturn(Arrays.asList(ad1, ad2));

        Ad dto1 = new Ad();
        Ad dto2 = new Ad();
        Ads dtos = new Ads();
        dtos.setResults(Arrays.asList(dto1, dto2));
        when(mapper.adToDtoList(Arrays.asList(ad1, ad2))).thenReturn(dtos);

        Ads result = adService.getAllAds();
        Assertions.assertEquals(dtos, result);
    }

    @Test
    void getAllAdsWithoutAds() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> {
            adService.getAllAds();
        });
    }

    @Test
    void getExtAd_WhenAdExists_ShouldReturnExtendedAd() {
        Integer adId = 1;
        AdEntity adEntity = new AdEntity();
        ExtendedAd extendedAd = new ExtendedAd();

        when(repository.findById(adId)).thenReturn(Optional.of(adEntity));
        when(mapper.adToExtDto(adEntity)).thenReturn(extendedAd);


        ExtendedAd result = adService.getExtAd(adId);

        verify(repository, times(1)).findById(adId);
        verify(mapper, times(1)).adToExtDto(adEntity);
        Assertions.assertEquals(extendedAd, result);
    }

    @Test
    void getExtAd_WhenAdDoesNotExist_ShouldReturnNull() {

        Integer adId = 1;

        when(repository.findById(adId)).thenReturn(Optional.empty());

        ExtendedAd result = adService.getExtAd(adId);

        verify(repository, times(1)).findById(adId);
        verify(mapper, never()).adToExtDto(any());
        Assertions.assertNull(result);
    }

    @Test
    void deleteAd_WhenAdExistsAndUserCanAccess_ShouldReturnDeletedAd() {
        Integer adId = 1;
        AdEntity adEntity = new AdEntity();
        Ad adDto = new Ad();

        when(repository.findById(adId)).thenReturn(Optional.of(adEntity));
        when(mapper.adToDto(adEntity)).thenReturn(adDto);

        Ad result = adService.deleteAd(adId);

        verify(repository, times(1)).findById(adId);
        verify(repository, times(1)).deleteById(adId);
        verify(mapper, times(1)).adToDto(adEntity);
        Assertions.assertEquals(adDto, result);
    }

    @Test
    void deleteAd_WhenAdDoesNotExist_ShouldReturnNull() {
        Integer adId = 1;

        when(repository.findById(adId)).thenReturn(Optional.empty());

        Ad result = adService.deleteAd(adId);

        verify(repository, times(1)).findById(adId);
        verify(repository, never()).deleteById(any());
        verify(mapper, never()).adToDto(any());
        Assertions.assertNull(result);
    }
    @Test
    void pathAd_WhenAdExists_ShouldReturnUpdatedAd() {

        Integer adId = 1;
        CreateOrUpdateAd updateAd = new CreateOrUpdateAd();
        updateAd.setTitle("Updated Title");
        updateAd.setDescription("Updated Description");
        updateAd.setPrice(100);

        AdEntity existingAdEntity = new AdEntity();
        existingAdEntity.setId(adId);
        existingAdEntity.setTitle("Original Title");
        existingAdEntity.setDescription("Original Description");
        existingAdEntity.setPrice(50);

        AdEntity updatedAdEntity = new AdEntity();
        updatedAdEntity.setId(adId);
        updatedAdEntity.setTitle("Updated Title");
        updatedAdEntity.setDescription("Updated Description");
        updatedAdEntity.setPrice(100);

        Ad updatedAdDto = new Ad();
        updatedAdDto.setPk(adId);
        updatedAdDto.setTitle("Updated Title");
        updatedAdDto.setPrice(100);

        when(repository.findById(adId)).thenReturn(Optional.of(existingAdEntity));
        when(repository.save(any())).thenReturn(updatedAdEntity);
        when(mapper.adToDto(updatedAdEntity)).thenReturn(updatedAdDto);

        Ad result = adService.pathAd(updateAd, adId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedAdDto, result);
        Assertions.assertEquals(updateAd.getTitle(), result.getTitle());
        Assertions.assertEquals(updateAd.getPrice(), result.getPrice());

        verify(repository, times(1)).findById(adId);
        verify(repository, times(1)).save(existingAdEntity);
    }

    @Test
    void pathAd_WhenAdDoesNotExist_ShouldReturnNull() {

        Integer adId = 1;
        CreateOrUpdateAd updateAd = new CreateOrUpdateAd();
        updateAd.setTitle("Updated Title");
        updateAd.setDescription("Updated Description");
        updateAd.setPrice(100);

        when(repository.findById(adId)).thenReturn(Optional.empty());

        Ad result = adService.pathAd(updateAd, adId);

        Assertions.assertNull(result);

        verify(repository, times(1)).findById(adId);
        verify(repository, never()).save(any());
    }
}