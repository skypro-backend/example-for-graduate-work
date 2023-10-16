package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Ad;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdMapperTest {
    @Autowired
    private AdMapper adMapper;

    @Test
    public void shouldTestMapper() {
        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto("Name", 2000, "Description");
        Ad ad = adMapper.toAd(createOrUpdateAdDto);
        System.out.println(ad);
    }
}