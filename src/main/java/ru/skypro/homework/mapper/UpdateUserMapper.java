package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.UpdateUser;

@Component
public class UpdateUserMapper {
    public UpdateUserDto mapToDTO(UpdateUser updateUser) {
        return new UpdateUserDto(
                updateUser.getId(),
                updateUser.getFirstName(),
                updateUser.getLastName(),
                updateUser.getPhone()
        );
    }

    public UpdateUser mapToEntity(UpdateUserDto updateUserDto) {
        return new UpdateUser(
                updateUserDto.getId(),
                updateUserDto.getFirstName(),
                updateUserDto.getLastName(),
                updateUserDto.getPhone()
        );
    }
}
