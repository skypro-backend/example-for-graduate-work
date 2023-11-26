package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Register;
import ru.skypro.homework.model.User;

@Component
public class RegisterMapper {
    public RegisterDto mapToDTO(Register register) {
        return new RegisterDto(
                register.getId(),
                register.getPassword(),
                register.getFirstName(),
                register.getLastName(),
                register.getPhone(),
                register.getRole()
        );
    }

    public User mapToEntity(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhone(),
                userDto.getRole(),
                userDto.getImage()

        );
    }
}
