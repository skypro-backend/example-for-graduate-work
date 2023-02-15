package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testEntityToDTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("Иван");
        userEntity.setLastName("Иванов");
        userEntity.setCity("Омск");
        userEntity.setEmail("ivanov@mail.ru");
        userEntity.setPhone("+79876543201");
        userEntity.setRegDate(LocalDateTime.of(2023, 2,14,14, 30,22));
        userEntity.setImage("/user/1");

        UserDTO userDTO = userMapper.toDTO(userEntity);
        assertNotNull(userDTO);
        assertEquals(userDTO.getLastName(), userEntity.getLastName());
    }

    @Test
    void testDTOToEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setFirstName("Иван");
        userDTO.setLastName("Иванов");
        userDTO.setCity("Омск");
        userDTO.setEmail("ivanov@mail.ru");
        userDTO.setPhone("+79876543201");
        userDTO.setRegDate("14-02-2023 14:30:22");
        userDTO.setImage("/user/1");

        UserEntity userEntity = userMapper.toEntity(userDTO);
        assertNotNull(userEntity);
        assertEquals(userEntity.getLastName(), userDTO.getLastName());
    }
}