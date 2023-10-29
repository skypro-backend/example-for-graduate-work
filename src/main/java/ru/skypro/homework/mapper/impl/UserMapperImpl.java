package ru.skypro.homework.mapper.impl;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDTO(UserDto model) {
        if ( model == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public UserDto toDTO(UserModel model) {
        return null;
    }

    @Override
    public UserDto toModel(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }
}

