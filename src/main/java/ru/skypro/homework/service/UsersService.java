package ru.skypro.homework.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Users;

@Service
public class UsersService {

    private final ModelMapper mapper;

    public UsersService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO toDTO(Users users) {
        return mapper.map(users, UserDTO.class);
    }

    public Users toEntity(UserDTO users) {
        return mapper.map(users, Users.class);
    }
}
