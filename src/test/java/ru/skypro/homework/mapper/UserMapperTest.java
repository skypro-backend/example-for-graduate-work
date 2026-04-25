package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void toDto_ShouldMapUserEntityToUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("user@test.com");
        userEntity.setFirstName("Ivan");
        userEntity.setLastName("Ivanov");
        userEntity.setPhone("+79991234567");

        User dto = userMapper.toDto(userEntity);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("user@test.com", dto.getEmail());
        assertEquals("Ivan", dto.getFirstName());
        assertEquals("Ivanov", dto.getLastName());
        assertEquals("+79991234567", dto.getPhone());
    }
}