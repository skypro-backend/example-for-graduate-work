package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.PhotoAd;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        UserMapper.INSTANCE.updateUserDTOToUser(updateUserDTO, user);
        return UserMapper.INSTANCE.userToUpdateUserDTO(userRepository.save(user));
    }

    @Override
    public Void setPassword(NewPasswordDTO newPasswordDTO) {
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
    public Void updateUserImage(MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        user.setImage(image.getName());
        userRepository.save(user);
        return null;
    }
}
