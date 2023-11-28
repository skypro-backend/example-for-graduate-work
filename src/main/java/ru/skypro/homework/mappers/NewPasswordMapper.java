package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.models.NewPassword;

public class NewPasswordMapper {

    public static NewPassword toNewPassword(NewPasswordDto newPasswordDto){
        if(newPasswordDto == null){
            throw new NullPointerException("Tried to map null in NewPassword");
        }

        NewPassword newPassword = new NewPassword();

        newPassword.setCurrentPassword(newPasswordDto.getCurrentPassword());
        newPassword.setNewPassword(newPasswordDto.getNewPassword());

        return newPassword;
    }

    public static NewPasswordDto fromNewPassword(NewPassword newPassword){
        if(newPassword == null){
            throw new NullPointerException("Tried to map null in NewPasswordDto");
        }

        NewPasswordDto newPasswordDto = new NewPasswordDto();

        newPasswordDto.setCurrentPassword(newPassword.getCurrentPassword());
        newPasswordDto.setNewPassword(newPassword.getNewPassword());

        return newPasswordDto;
    }

}
