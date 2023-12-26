package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.security.GetAuthentication;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final ImageService imageService;
    private final PasswordEncoder encoder;

    @Override
    public UserDTO getUser(Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        return mapper.toDto(user);
    }

    @Override
    public void setPassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        if (encoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(newPasswordDTO.getNewPassword()));
            repository.save(user);
            return;
        }
        throw new IncorrectPasswordException("Неверный пароль");

    }

    @Override
    public UpdateUserDTO updateUserInfo(UpdateUserDTO updateUserDTO, Authentication authentication) {

        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(user.getLastName());
        user.setPhone(user.getPhone());
        repository.save(user);
        return updateUserDTO;
    }

    @Override
    @Transactional
    public void updateUserAvatar(MultipartFile image, Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        Image imageFile = user.getImage();
        user.setImage(imageService.uploadImage(image));
        if (image != null) {
            imageService.removeImage(imageFile);
        }
        repository.save(user);

    }
}
