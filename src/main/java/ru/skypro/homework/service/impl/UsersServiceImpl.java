package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.NullEmailException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.ProfileUser;
import ru.skypro.homework.model.mapper.UserMapper;
import ru.skypro.homework.model.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto update(UserDto user, String email) {
        if (isEmpty(email)) {
            throw new NullEmailException();
        }
        user.setEmail(email);
        ProfileUser userProfile = userRepository.findByEmail(email).orElse(new ProfileUser());
        UserMapper.INSTANCE.partialUpdate(user, userProfile);
        return UserMapper
                .INSTANCE
                .userToUserDto(userRepository.save(userProfile));
    }

    @Override
    public void updateUserImage() {
        //TODO image
    }

    @Override
    public UserDto getUser(String email) {
        ProfileUser user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserMapper
                .INSTANCE
                .userToUserDto(user);
    }

}
