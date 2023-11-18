package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

@Service
public class UserMapper {

    /**
     * Dto -> entity mapping
     *
     * @param dto input dto class
     * @return entity class
     */
    public static UserEntity mapToUserEntity(Register dto) {
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;
    }

    /**
     * Entity -> dto mapping
     *
     * @param entity input entity class
     * @return dto class
     */
    public static User mapToUserDto(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId());
        dto.setEmail(entity.getUserName());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
//        dto.setImage(entity.getAvatar().getFilePath()); // вот это момент на подумать
        return dto;
    }
}
