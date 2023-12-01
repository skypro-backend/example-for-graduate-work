package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.MethodLog;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ImageService imageService;


    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository, ImageService imageService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public UserDTO getCurrentUser() {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        return UserMapper.INSTANCE.toUserDTO(user);

    }


    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        UserMapper.INSTANCE.updateUserDTOToUser(updateUserDTO, user);

        return UserMapper.INSTANCE.toUserDTO(userRepository.save(user));
    }

    @Override
    public Void setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        if (user == null) {
            // Обработка случая, если пользователя не существует
            throw new UserNotFoundException("User not found");
        }

        // Проверка, что старый пароль совпадает
        if (!encoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            // Обработка случая, если старый пароль не совпадает
            throw new IncorrectPasswordException("Incorrect old password");
        }

        // Обновление пароля
        String hashedPassword = encoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return null;
    }

    @Override
    @Transactional
    public Void updateUserImage(MultipartFile image, String userName) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        User user = userRepository.findByEmail(userName);
        if (user.getImage() == null) {
            user.setImage(imageService.addImage(image));
            userRepository.save(user);
            return null;
        }
        Long imageId = user.getImage().getId();
        user.setImage(imageService.addImage(image));
        imageService.deleteImage(imageId);
        userRepository.save(user);
        return null;
    }
}
