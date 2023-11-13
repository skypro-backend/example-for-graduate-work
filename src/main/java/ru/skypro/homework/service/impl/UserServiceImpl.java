package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;
import ru.skypro.homework.Exceptions.*;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.*;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final ImageService imageService;

    @Override
    public UpdateUserDto update(UpdateUserDto user, String name) {
        return mapper.entityToUpdateUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public void delete(String name) {
        userRepository.deleteByUsername(name);
    }

    @Override
    public UserDto get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    @Override
    public User getEntity(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new FindNoEntityException("User with name: " + name + "not found"));
    }

    @Override
    public User getEntityById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new FindNoEntityException("There is no user with the specified id"));
    }

    @Override
    public void changePassword(String newPassword, String name) {
        User entity = getEntity(name);
        entity.setPassword(newPassword);
        userRepository.save(entity);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void uploadImage(MultipartFile image, String username) throws IOException {
        User user = getEntity(username);
        Image imageEntity = user.getImage();
        user.setImage(imageService.saveImage(image));
        userRepository.save(user);
        if (imageEntity != null) {
            imageService.deleteImage((Image) image);
        }
    }

}
