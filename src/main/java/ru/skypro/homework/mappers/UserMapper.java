package ru.skypro.homework.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Data
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
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
