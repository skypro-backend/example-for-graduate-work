package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdMapper adMapper;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private AdServiceImpl adService;

    private UserEntity user;
    private AdEntity ad;
    private Ad adDto;
    private ExtendedAd extendedAdDto;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1);
        user.setEmail("user@test.com");
        user.setRole(ru.skypro.homework.entity.Role.USER);

        ad = new AdEntity();
        ad.setPk(100);
        ad.setTitle("Велосипед");
        ad.setPrice(10_000);
        ad.setDescription("Как новый");
        ad.setAuthor(user);

        adDto = new Ad();
        adDto.setPk(100);
        adDto.setTitle("Велосипед");
        adDto.setPrice(10_000);
        adDto.setAuthor(1);

        extendedAdDto = new ExtendedAd();
        extendedAdDto.setPk(100);
        extendedAdDto.setTitle("Велосипед");
        extendedAdDto.setPrice(10_000);
        extendedAdDto.setDescription("Как новый");
        extendedAdDto.setAuthorFirstName("Иван");
        extendedAdDto.setEmail("user@test.com");
    }

    @Test
    void getAllAds_shouldReturnAllAds() {
        // Given
        when(adRepository.findAll()).thenReturn(Arrays.asList(ad));
        when(adMapper.toDto(ad)).thenReturn(adDto);

        // When
        Ads result = adService.getAllAds();

        // Then
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getResults()).hasSize(1);
        assertThat(result.getResults().get(0).getTitle()).isEqualTo("Велосипед");
        verify(adRepository).findAll();
    }

    @Test
    void getExtendedAd_shouldReturnExtendedAdById() {
        // Given
        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(adMapper.toExtendedDto(ad)).thenReturn(extendedAdDto);

        // When
        ExtendedAd result = adService.getExtendedAd(100);

        // Then
        assertThat(result.getPk()).isEqualTo(100);
        assertThat(result.getDescription()).isEqualTo("Как новый");
        verify(adRepository).findById(100);
    }

    @Test
    void getExtendedAd_whenAdNotFound_shouldThrowException() {
        // Given
        when(adRepository.findById(999)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> adService.getExtendedAd(999)).isInstanceOf(EntityNotFoundException.class).hasMessage("Объявление не найдено");
    }

    @Test
    void createAd_shouldCreateNewAd() {
        // Given
        AdEntity adToSave = new AdEntity();
        adToSave.setTitle("Велосипед");
        adToSave.setPrice(10_000);

        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(adRepository.save(any(AdEntity.class))).thenAnswer(i -> {
            AdEntity saved = i.getArgument(0);
            saved.setPk(100); // имитация генерации ID
            return saved;
        });
        when(adMapper.toDto(any(AdEntity.class))).thenReturn(adDto);

        // When
        Ad result = adService.createAd(adToSave, "user@test.com");

        // Then
        assertThat(result.getPk()).isEqualTo(100);
        assertThat(result.getTitle()).isEqualTo("Велосипед");
        assertThat(result.getAuthor()).isEqualTo(1);
        verify(adRepository).save(argThat(a -> a.getAuthor().getId().equals(1)));
    }

    @Test
    void deleteAd_shouldDeleteAdIfAuthor() {
        // Given
        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        // When
        adService.deleteAd(100, "user@test.com");

        // Then
        verify(adRepository).deleteById(100);
    }

    @Test
    void deleteAd_whenUserIsAdmin_shouldDeleteAd() {
        // Given
        UserEntity admin = new UserEntity();
        admin.setId(2);
        admin.setRole(ru.skypro.homework.entity.Role.ADMIN);

        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("admin@test.com")).thenReturn(Optional.of(admin));

        // When
        adService.deleteAd(100, "admin@test.com");

        // Then
        verify(adRepository).deleteById(100);
    }

    @Test
    void deleteAd_whenNotAuthorOrAdmin_shouldThrowException() {
        // Given
        UserEntity otherUser = new UserEntity();
        otherUser.setId(2);
        otherUser.setRole(ru.skypro.homework.entity.Role.USER);

        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("other@test.com")).thenReturn(Optional.of(otherUser));

        // When & Then
        assertThatThrownBy(() -> adService.deleteAd(100, "other@test.com")).isInstanceOf(org.springframework.web.server.ResponseStatusException.class).hasMessageContaining("403 FORBIDDEN");
    }

    @Test
    void updateAd_shouldUpdateFieldsIfAuthor() {
        // Given
        AdEntity updatedAd = new AdEntity();
        updatedAd.setTitle("Новый велосипед");
        updatedAd.setPrice(15_000);

        // Создаём ожидаемый результат
        Ad expectedDto = new Ad();
        expectedDto.setPk(100);
        expectedDto.setTitle("Новый велосипед");
        expectedDto.setPrice(15_000);
        expectedDto.setAuthor(1);

        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(adRepository.save(any(AdEntity.class))).thenAnswer(i -> i.getArgument(0)); // возвращаем сохранённый объект
        when(adMapper.toDto(any(AdEntity.class))).thenReturn(expectedDto); // теперь с новым заголовком

        // When
        Ad result = adService.updateAd(100, updatedAd, "user@test.com");

        // Then
        assertThat(result.getTitle()).isEqualTo("Новый велосипед");
        assertThat(result.getPrice()).isEqualTo(15_000);
        verify(adRepository).save(argThat(saved -> saved.getTitle().equals("Новый велосипед") && saved.getPrice() == 15_000));
    }

    @Test
    void getAdsByUser_shouldReturnUsersAds() {
        // Given
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(adRepository.findByAuthorId(1)).thenReturn(Arrays.asList(ad));
        when(adMapper.toDto(ad)).thenReturn(adDto);

        // When
        Ads result = adService.getAdsByUser("user@test.com");

        // Then
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getResults().get(0).getPk()).isEqualTo(100);
    }

    @Test
    void updateImage_shouldSaveImageIfAuthor() throws IOException {
        // Given
        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        // When
        adService.updateImage(100, "image_data".getBytes(), "user@test.com");

        // Then
        verify(imageService).saveAdImage(100, "image_data".getBytes());
    }
}