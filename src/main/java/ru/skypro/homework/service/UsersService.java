package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.AdPicture;
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

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userDTO.toUser();
        user.setId(null);
        User savedUser = usersRepository.save(user);
        return UserDTO.fromUser(savedUser);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userDTO.toUser();
        User savedUser = usersRepository.save(user);
        return UserDTO.fromUser(savedUser);
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

    // todo исправить, чтобы выдавала авториз. юзера
    public UserDTO getAuthorisedUser() {
        User user = usersRepository.findById(1L).orElse(new User());
        return UserDTO.fromUser(user);
    }
}
