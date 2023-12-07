package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapping;
    private final ImageService imageService;

    public UserService(UserRepository userRepository, UserMapper userMapping, ImageService imageService) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.imageService = imageService;
    }


    public boolean userExists(String username) {
        return userRepository.existsUserByUsername(username);
    }
    /**
     * Поиск пользователя по логину.
     */
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    /**
     * Получение информации об авторизованном пользователе.
     */
    public UserDTO getUserInfo(String username) {

        return userMapping.mapToUserDto(userRepository.findByUsername(username));
    }
    /**
     * Обновление информации об авторизованном пользователе.
     */
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, String name) {
        User updateUser = userRepository.findByUsername(name);
        updateUser.setLastName(updateUserDTO.getLastName());
        updateUser.setFirstName(updateUserDTO.getFirstName());
        updateUser.setPhone(updateUserDTO.getPhone());
        userRepository.save(updateUser);
        return userMapping.mapToUpdateUserDTO(updateUser);
    }
    /**
     * Обновление аватара авторизованного пользователя.
     */
    public void editUserImage(MultipartFile imageFile, String username) throws IOException {
        User updateUser = userRepository.findByUsername(username);
        Image oldImage = updateUser.getImage();
        updateUser.setImage(imageService.uploadImage(imageFile));
        userRepository.save(updateUser);
        imageService.deleteImage(oldImage);
    }

}
