package ru.skypro.kakavito.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skypro.kakavito.dto.*;
import ru.skypro.kakavito.model.User;

import java.util.Optional;

/**
 * Маппер для преобразования сущности User
 *
 * @see User
 */
@Data
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Value("${query.to.get.image}")
    private String imageQuery;

    /**
     * Конвертирует в UpdateUserDTO
     *
     * @param user
     * @return UpdateUserDTO
     * @see UpdateUserDTO
     */
    public UpdateUserDTO convertToUpdateUserDTO(User user) {
        UpdateUserDTO updateUserDTO = modelMapper.map(user, UpdateUserDTO.class);

        return updateUserDTO;
    }

    /**
     * Конвертирует в UserDTO
     *
     * @param user
     * @return UserDTO
     * @see UserDTO
     */
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        Optional.ofNullable(user.getImage())
                .ifPresent(elem -> userDto.setImage(imageQuery + user.getImage().getId()));
        return userDto;
    }

}
