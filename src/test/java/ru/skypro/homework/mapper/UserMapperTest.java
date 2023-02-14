package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
        userEntity.setImage(getImage());

        UserDTO userDTO = userMapper.toDTO(userEntity);
        assertNotNull(userDTO);
        assertEquals(userDTO.getLastName(), userEntity.getLastName());
    }

    byte[] getImage() {
        BufferedImage bi;
        try {
            bi = ImageIO.read(new File("db_diagram.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }
}