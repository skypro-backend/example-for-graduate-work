package ru.skypro.kakavito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.kakavito.dto.*;
import ru.skypro.kakavito.exceptions.AdNotFoundException;
import ru.skypro.kakavito.mappers.AdMapper;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.AdRepo;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.impl.AdServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTest {

        @Mock
        private AdRepo adRepo;

        @Mock
        private UserRepo userRepo;

        @Mock
        private ImageService imageService;

        @Mock
        private AdMapper adMapper;

        @Mock
        private SecurityContext securityContext;

        @Mock
        private Authentication authentication;

        @InjectMocks
        private AdServiceImpl adService;

        // Предварительная настройка данных
        private Ad ad;
        private User user;
        private AdDTO adDTO;

        @BeforeEach
        void setUp() {
            // Инициализация данных
            user = new User();
            user.setId(1L);
            user.setRole(Role.USER);

            ad = new Ad();
            ad.setId(1);
            ad.setUser(user);

            adDTO = new AdDTO();
            adDTO.setPk(1);

            // Настройка SecurityContext
            when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            when(authentication.getPrincipal()).thenReturn(new UserPrincipalDTO());
        }

        @Test
        void testGetAllAds() {
            List<Ad> ads = List.of(new Ad()); // Замените на реальные тестовые данные
            when(adRepo.findAll()).thenReturn(ads);
            when(adMapper.convertToAdsDTO(Collections.singletonList(any(Ad.class)))).thenReturn(new AdDTO());

            AdsDTO result = adService.findAllAds();

            assertThat(result).isNotNull();
            assertThat(result.getCount()).isEqualTo(ads.size());
            verify(adMapper, times(ads.size())).convertCreatDTOToAd(any(Ad.class));
        }

        @Test
        void testCreateAd() throws IOException {
            CreateOrUpdateAdDTO dto = new CreateOrUpdateAdDTO();
            Ad ad = new Ad();
            when(adMapper.convertCreatDTOToAd(dto)).thenReturn(ad);
            when(adRepo.save(ad)).thenReturn(ad);
            when(imageService.upLoadImage(any())).thenReturn(new Image());

            AdDTO result = adService.addAd(dto, null);

            assertThat(result).isNotNull();
            verify(adRepo).save(any(Ad.class));
            verify(imageService).upLoadImage(any());
        }

        @Test
        void testGetAdById() {
            when(adRepo.findById(1)).thenReturn(Optional.of(new Ad()));
            when(adMapper.convertToExtendedAd(any(Ad.class))).thenReturn(new ExtendedAdDTO());

            ExtendedAdDTO result = adService.findById(1);

            assertThat(result).isNotNull();
            verify(adMapper).convertToExtendedAd(any(Ad.class));
        }

        @Test
        void getAdById_ShouldThrowAdNotFoundException() {
            when(adRepo.findById(1)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> adService.findById(1))
                    .isInstanceOf(AdNotFoundException.class)
                    .hasMessageContaining("Ad not found with ID: 1");
        }

        @Test
        void testDeleteAd() {
            Ad ad = new Ad();
            ad.setUser(new User());
            when(adRepo.findById(1)).thenReturn(Optional.of(ad));
            doNothing().when(adRepo).deleteById(1);

            adService.deleteAd(1);

            verify(adRepo).deleteById(1);
        }

        @Test
        void testUpdateAd() {
            CreateOrUpdateAdDTO dto = new CreateOrUpdateAdDTO();
            Ad ad = new Ad();
            when(adRepo.findById(1)).thenReturn(Optional.of(ad));
            when(adMapper.convertToAdDTO(any(Ad.class))).thenReturn(new AdDTO());

            AdDTO result = adService.updateAd(1, dto);

            assertThat(result).isNotNull();
            verify(adRepo).save(any(Ad.class));
        }

        @Test
        void testGetAdsByUser() {
            User user = new User();
            user.setId(1L);
            List<Ad> ads = List.of(new Ad());
            when(userRepo.findByEmail("user@example.com")).thenReturn(Optional.of(user));
            when(adRepo.findAllByUserId(1L)).thenReturn(ads);
            when(adMapper.convertToAdDTO(any(Ad.class))).thenReturn(new AdDTO());

            AdsDTO result = adService.getAdByAuthUser();

            assertThat(result).isNotNull();
            assertThat(result.getCount()).isEqualTo(ads.size());
            verify(adRepo).findAllByUserId(1);
            verify(adMapper, times(ads.size())).toDto(any(Ad.class));
        }

        @Test
        void testUpdateAdImage() throws IOException {
            when(adRepo.findById(1)).thenReturn(Optional.of(new Ad()));
            doNothing().when(imageService).updateImage(any(), anyInt());

            adService.updateAdImage(1, null);

            verify(imageService).updateImage(any(), eq(1));
        }
}
