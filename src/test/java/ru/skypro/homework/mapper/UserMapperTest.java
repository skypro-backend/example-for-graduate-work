package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void toDto_shouldMapUserEntityToUserDto() {
        // Given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("user@test.com");
        userEntity.setFirstName("Иван");
        userEntity.setLastName("Иванов");
        userEntity.setPhone("+79991234567");
        userEntity.setRole(Role.USER);

        // When
        User result = mapper.toDto(userEntity);

        // Then
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getEmail()).isEqualTo("user@test.com");
        assertThat(result.getFirstName()).isEqualTo("Иван");
        assertThat(result.getLastName()).isEqualTo("Иванов");
        assertThat(result.getPhone()).isEqualTo("+79991234567");
        assertThat(result.getRole()).isEqualTo(Role.USER);
        assertThat(result.getImage()).isEqualTo("/image/user/1");
    }

    @Test
    void toEntity_shouldMapUserDtoToUserEntity() {
        // Given
        User user = new User();
        user.setFirstName("Петр");
        user.setLastName("Петров");
        user.setPhone("+79876543210");

        // When
        UserEntity result = mapper.toEntity(user);

        // Then
        assertThat(result.getFirstName()).isEqualTo("Петр");
        assertThat(result.getLastName()).isEqualTo("Петров");
        assertThat(result.getPhone()).isEqualTo("+79876543210");
        assertThat(result.getPassword()).isNull();
        assertThat(result.getAds()).isNull();
        assertThat(result.getComments()).isNull();
    }
}