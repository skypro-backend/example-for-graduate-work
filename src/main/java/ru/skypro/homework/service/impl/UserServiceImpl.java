package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    /**
     * Finding user by his authentication using {@link UserRepository#findByUsername(String)},
     * @param authentication
     * @return UserDTO
     */
    @Override
    public UserDTO findUser(Authentication authentication) {
        String username = authentication.getName();
        return userMapper.mapToDTO(userRepository.findByUsername(username));
    }

    /**
     * Editing user data using {@link Authentication#getName()}, {@link UserMapper#saveFromUpdate(String, UpdateUser)}
     * @param updateUserDto
     * @param authentication
     * @return UpdateUser
     */
    @Override
    public UpdateUser editUser(UpdateUser updateUserDto, Authentication authentication) {
        String username = authentication.getName();
        userMapper.saveFromUpdate(username, updateUserDto);
        return updateUserDto;
    }

    /**
     * Updating user image using {@link UserRepository#findByUsername(String)}, {@link ImageService#uploadImage(MultipartFile)},
     * {@link UserRepository#save(Object)}, {@link ImageMapper#mapToDTO(Image)}
     * @param image
     * @param authentication
     * @return String
     * @throws IOException
     */
    @Override
    public String editUserImage(MultipartFile image,
                                Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Image imageToDB = imageService.uploadImage(image);
        user.setImage(imageToDB);
        userRepository.save(user);
        return imageMapper.mapToDTO(imageToDB).getUrl();
    }

    /**
     * Password update
     * Method used {@link UserRepository#findByUsername(String)}
     * @param authentication
     * @param newPassword
     * @return boolean
     */
    @Override
    public boolean setPassword(NewPassword newPassword,
                               Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if(passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            String password = passwordEncoder.encode(newPassword.getNewPassword());
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } else
            return false;
    }

}
