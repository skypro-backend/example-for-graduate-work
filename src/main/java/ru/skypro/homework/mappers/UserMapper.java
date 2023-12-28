package ru.skypro.homework.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Data
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public User convertToUser(NewPasswordDTO newPasswordDTO) {
        return modelMapper.map(newPasswordDTO, User.class);
    }

    public User convertToUpdateUser(UpdateUserDTO updateUserDTO) {
        return modelMapper.map(updateUserDTO, User.class);
    }

    public UserDto converToUserDto (User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UpdateUserDTO convertToUpdateUserDTO(User user) {
        return modelMapper.map(user, UpdateUserDTO.class);
    }

    public ImageDTO convertToImageDTO(User user) {
        return modelMapper.map(user, ImageDTO.class);
    }
}
