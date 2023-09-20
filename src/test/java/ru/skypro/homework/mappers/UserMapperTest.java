package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldToUserDto() {

        //given
        User testUser = new User();
        testUser.setId(15);
        testUser.setFirstName("Oleg");
        testUser.setLastName("Olegov");
        testUser.setPhone("phone");
        testUser.setEmail("email");
        testUser.setImage("avatar");
        testUser.setUsername("username");
        testUser.setPassword("password");

        //when
        UserDto testUserDto = userMapper.toUserDto(testUser);
        System.out.println(testUserDto);

        //then
        assertThat(testUserDto).isNotNull();
        assertThat(testUserDto.getId().intValue()).isEqualTo(testUser.getId());
        assertThat(testUserDto.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(testUserDto.getFirstName()).isEqualTo(testUser.getFirstName());
        assertThat(testUserDto.getLastName()).isEqualTo(testUser.getLastName());
        assertThat(testUserDto.getPhone()).isEqualTo(testUser.getPhone());
        assertThat(testUserDto.getImage()).isEqualTo(testUser.getImage());
    }

    @Test
    void shouldToUpdateUserDto() {

        //given
        User testUser2 = new User();
        testUser2.setId(150);
        testUser2.setFirstName("Anna");
        testUser2.setLastName("Leskova");
        testUser2.setPhone("phone of Anna");
        testUser2.setEmail("email of Anna");
        testUser2.setImage("avatar od Anna");
        testUser2.setUsername("username2");
        testUser2.setPassword("password2");

        //when
        UpdateUserDto testUpdateUserDto = userMapper.toUpdateUserDto(testUser2);
        System.out.println(testUpdateUserDto);

        //then
        assertThat(testUpdateUserDto).isNotNull();
        assertThat(testUpdateUserDto.getFirstName()).isEqualTo(testUser2.getFirstName());
        assertThat(testUpdateUserDto.getLastName()).isEqualTo(testUser2.getLastName());
        assertThat(testUpdateUserDto.getPhone()).isEqualTo(testUser2.getPhone());
    }
}