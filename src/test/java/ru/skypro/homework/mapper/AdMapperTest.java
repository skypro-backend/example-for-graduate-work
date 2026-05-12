package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AdMapperImpl.class)
class AdMapperTest {

    @Autowired
    private AdMapper mapper;

    @Test
    void toDto_shouldMapAdEntityToAdDto() {
        UserEntity author = new UserEntity();
        author.setId(1);

        AdEntity adEntity = new AdEntity();
        adEntity.setPk(1);
        adEntity.setTitle("Велосипед");
        adEntity.setPrice(10000);
        adEntity.setAuthor(author);

        Ad result = mapper.toDto(adEntity);

        assertThat(result.getPk()).isEqualTo(1);
        assertThat(result.getTitle()).isEqualTo("Велосипед");
        assertThat(result.getPrice()).isEqualTo(10000);
        assertThat(result.getAuthor()).isEqualTo(1);
        assertThat(result.getImage()).isEqualTo("/image/ad/1");
    }

    @Test
    void toExtendedDto_shouldMapAdEntityToExtendedAdDto() {
        UserEntity author = new UserEntity();
        author.setId(2);
        author.setFirstName("Иван");
        author.setLastName("Иванов");
        author.setEmail("ivan@example.com");
        author.setPhone("+79991234567");

        AdEntity adEntity = new AdEntity();
        adEntity.setPk(1);
        adEntity.setTitle("Велосипед");
        adEntity.setPrice(10000);
        adEntity.setDescription("Как новый");
        adEntity.setAuthor(author);

        ExtendedAd result = mapper.toExtendedDto(adEntity);

        assertThat(result.getPk()).isEqualTo(1);
        assertThat(result.getAuthorFirstName()).isEqualTo("Иван");
        assertThat(result.getEmail()).isEqualTo("ivan@example.com");
        assertThat(result.getAuthorImage()).isEqualTo("/image/user/2");
        assertThat(result.getImage()).isEqualTo("/image/ad/1");
    }

    @Test
    void toEntity_shouldMapCreateOrUpdateAdToAdEntity() {
        CreateOrUpdateAd dto = new CreateOrUpdateAd();
        dto.setTitle("Велосипед");
        dto.setPrice(10000);
        dto.setDescription("Как новый");

        AdEntity result = mapper.toEntity(dto);

        assertThat(result.getTitle()).isEqualTo("Велосипед");
        assertThat(result.getPrice()).isEqualTo(10000);
        assertThat(result.getDescription()).isEqualTo("Как новый");
        assertThat(result.getPk()).isNull();
        assertThat(result.getAuthor()).isNull();
    }
}