package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.FileUploadService;
import ru.skypro.homework.service.PasswordService;
import ru.skypro.homework.service.UserMapperService;
import ru.skypro.homework.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapperService userMapperService;
    private final FileUploadService fileUploadService;
    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, UserMapperService userMapperService, FileUploadService fileUploadService, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.userMapperService = userMapperService;
        this.fileUploadService = fileUploadService;
        this.passwordService = passwordService;
    }

    public UserDto getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapperService.mapToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapperService::mapToDto).collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDTO) {
        User user = userMapperService.mapToEntity(userDTO);
        user = userRepository.save(user);
        return userMapperService.mapToDto(user);
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user = userMapperService.updateEntityFromDto(userDto, user);
        user = userRepository.save(user);
        return userMapperService.mapToDto(user);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void updateUserImage(Integer id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        String pathToFile = fileUploadService.uploadFile(file);
        user.setImagePath(pathToFile);
        userRepository.save(user);
    }

    public void updatePassword(Integer id, NewPasswordDto newPasswordDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        boolean isPasswordUpdated = passwordService.updatePassword(user, newPasswordDto);
        if (!isPasswordUpdated) {
            throw new RuntimeException("Password couldn't be updated for some reason");
        }
        userRepository.save(user);
    }
}
