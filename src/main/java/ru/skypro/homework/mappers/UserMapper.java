package ru.skypro.homework.mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.entities.UserEntity;


@Component
public class UserMapper {

    public UserEntity userEntityUpdate(UpdateUserDTO updateUserDTO,
                                       UserEntity userEntity) {
        userEntity.setFirstName(updateUserDTO.getFirstName());
        userEntity.setLastName(updateUserDTO.getLastName());
        userEntity.setPhone(updateUserDTO.getPhone());
        return userEntity;
    }

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
        if (userEntity.getImageEntity() == null){
            userDTO.setImage(null);
        }else
            userDTO.setImage("/images/" + userEntity.getImageEntity().getImageName());
        userDTO.setPhone(userEntity.getPhone());
        return userDTO;
    }

    public UserEntity toUserEntity(Register register) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getUsername());
        userEntity.setPassword(passwordEncoder().encode(register.getPassword()));
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setRole(register.getRole());
        return userEntity;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
