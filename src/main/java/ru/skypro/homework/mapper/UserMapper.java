package ru.skypro.homework.mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constants.Constants;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entities.PhotoEntity;
import ru.skypro.homework.entities.UserEntity;

import java.io.IOException;


@Service
public class UserMapper {

    public static UserEntity mapFromRegisterToUserEntity(Register dto) {
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;

    }


    public static User mapFromUserEntityToUser(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId());
        dto.setEmail(entity.getUserName());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setImage(Constants.URL_PHOTO_CONSTANT + entity.getPhoto().getId());
        return dto;
    }


    public static UpdateUser mapFromUserEntityToUpdateUser(UserEntity entity) {
        UpdateUser dto = new UpdateUser();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        return dto;
    }


    public PhotoEntity mapMuptipartFileToPhoto(MultipartFile image) {
        PhotoEntity photo = new PhotoEntity();
        try {
            photo.setData(image.getBytes());
            photo.setMediaType(image.getContentType());
            photo.setFileSize(image.getSize());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка конвертации MultipartFile в PhotoEntity, " +
                    "место ошибки - userMapper.mapMultiPartFileToPhoto()");
        }
        return photo;
    }
}
