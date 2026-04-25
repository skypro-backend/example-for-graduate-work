package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdMapperTest {

    @Autowired
    private AdMapper adMapper;

    @Test
    void toDto_ShouldMapAdEntityToAdDto() {
        UserEntity author = new UserEntity();
        author.setId(1);

        AdEntity adEntity = new AdEntity();
        adEntity.setPk(100);
        adEntity.setTitle("Laptop");
        adEntity.setPrice(500);
        adEntity.setAuthor(author);
        adEntity.setCreatedAt(LocalDateTime.now());

        Ad dto = adMapper.toDto(adEntity);

        assertNotNull(dto);
        assertEquals(100, dto.getPk());
        assertEquals("Laptop", dto.getTitle());
        assertEquals(500, dto.getPrice());
        assertEquals("/ads/100/image", dto.getImage());
        assertEquals(1, dto.getAuthor());
    }

    @Test
    void toExtendedDto_ShouldMapAdEntityToExtendedAd() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setFirstName("Ivan");
        author.setLastName("Ivanov");
        author.setEmail("user@test.com");
        author.setPhone("+79991234567");

        AdEntity adEntity = new AdEntity();
        adEntity.setPk(100);
        adEntity.setTitle("Laptop");
        adEntity.setPrice(500);
        adEntity.setDescription("Good condition");
        adEntity.setAuthor(author);
        adEntity.setCreatedAt(LocalDateTime.now());

        ExtendedAd dto = adMapper.toExtendedDto(adEntity);

        assertNotNull(dto);
        assertEquals(100, dto.getPk());
        assertEquals("Laptop", dto.getTitle());
        assertEquals(500, dto.getPrice());
        assertEquals("Good condition", dto.getDescription());
        assertEquals("Ivan", dto.getAuthorFirstName());
        assertEquals("Ivanov", dto.getAuthorLastName());
        assertEquals("user@test.com", dto.getEmail());
        assertEquals("+79991234567", dto.getPhone());
        assertEquals("/users/1/image", dto.getAuthorImage());
        assertEquals("/ads/100/image", dto.getImage());
    }
}