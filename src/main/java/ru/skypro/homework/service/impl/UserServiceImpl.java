package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO getUser(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage()
        );
    }

    @Override
    public UserDTO updateUser(String userName, UpdateUser updateUser) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage()
        );
    }
}