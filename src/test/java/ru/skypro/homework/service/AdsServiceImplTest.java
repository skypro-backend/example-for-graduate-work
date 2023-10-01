package ru.skypro.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.FullAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AdsServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdsServiceImplTest {
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ImageService imageService;


    @InjectMocks
    AdsServiceImpl out;

    Ad ad = new Ad();
    Ad ad2 = new Ad();
    List<Ad> adList = new ArrayList<>();


    @Test
    void ShouldReturnAdsDtoWhenGetAllAds() {
        when(adRepository.findAll()).thenReturn(adList);
        assertEquals(out.getAllAds(), new AdsDto().fromAdsList(adList));
        verify(adRepository, times(1)).findAll();

    }

    @Test
    void ShouldReturnAdDtoWhenGetAdById() {
        User testUser = new User();
        testUser.setId(42);
        testUser.setUsername("test@test.com");
        ad.setPk(1);
        ad.setUser(testUser);
        AdDto adDto = AdDto.fromAd(ad);
        when(adRepository.findByPk(ad.getPk())).thenReturn(Optional.of(ad));
        assertEquals(out.getAdById(1), adDto);
        verify(adRepository, times(1)).findByPk(ad.getPk());
    }

    @Test
    void getFullAdById() {
        User testUser = new User();
        testUser.setId(10);
        testUser.setUsername("test@test.com");
        ad.setPk(1);
        ad.setUser(testUser);
        when(adRepository.findByPk(1)).thenReturn(Optional.of(ad));
        assertEquals(out.getFullAdById(1), FullAdDto.fromAd(ad));
    }

    @Test
    void ShouldReturnResponseStatusExceptionWhenRemoveAd() {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("test@test.com");
        testUser.setRole("USER");
        User testUser2 = new User();
        testUser2.setId(2);
        testUser2.setUsername("user@user.com");
        testUser2.setRole("USER");
        ad.setPk(2);
        ad.setUser(testUser);
        when(userRepository.findUserByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser2));
        when(adRepository.findByPk(ad.getPk())).thenReturn(Optional.of(ad));
        Assertions.assertThrows(ResponseStatusException.class,() -> {
            out.removeAd(2, "test@test.com");
        });
    }

    @Test
    void updateAdById() {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("test@test.com");
        testUser.setRole("USER");
        ad.setPk(2);
        ad.setTitle("lala");
        ad.setPrice(4000000);
        ad.setDescription("bla");
        ad.setUser(testUser);
        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto(
                "la1la1", 5000000, "bla1");
        when(userRepository.findUserByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));
        when(adRepository.findByPk(ad.getPk())).thenReturn(Optional.of(ad));
        assertTrue(out.updateAdById(2,createOrUpdateAdDto, "test@test.com"));
    }

    @Test
    void ShouldReturnAdsDtoWhenGetAllAdsForUser() {
        User testUser = new User();
        testUser.setId(22);
        testUser.setUsername("test@test.com");
        testUser.setRole("ADMIN");
        ad.setUser(testUser);
        ad2.setUser(testUser);
        adList.add(ad);
        adList.add(ad2);
        when(adRepository.findAdsByUser_UsernameContains(testUser.getUsername())).thenReturn(Optional.of(adList));
        assertEquals(out.getAllAdsForUser("test@test.com"), new AdsDto().fromAdsList(adList));
    }

    @Test
    void ShouldReturnTrueWhenUpdateImageById() {
        Image testImg1 = new Image();
        testImg1.setId("Str1");
        Image testImg2 = new Image();
        testImg2.setId("Str2");
        MultipartFile file = new MockMultipartFile("qwer", "foto", "jpeg", new byte[1]);
        User testUser = new User();
        testUser.setId(2);
        testUser.setUsername("test@test.com");
        ad.setPk(1);
        ad.setUser(testUser);
        ad.setImage("Str1");
        when(adRepository.findByPk(ad.getPk())).thenReturn(Optional.of(ad));
        when(imageService.uploadImage(file)).thenReturn(testImg2.getId());
        when(adRepository.save(any())).thenReturn(any());
        assertTrue(out.updateImageById(1,file));
    }
}
