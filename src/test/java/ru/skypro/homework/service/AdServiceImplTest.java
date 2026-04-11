package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты сервиса объявлений")
class AdServiceImplTest {

    @Mock
    private AdvertisementRepository adRepository;

    @Mock
    private AdMapper adMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private AdServiceImpl adService;

    @Test
    @DisplayName("createAd - успешное создание объявления с изображением")
    void createAd_WithImage_Success() {
        String email = "user@example.com";
        CreateOrUpdateAd adDto = new CreateOrUpdateAd();
        adDto.setTitle("Test Ad");
        adDto.setPrice(1000);
        adDto.setDescription("Test Description");

        MockMultipartFile image = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg", "test".getBytes()
        );

        UserEntity author = new UserEntity();
        author.setEmail(email);

        AdvertisementEntity entity = new AdvertisementEntity();
        AdvertisementEntity savedEntity = new AdvertisementEntity();
        savedEntity.setId(1);
        savedEntity.setTitle("Test Ad");

        Ad expectedAd = new Ad();
        expectedAd.setPk(1);
        expectedAd.setTitle("Test Ad");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(author));
        when(adMapper.toEntity(adDto)).thenReturn(entity);
        when(imageService.saveImage(image)).thenReturn("/images/test.jpg");
        when(adRepository.save(entity)).thenReturn(savedEntity);
        when(adMapper.toDto(savedEntity)).thenReturn(expectedAd);

        Ad result = adService.createAd(adDto, image, email);

        assertThat(result).isNotNull();
        assertThat(result.getPk()).isEqualTo(1);
        verify(adRepository).save(entity);
    }

    @Test
    @DisplayName("createAd - ошибка при создании объявления без автора")
    void createAd_UserNotFound_ThrowsException() {
        String email = "nonexistent@example.com";
        CreateOrUpdateAd adDto = new CreateOrUpdateAd();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adService.createAd(adDto, null, email))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("getAdById - успешное получение объявления по ID")
    void getAdById_ValidId_ReturnsExtendedAd() {
        Integer adId = 1;
        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);
        ad.setTitle("Test Ad");
        ad.setPrice(1000);

        UserEntity author = new UserEntity();
        author.setFirstName("Иван");
        author.setLastName("Иванов");
        author.setEmail("author@example.com");
        author.setPhone("+79991234567");
        ad.setAuthor(author);

        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));

        var result = adService.getAdById(adId);

        assertThat(result).isNotNull();
        assertThat(result.getPk()).isEqualTo(adId);
        assertThat(result.getTitle()).isEqualTo("Test Ad");
    }

    @Test
    @DisplayName("getAdById - ошибка при поиске несуществующего объявления")
    void getAdById_InvalidId_ThrowsException() {
        Integer adId = 999;

        when(adRepository.findById(adId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adService.getAdById(adId))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("updateAd - успешное обновление объявления владельцем")
    void updateAd_ByOwner_Success() {
        Integer adId = 1;
        String email = "owner@example.com";
        CreateOrUpdateAd request = new CreateOrUpdateAd();
        request.setTitle("Updated Title");
        request.setPrice(2000);

        UserEntity author = new UserEntity();
        author.setEmail(email);

        AdvertisementEntity existingAd = new AdvertisementEntity();
        existingAd.setId(adId);
        existingAd.setAuthor(author);
        existingAd.setTitle("Old Title");
        existingAd.setPrice(1000);

        AdvertisementEntity updatedAd = new AdvertisementEntity();
        updatedAd.setId(adId);
        updatedAd.setTitle("Updated Title");
        updatedAd.setPrice(2000);

        Ad expectedDto = new Ad();
        expectedDto.setPk(adId);
        expectedDto.setTitle("Updated Title");

        when(adRepository.findById(adId)).thenReturn(Optional.of(existingAd));
        when(adRepository.save(any(AdvertisementEntity.class))).thenReturn(updatedAd);
        when(adMapper.toDto(updatedAd)).thenReturn(expectedDto);

        Ad result = adService.updateAd(adId, request, email);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("deleteAd - успешное удаление объявления")
    void deleteAd_ByOwner_Success() {
        Integer adId = 1;
        String email = "owner@example.com";

        UserEntity author = new UserEntity();
        author.setEmail(email);

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);
        ad.setAuthor(author);
        ad.setImage("/images/test.jpg");

        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));
        doNothing().when(imageService).deleteImage(anyString());
        doNothing().when(adRepository).delete(ad);

        adService.deleteAd(adId, email);

        verify(adRepository).delete(ad);
        verify(imageService).deleteImage("/images/test.jpg");
    }
}