package ru.skypro.homework.service.users.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.mappers.UserMapper;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        usersRepository.save(user);
    }

}
