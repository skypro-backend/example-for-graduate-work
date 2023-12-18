package ru.skypro.homework.service;

import org.junit.jupiter.api.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import java.util.List;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserMapperTest {
    private static UserEntity source = new UserEntity(1, "vasyan228","ya.ru/kartinka", "lolka@bolka.ru", "Lolek", "Bolek", "88005553535", Role.USER, "QWERTY123", List.of(new AdEntity(), new AdEntity()));
    private static User sourceDTO = new User(1, "lolka@bolka.ru", "Lolek", "Bolek", "88005553535", Role.USER.name(), "ya.ru/kartinka");

    @Test
    @Order(0)
    void mapperLoads() {
       Assertions.assertNotNull(UserMapper.INSTANCE);
    }

    @Test
    @Order(1)
    void userEntityConvertsToDTO() {

        User DTO = UserMapper.INSTANCE.UserEntityToDTO(source);

        Assertions.assertNotNull(DTO);
        Assertions.assertEquals(1, DTO.getId());
        Assertions.assertEquals("ya.ru/kartinka", DTO.getImage());
        Assertions.assertEquals("lolka@bolka.ru", DTO.getEmail());
        Assertions.assertEquals("Lolek", DTO.getFirstName());
        Assertions.assertEquals("Bolek", DTO.getLastName());
        Assertions.assertEquals("88005553535", DTO.getPhone());
        Assertions.assertEquals(Role.USER.name(), DTO.getRole());

    }
    @Test
    void UserDtoConvertsToEntity() {

        UserEntity entity = UserMapper.INSTANCE.DtoToEntity(sourceDTO);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(1, entity.getId());
        Assertions.assertEquals("ya.ru/kartinka", entity.getImage());
        Assertions.assertEquals("lolka@bolka.ru", entity.getEmail());
        Assertions.assertEquals("Lolek", entity.getFirstName());
        Assertions.assertEquals("Bolek", entity.getLastName());
        Assertions.assertEquals("88005553535", entity.getPhone());
        Assertions.assertEquals(Role.USER, entity.getRole());
        Assertions.assertNull(entity.getAds());
        Assertions.assertNull(entity.getPassword());
        Assertions.assertNull(entity.getLogin());
    }
}