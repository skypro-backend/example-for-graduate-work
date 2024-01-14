package ru.skypro.kakavito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.*;
import ru.skypro.kakavito.exceptions.AdNotFoundException;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.exceptions.UserNotFoundException;
import ru.skypro.kakavito.mappers.AdMapper;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.AdRepo;
import ru.skypro.kakavito.repository.ImageRepo;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.ImageService;
import ru.skypro.kakavito.service.impl.AdServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private AdMapper adMapper;

    @Mock
    private AdRepo adRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ImageRepo imageRepo;

    @Mock
    private ImageService imageService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void findAllAds_shouldReturnAdsDTO() {
        // Arrange
        when(adRepo.findAll()).thenReturn(Collections.singletonList(new Ad()));
        when(adMapper.convertToAdsDTO(anyList())).thenReturn(new AdsDTO());

        // Act
        AdsDTO result = adService.findAllAds();

        // Assert
        assertNotNull(result);
        verify(adRepo, times(1)).findAll();
        verify(adMapper, times(1)).convertToAdsDTO(anyList());
    }

    @Test
    void addAd_shouldReturnAdDTO() throws IOException, ImageSizeExceededException {
        // Arrange
        MultipartFile imageFile = mock(MultipartFile.class);
        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        User user = new User();
        user.setId(1L);

        when(imageService.upLoadImage(imageFile)).thenReturn(new Image());
        when(adMapper.convertCreatDTOToAd(createOrUpdateAdDTO)).thenReturn(new Ad());
        when(adRepo.save(any(Ad.class))).thenReturn(new Ad());
        when(adMapper.convertToAdDTO(any(Ad.class))).thenReturn(new AdDTO());
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(authentication.getPrincipal()).thenReturn(mock(UserDetails.class));
        when(((UserDetails) authentication.getPrincipal()).getUsername()).thenReturn("test@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Act
        AdDTO result = adService.addAd(createOrUpdateAdDTO, imageFile);

        // Assert
        assertNotNull(result);
        verify(imageService, times(1)).upLoadImage(imageFile);
        verify(adRepo, times(1)).save(any(Ad.class));
        verify(adMapper, times(1)).convertToAdDTO(any(Ad.class));
        verify(userRepo, times(1)).findByEmail(anyString());
    }

    @Test
    void findById_shouldReturnExtendedAdDTO() {
        // Arrange
        when(adRepo.findById(anyInt())).thenReturn(Optional.of(new Ad()));
        when(adMapper.convertToExtendedAd(any(Ad.class))).thenReturn(new ExtendedAdDTO());

        // Act
        ExtendedAdDTO result = adService.findById(1);

        // Assert
        assertNotNull(result);
        verify(adRepo, times(1)).findById(anyInt());
        verify(adMapper, times(1)).convertToExtendedAd(any(Ad.class));
    }

    @Test
    void getAdByAuthUser_shouldReturnAdsDTO() {
        // Arrange
        User user = new User();
        user.setId(1L);
        when(adRepo.findAllByUserId(user.getId())).thenReturn(Collections.singletonList(new Ad()));
        when(adMapper.convertToAdsDTO(anyList())).thenReturn(new AdsDTO());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(mock(UserDetails.class));
        when(((UserDetails) authentication.getPrincipal()).getUsername()).thenReturn("test@example.com");
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(user));

        // Act
        AdsDTO result = adService.getAdByAuthUser();

        // Assert
        assertNotNull(result);
        verify(adRepo, times(1)).findAllByUserId(user.getId());
        verify(adMapper, times(1)).convertToAdsDTO(anyList());
        verify(userRepo, times(1)).findByEmail(anyString());
    }

    @Test
    void getAuthUser_shouldReturnUser() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(mock(UserDetails.class));
        when(((UserDetails) authentication.getPrincipal()).getUsername()).thenReturn("test@example.com");
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        // Act
        User result = adService.getAuthUser();

        // Assert
        assertNotNull(result);
        verify(userRepo, times(1)).findByEmail(anyString());
    }

    @Test
    void updateAd_shouldReturnAdDTO() {
        // Arrange
        int adId = 1;
        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        Ad ad = new Ad();
        ad.setId(adId);
        ad.setTitle("Old Title");
        when(adRepo.findById(adId)).thenReturn(Optional.of(ad));
        when(adRepo.save(any(Ad.class))).thenReturn(ad);
        when(adMapper.convertToAdDTO(ad)).thenReturn(new AdDTO());

        // Act
        AdDTO result = adService.updateAd(adId, createOrUpdateAdDTO);

        // Assert
        assertNotNull(result);
        assertEquals(createOrUpdateAdDTO.getTitle(), ad.getTitle());
        verify(adRepo, times(1)).findById(adId);
        verify(adRepo, times(1)).save(any(Ad.class));
        verify(adMapper, times(1)).convertToAdDTO(ad);
    }

    @Test
    void updateAdImage_shouldUpdateImage() {
        // Arrange
        int adId = 1;
        MultipartFile imageFile = mock(MultipartFile.class);
        Ad ad = new Ad();
        ad.setId(adId);
        when(adRepo.findById(adId)).thenReturn(Optional.of(ad));

        // Act
        adService.updateAdImage(adId, imageFile);

        // Assert
        verify(imageService, times(1)).updateImage(adId, imageFile);
    }

    @Test
    void deleteAd_shouldDeleteAd() {
        // Arrange
        int adId = 1;
        when(adRepo.findById(adId)).thenReturn(Optional.of(new Ad()));

        // Act
        adService.deleteAd(adId);

        // Assert
        verify(adRepo, times(1)).findById(adId);
        verify(adRepo, times(1)).deleteById(adId);
    }

    // Add similar test methods for other AdServiceImpl methods
}
