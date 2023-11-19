package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User get(Long id) {
        logger.info("Method get was invoked!");
        return userRepository.findById(id).
                orElse(null);

    }

    @Override
    public User update(Long id, User user) {
        logger.info("Method add update invoked!");
        User userFromDB = get(id);
        if (userFromDB == null) {
            return null;
        }
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setPhone(user.getPhone());
        return userRepository.save(userFromDB);

    }

    @Override
    public User setPassword(User user) {
        logger.info("Method add update invoked!");
        User userFromDBForUpdatePassword = user.getId();
        if (userFromDBForUpdatePassword == null) {
            return null;
        }
        userFromDBForUpdatePassword.setPassword(user.getPassword());

        return userRepository.save(userFromDBForUpdatePassword);
    }
}
