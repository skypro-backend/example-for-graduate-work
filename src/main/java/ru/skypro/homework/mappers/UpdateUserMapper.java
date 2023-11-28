package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.models.UpdateUser;

public class UpdateUserMapper {

    public static UpdateUser toUpdateUser(UpdateUserDto updateUserDto){
        if(updateUserDto == null){
            throw new NullPointerException("Tried to map null in UpdateUser");
        }

        UpdateUser updateUser = new UpdateUser();

        updateUser.setFirstName(updateUserDto.getFirstName());
        updateUser.setLastName(updateUserDto.getLastName());
        updateUser.setPhone(updateUserDto.getPhone());

        return updateUser;
    }

    public static UpdateUserDto fromUpdateUser(UpdateUser updateUser){
        if(updateUser == null){
            throw new NullPointerException("Tried to map null in UpdateUserDto");
        }

        UpdateUserDto updateUserDto = new UpdateUserDto();

        updateUserDto.setFirstName(updateUser.getFirstName());
        updateUserDto.setLastName(updateUser.getLastName());
        updateUserDto.setPhone(updateUser.getPhone());

        return updateUserDto;
    }

}
