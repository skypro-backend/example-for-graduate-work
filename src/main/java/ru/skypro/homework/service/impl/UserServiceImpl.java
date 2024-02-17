package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UsersMapper usersMapper;
    public UserServiceImpl(UserRepository userRepository, UsersMapper usersMapper) {
        this.userRepository = userRepository;
        this.usersMapper = usersMapper;
    }


    @Override
    public void updateUserEntity(User user) {
        UserEntity userEntity = usersMapper.mapToUserEntity(user);
        userRepository.save(userEntity);
    }
}