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
    }

}
