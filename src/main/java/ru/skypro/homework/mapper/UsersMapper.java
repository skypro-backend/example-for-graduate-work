package ru.skypro.homework.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Users;

@Service
public class UsersMapper {

    private final ModelMapper mapper;

    public UsersMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO toDTO(Users users) {
        return mapper.map(users, UserDTO.class);
    }

    public Users toEntity(RegisterDTO dto) {
        return mapper.map(dto, Users.class);
    }
}
