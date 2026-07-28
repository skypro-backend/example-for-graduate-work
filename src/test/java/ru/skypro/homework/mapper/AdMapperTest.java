package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

class AdMapperTest {

    private final AdMapper adMapper = new AdMapperImpl();

    @Test
    void toDto_shouldMapIdToPkAndAuthorToAuthorId() {
        User author = new User();
        author.setId(42);

        ru.skypro.homework.entity.Ad entity = new ru.skypro.homework.entity.Ad();
        entity.setId(7);
        entity.setTitle("iPhone 15");
        entity.setPrice(100000);
        entity.setDescription("Отличное состояние");
        entity.setAuthor(author);
        entity.setImage("/images/ad_7.jpg");

        Ad dto = adMapper.toDto(entity);

        assertThat(dto.getPk()).isEqualTo(7);
        assertThat(dto.getAuthor()).isEqualTo(42);
        assertThat(dto.getTitle()).isEqualTo("iPhone 15");
        assertThat(dto.getPrice()).isEqualTo(100000);
        assertThat(dto.getImage()).isEqualTo("/images/ad_7.jpg");
    }

    @Test
    void toExtendedDto_shouldMapAuthorFieldsFlattenedIntoExtendedAd() {
        User author = new User();
        author.setId(1);
        author.setFirstName("Ivan");
        author.setLastName("Ivanov");
        author.setEmail("ivan@mail.com");
        author.setPhone("+79991234567");
        author.setRole(Role.USER);

        ru.skypro.homework.entity.Ad entity = new ru.skypro.homework.entity.Ad();
        entity.setId(3);
        entity.setTitle("Велосипед");
        entity.setPrice(15000);
        entity.setDescription("Почти новый");
        entity.setAuthor(author);
        entity.setImage("/images/ad_3.jpg");

        ExtendedAd dto = adMapper.toExtendedDto(entity);

        assertThat(dto.getPk()).isEqualTo(3);
        assertThat(dto.getAuthorFirstName()).isEqualTo("Ivan");
        assertThat(dto.getAuthorLastName()).isEqualTo("Ivanov");
        assertThat(dto.getEmail()).isEqualTo("ivan@mail.com");
        assertThat(dto.getPhone()).isEqualTo("+79991234567");
        assertThat(dto.getTitle()).isEqualTo("Велосипед");
        assertThat(dto.getPrice()).isEqualTo(15000);
        assertThat(dto.getDescription()).isEqualTo("Почти новый");
        assertThat(dto.getImage()).isEqualTo("/images/ad_3.jpg");
    }

    @Test
    void toEntity_fromCreateOrUpdateAd_shouldMapBasicFieldsOnly() {
        CreateOrUpdateAd dto = new CreateOrUpdateAd();
        dto.setTitle("Ноутбук");
        dto.setPrice(50000);
        dto.setDescription("В отличном состоянии, без царапин");

        ru.skypro.homework.entity.Ad entity = adMapper.toEntity(dto);

        assertThat(entity.getTitle()).isEqualTo("Ноутбук");
        assertThat(entity.getPrice()).isEqualTo(50000);
        assertThat(entity.getDescription()).isEqualTo("В отличном состоянии, без царапин");
        assertThat(entity.getAuthor()).isNull(); // автор проставляется в сервисе, не маппером
        assertThat(entity.getId()).isNull(); // id ещё не присвоен, сущность не сохранена
    }
}