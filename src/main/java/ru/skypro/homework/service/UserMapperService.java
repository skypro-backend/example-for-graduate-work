package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Service
public class UserMapperService {

    public User mapToEntity(UserDto userDto) {
        User userEntity = new User();
        userEntity.setUserId(userDto.getId());
        userEntity.setLogin(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setImagePath(userDto.getImage());
        return userEntity;
    }

    public UserDto mapToDto(User userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getUserId());
        userDto.setEmail(userEntity.getLogin());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPhone(userEntity.getPhone());
        userDto.setImage(userEntity.getImagePath());
        return userDto;
    }

    public User updateEntityFromDto(UserDto userDto, User user) {
        return null;
    }
}
