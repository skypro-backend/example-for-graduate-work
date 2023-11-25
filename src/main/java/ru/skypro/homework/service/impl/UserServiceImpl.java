package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto get(Integer id) {
        logger.info("Method get was invoked!");
//        return (UserDto) userRepository.findById(id)
//                .orElse(null);
        return null;

    }

    @Override
    public UserDto update(Integer id, UserDto userDto) {
        logger.info("Method add update invoked!");
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userDtoFromDB -> {
            userDtoFromDB.setFirstName(userDto.getFirstName());
            userDtoFromDB.setLastName(userDto.getLastName());
            userDtoFromDB.setPhone(userDto.getPhone());
            userRepository.save(userDtoFromDB);
        });

        return get(id);
    }

//    @Override
//    public User setPassword(User user) {
//        logger.info("Method add update invoked!");
//        User userFromDBForUpdatePassword = user.getId();
//        if (userFromDBForUpdatePassword == null) {
//            return null;
//        }
//        userFromDBForUpdatePassword.setPassword(user.getPassword());
//
//        return userRepository.save(userFromDBForUpdatePassword);
//    }
}

