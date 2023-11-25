package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.NewPassword;

@Component
public class NewPasswordMapper {
    public NewPasswordDto mapToDTO(NewPassword newPassword) {
        return new NewPasswordDto(
                newPassword.getId(),
                newPassword.getCurrentPassword(),
                newPassword.getNewPassword()
        );
    }

    public NewPassword mapToEntity(NewPasswordDto newPasswordDto) {
        return new NewPassword(
                newPasswordDto.getId(),
                newPasswordDto.getCurrentPassword(),
                newPasswordDto.getNewPassword()
        );
    }
}
