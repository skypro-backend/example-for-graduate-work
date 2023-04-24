package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.UsersRepository;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final AvatarRepository avatarRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = usersRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("Пользователь " + userDTO.getEmail() + " не найден"));
        User userToSave = userDTO.toUser();
        userToSave.setPassword(existingUser.getPassword());
        userToSave.setRole(existingUser.getRole());
        return UserDTO.fromUser(usersRepository.save(userToSave));
    }

    public void setAvatar(Long userId, MultipartFile image) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь с ид. " + userId));
        user.setImage(saveAvatar(image));
        usersRepository.save(user);
    }

    private String saveAvatar(MultipartFile image) {
        Avatar avatar = new Avatar();
        try {
            byte[] bytes = image.getBytes();
            avatar.setData(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String id = "/avatar/" + RandomStringUtils.randomAlphabetic(4);
        avatar.setId(id);
        Avatar savedAvatar = avatarRepository.save(avatar);

        return savedAvatar.getId();
    }

    public UserDTO getAuthorisedUser(String username) {
        User user = usersRepository.findByEmail(username).orElseThrow(() ->
                new NotFoundException("Пользователь " + username + " не найден"));

        return UserDTO.fromUser(user);
    }

    public void setPassword(Long userId, NewPasswordDTO newPasswordDTO) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с ид." + userId + " не найден"));
        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new ForbiddenException();
        }
        user.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
        usersRepository.save(user);

    }
}
