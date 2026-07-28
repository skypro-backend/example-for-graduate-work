package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void toDto_shouldMapAllFieldsCorrectly() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@mail.com");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPhone("+79991234567");
        user.setRole(Role.USER);
        user.setImage("/images/user_1.png");

        ru.skypro.homework.dto.User dto = userMapper.toDto(user);

        assertThat(dto.getId()).isEqualTo(1);
        assertThat(dto.getEmail()).isEqualTo("test@mail.com");
        assertThat(dto.getFirstName()).isEqualTo("Ivan");
        assertThat(dto.getLastName()).isEqualTo("Ivanov");
        assertThat(dto.getPhone()).isEqualTo("+79991234567");
        assertThat(dto.getRole()).isEqualTo(ru.skypro.homework.dto.Role.USER);
        assertThat(dto.getImage()).isEqualTo("/images/user_1.png");
    }

    @Test
    void toEntity_fromRegister_shouldMapUsernameToEmailAndIgnorePassword() {
        Register register = new Register();
        register.setUsername("new@mail.com");
        register.setPassword("rawPassword");
        register.setFirstName("Petr");
        register.setLastName("Petrov");
        register.setPhone("+79991112233");
        register.setRole(ru.skypro.homework.dto.Role.ADMIN);

        User entity = userMapper.toEntity(register);

        assertThat(entity.getEmail()).isEqualTo("new@mail.com");
        assertThat(entity.getPassword()).isNull();
        assertThat(entity.getFirstName()).isEqualTo("Petr");
        assertThat(entity.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void updateEntityFromDto_shouldIgnoreNullFields() {
        User existingUser = new User();
        existingUser.setFirstName("OldName");
        existingUser.setLastName("OldSurname");
        existingUser.setPhone("+79990000000");

        ru.skypro.homework.dto.UpdateUser updateDto = new ru.skypro.homework.dto.UpdateUser();
        updateDto.setFirstName("NewName");

        userMapper.updateEntityFromDto(updateDto, existingUser);

        assertThat(existingUser.getFirstName()).isEqualTo("NewName");
        assertThat(existingUser.getLastName()).isEqualTo("OldSurname");
        assertThat(existingUser.getPhone()).isEqualTo("+79990000000");
    }
}