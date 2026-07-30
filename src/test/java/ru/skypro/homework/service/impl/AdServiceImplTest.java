package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    private UserEntity user;
    private AdEntity adEntity;
    private Ad adDto;

    @BeforeEach
    void setUp() {

        user = new UserEntity();
        user.setId(1L);
        user.setUsername("user");

        adEntity = new AdEntity();
        adEntity.setId(1L);
        adEntity.setAuthor(user);

        adDto = new Ad();
        adDto.setPk(1);
    }

    @Test
    void getAllAds_ShouldReturnAds() {

        when(adRepository.findAllByOrderByIdDesc())
                .thenReturn(List.of(adEntity));

        when(adMapper.toDto(adEntity))
                .thenReturn(adDto);

        Ads ads = adService.getAllAds();

        assertEquals(1, ads.getCount());
        assertEquals(1, ads.getResults().size());

        verify(adRepository).findAllByOrderByIdDesc();
    }

    @Test
    void createAd_ShouldCreateAd() {

        CreateOrUpdateAd request = new CreateOrUpdateAd();
        request.setTitle("Phone");
        request.setPrice(100);

        byte[] image = new byte[]{1, 2, 3};

        when(userRepository.findByUsernameIgnoreCase("user"))
                .thenReturn(Optional.of(user));

        when(adMapper.toEntity(request, user))
                .thenReturn(adEntity);

        when(adRepository.save(adEntity))
                .thenReturn(adEntity);

        when(adMapper.toDto(adEntity))
                .thenReturn(adDto);

        Ad result = adService.createAd(
                "user",
                request,
                image,
                "image/png"
        );

        assertNotNull(result);

        verify(adRepository).save(adEntity);

        assertArrayEquals(image, adEntity.getImage());
        assertEquals(
                "image/png",
                adEntity.getImageMediaType()
        );
    }

    @Test
    void getAd_ShouldReturnExtendedAd() {
        ru.skypro.homework.dto.ExtendedAd extendedAd =
                new ru.skypro.homework.dto.ExtendedAd();

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        when(adMapper.toExtendedDto(adEntity))
                .thenReturn(extendedAd);

        ru.skypro.homework.dto.ExtendedAd result =
                adService.getAd(1L);

        assertSame(extendedAd, result);

        verify(adRepository).findById(1L);
        verify(adMapper).toExtendedDto(adEntity);
    }

    @Test
    void getMyAds_ShouldReturnCurrentUserAds() {
        when(userRepository.findByUsernameIgnoreCase("user"))
                .thenReturn(Optional.of(user));

        when(adRepository.findAllByAuthorOrderByIdDesc(user))
                .thenReturn(List.of(adEntity));

        when(adMapper.toDto(adEntity))
                .thenReturn(adDto);

        Ads result = adService.getMyAds("user");

        assertEquals(1, result.getCount());
        assertEquals(1, result.getResults().size());
        assertSame(adDto, result.getResults().get(0));

        verify(userRepository)
                .findByUsernameIgnoreCase("user");

        verify(adRepository)
                .findAllByAuthorOrderByIdDesc(user);
    }

    @Test
    void updateAd_ShouldAllowOwner() {
        CreateOrUpdateAd request =
                new CreateOrUpdateAd();

        request.setTitle("Updated title");
        request.setPrice(200);
        request.setDescription("Updated description");

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        when(adRepository.save(adEntity))
                .thenReturn(adEntity);

        when(adMapper.toDto(adEntity))
                .thenReturn(adDto);

        Ad result = adService.updateAd(
                1L,
                "user",
                "USER",
                request
        );

        assertSame(adDto, result);

        verify(adMapper)
                .updateEntity(adEntity, request);

        verify(adRepository)
                .save(adEntity);
    }

    @Test
    void updateAd_ShouldAllowAdminToUpdateOtherUsersAd() {
        CreateOrUpdateAd request =
                new CreateOrUpdateAd();

        request.setTitle("Changed by admin");
        request.setPrice(300);
        request.setDescription("Admin changed this ad");

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        when(adRepository.save(adEntity))
                .thenReturn(adEntity);

        when(adMapper.toDto(adEntity))
                .thenReturn(adDto);

        Ad result = adService.updateAd(
                1L,
                "admin",
                "ADMIN",
                request
        );

        assertSame(adDto, result);

        verify(adMapper)
                .updateEntity(adEntity, request);

        verify(adRepository)
                .save(adEntity);
    }

    @Test
    void updateAd_ShouldDenyOtherUser() {
        CreateOrUpdateAd request =
                new CreateOrUpdateAd();

        request.setTitle("Illegal update");
        request.setPrice(100);
        request.setDescription("Other user tries to update");

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        ru.skypro.homework.exception.AccessDeniedException exception =
                assertThrows(
                        ru.skypro.homework.exception.AccessDeniedException.class,
                        () -> adService.updateAd(
                                1L,
                                "other-user",
                                "USER",
                                request
                        )
                );

        assertEquals(
                "Недостаточно прав для изменения объявления",
                exception.getMessage()
        );

        verify(adMapper, never())
                .updateEntity(any(), any());

        verify(adRepository, never())
                .save(any());
    }

    @Test
    void deleteAd_ShouldAllowOwner() {
        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        adService.deleteAd(
                1L,
                "user",
                "USER"
        );

        verify(adRepository)
                .delete(adEntity);
    }

    @Test
    void deleteAd_ShouldAllowAdmin() {
        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        adService.deleteAd(
                1L,
                "admin",
                "ADMIN"
        );

        verify(adRepository)
                .delete(adEntity);
    }

    @Test
    void deleteAd_ShouldDenyOtherUser() {
        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        assertThrows(
                ru.skypro.homework.exception.AccessDeniedException.class,
                () -> adService.deleteAd(
                        1L,
                        "other-user",
                        "USER"
                )
        );

        verify(adRepository, never())
                .delete(any());
    }

    @Test
    void updateImage_ShouldSaveImageForOwner() {
        byte[] image = new byte[]{
                10,
                20,
                30
        };

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        adService.updateImage(
                1L,
                "user",
                "USER",
                image,
                "image/jpeg"
        );

        assertArrayEquals(
                image,
                adEntity.getImage()
        );

        assertEquals(
                "image/jpeg",
                adEntity.getImageMediaType()
        );

        verify(adRepository)
                .save(adEntity);
    }

    @Test
    void updateImage_ShouldDenyOtherUser() {
        byte[] image = new byte[]{
                1,
                2,
                3
        };

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        assertThrows(
                ru.skypro.homework.exception.AccessDeniedException.class,
                () -> adService.updateImage(
                        1L,
                        "other-user",
                        "USER",
                        image,
                        "image/png"
                )
        );

        verify(adRepository, never())
                .save(any());
    }

    @Test
    void getImage_ShouldReturnStoredImage() {
        byte[] expectedImage = new byte[]{
                5,
                6,
                7
        };

        adEntity.setImage(expectedImage);

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        byte[] result = adService.getImage(1L);

        assertArrayEquals(
                expectedImage,
                result
        );
    }

    @Test
    void getImage_ShouldThrowWhenImageDoesNotExist() {
        adEntity.setImage(null);

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> adService.getImage(1L)
                );

        assertEquals(
                "Изображение объявления с id 1 не найдено",
                exception.getMessage()
        );
    }

    @Test
    void getImageMediaType_ShouldReturnStoredMediaType() {
        adEntity.setImageMediaType("image/png");

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        String result =
                adService.getImageMediaType(1L);

        assertEquals(
                "image/png",
                result
        );
    }

    @Test
    void getImageMediaType_ShouldReturnDefaultMediaType() {
        adEntity.setImageMediaType(null);

        when(adRepository.findById(1L))
                .thenReturn(Optional.of(adEntity));

        String result =
                adService.getImageMediaType(1L);

        assertEquals(
                "application/octet-stream",
                result
        );
    }

    @Test
    void getAd_ShouldThrowWhenAdDoesNotExist() {
        when(adRepository.findById(999L))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> adService.getAd(999L)
                );

        assertEquals(
                "Объявление с id 999 не найдено",
                exception.getMessage()
        );

        verify(adMapper, never())
                .toExtendedDto(any());
    }

    @Test
    void createAd_ShouldThrowWhenUserDoesNotExist() {
        CreateOrUpdateAd request =
                new CreateOrUpdateAd();

        request.setTitle("Phone");
        request.setPrice(100);
        request.setDescription("Description");

        when(userRepository
                .findByUsernameIgnoreCase("unknown"))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> adService.createAd(
                                "unknown",
                                request,
                                new byte[]{1},
                                "image/png"
                        )
                );

        assertEquals(
                "Пользователь unknown не найден",
                exception.getMessage()
        );

        verify(adMapper, never())
                .toEntity(any(), any());

        verify(adRepository, never())
                .save(any());
    }


}
