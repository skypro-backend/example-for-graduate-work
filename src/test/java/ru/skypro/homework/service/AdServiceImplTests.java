package ru.skypro.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdServiceImplTests {

    @Mock
    private AdRepository adRepository;
    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    @Test
    public void shouldCorrectlyCreateAd() {

    }

    @Test
    public void shouldCorrectlyUpdateAd() {
        Ad ad = new Ad();
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd("Монитор", 1000, "Монитор в хорошем состоянии");

        ad.setTitle("Монитор");
        ad.setPrice(1000);
        ad.setDescription("Монитор в хорошем состоянии");

        when(adRepository.save(ad)).thenReturn(ad);
        Ad expected = ad;
        Ad actual = adService.updateAd(ad ,createOrUpdateAd);
        Assertions.assertEquals(expected, actual);
    }

}
