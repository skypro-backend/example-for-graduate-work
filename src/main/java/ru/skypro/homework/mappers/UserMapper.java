package ru.skypro.homework.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Data
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Value("${query.to.get.image}")
    private String imageQuery;


    public UpdateUserDTO convertToUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = modelMapper.map(user, UpdateUserDTO.class);

        return updateUserDTO;
    }

//    public UserDto convertToUserDTO(User user) {
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        Optional.ofNullable(user.getImage())
//                .ifPresent(elem -> {
//                    UserDto.setImage(imageQuery + user.getImage().getId());
//                });
//        return userDto;
//    }

}
