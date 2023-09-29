package ru.skypro.homework.mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.entities.UserEntity;


@Component
public class UserMapper {

    public UpdateUserDTO toUpdateUserDto(UserEntity userEntity) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName(userEntity.getFirstName());
        updateUserDTO.setLastName(userEntity.getLastName());
        updateUserDTO.setPhone(userEntity.getPhone());
        return updateUserDTO;
    }

    public UserDTO toUserDto(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setImage(userEntity.getImage());
        return userDTO;
    }

}
