package ru.skypro.homework.service.impl;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapping.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@Data
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    /**
     * @param user
     * @return
     */
    @Override
    public UserDto saveUser(UserDto user) {
        return null;
    }

    @Override
    public UserDto deleteUser(UserDto user) {
        logger.info("deleting user not supported by current version");
        return null;
    }

    /**
     * @return
     */
    @Override
    public UserDto getInfoUser(String userName) {
        if (userName.isEmpty()) {
            return new UserDto();
        }
        return UserMapper.INSTANCE.userToDto(userRepository.findByEmail(userName).orElse(null));
    }

    @Override
    public UpdateUserDto setInfoUser(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public AvatarDto setAvatar(MultipartFile avatar) throws IOException {
        return null;
    }


}
