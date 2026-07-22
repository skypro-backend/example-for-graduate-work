package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.image.ImageStorageService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdServiceImplTest {


    private AdRepository adRepository;
    private UserRepository userRepository;
    private AdMapper adMapper;
    private ImageStorageService imageStorageService;

    private AdServiceImpl adService;



    @BeforeEach
    void setUp() {


        adRepository = mock(AdRepository.class);
        userRepository = mock(UserRepository.class);
        adMapper = mock(AdMapper.class);
        imageStorageService = mock(ImageStorageService.class);


        adService = new AdServiceImpl(
                adRepository,
                userRepository,
                adMapper,
                imageStorageService
        );



        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                "test@mail.ru",
                                null
                        )
                );
    }



    @AfterEach
    void clearContext() {

        SecurityContextHolder.clearContext();
    }





    @Test
    void shouldGetAdById() {


        Ad ad = new Ad();

        ad.setId(1);



        when(adRepository.findById(1))
                .thenReturn(
                        Optional.of(ad)
                );


        ru.skypro.homework.dto.ExtendedAd dto =
                new ru.skypro.homework.dto.ExtendedAd();


        when(adMapper.toExtendedDto(ad))
                .thenReturn(dto);



        ru.skypro.homework.dto.ExtendedAd result =
                adService.getAd(1);



        assertThat(result)
                .isEqualTo(dto);



        verify(adRepository)
                .findById(1);
    }






    @Test
    void shouldCreateAd() throws Exception {


        User user = new User();

        user.setEmail(
                "test@mail.ru"
        );


        Ad ad = new Ad();

        ru.skypro.homework.dto.Ad dto =
                new ru.skypro.homework.dto.Ad();



        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );



        when(adMapper.toEntity(
                any(CreateOrUpdateAd.class)
        ))
                .thenReturn(ad);



        when(adRepository.save(ad))
                .thenReturn(ad);



        when(adMapper.toDto(ad))
                .thenReturn(dto);



        CreateOrUpdateAd createDto =
                new CreateOrUpdateAd();



        ru.skypro.homework.dto.Ad result =
                adService.createAd(
                        createDto,
                        null
                );



        assertThat(result)
                .isEqualTo(dto);



        assertThat(ad.getAuthor())
                .isEqualTo(user);



        verify(adRepository)
                .save(ad);
    }






    @Test
    void shouldDeleteAdByOwner() {


        User user = new User();

        user.setEmail(
                "test@mail.ru"
        );



        Ad ad = new Ad();

        ad.setAuthor(user);



        when(adRepository.findById(1))
                .thenReturn(
                        Optional.of(ad)
                );



        adService.deleteAd(1);



        verify(adRepository)
                .delete(ad);
    }





    @Test
    void shouldUpdateAd() {


        User user = new User();

        user.setEmail(
                "test@mail.ru"
        );



        Ad ad = new Ad();

        ad.setAuthor(user);



        when(adRepository.findById(1))
                .thenReturn(
                        Optional.of(ad)
                );



        when(adRepository.save(ad))
                .thenReturn(ad);



        ru.skypro.homework.dto.Ad dto =
                new ru.skypro.homework.dto.Ad();



        when(adMapper.toDto(ad))
                .thenReturn(dto);



        CreateOrUpdateAd updateDto =
                new CreateOrUpdateAd();



        ru.skypro.homework.dto.Ad result =
                adService.updateAd(
                        1,
                        updateDto
                );



        assertThat(result)
                .isEqualTo(dto);



        verify(adMapper)
                .updateEntityFromDto(
                        updateDto,
                        ad
                );


        verify(adRepository)
                .save(ad);
    }

}