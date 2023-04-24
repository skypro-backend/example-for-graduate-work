package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.exception.NotAuthorizedException;
import ru.skypro.homework.exception.notFound.UserNotFoundException;
import ru.skypro.homework.exception.WrongPasswordException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService( UserRepository userRepository, PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User getUser( Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        UserModel target = userRepository.findByUsername(authentication.getName()).orElseThrow(UserNotFoundException::new);
        return User.fromModel(target);
    }

    public User updateUser( User user, Authentication authentication ) {
        String username = authentication.getName();
        if (username == null) {
            throw new NotAuthorizedException();
        }
        UserModel currentUser = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        UserModel prevUserInDB = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        UserModel newUser = user.toModel();
        newUser.setPassword(prevUserInDB.getPassword());
        newUser.setImage(prevUserInDB.getImage());
        newUser.setRole(prevUserInDB.getRole());
        if (user.getId().equals(currentUser.getPk())) {
            userRepository.save(newUser);
        } else {
            throw new AccessDeniedException("нет прав менять профиль другого пользователя!");
        }
        return user;
    }

    public void updateUserImage( MultipartFile image, Authentication authentication ) {
        String username = authentication.getName();
        UserModel target = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        try {
            byte[] bytes = image.getBytes();
            target.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(target);
    }

    public byte[] getAvatarByUserId( Integer id ) {
        UserModel target = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return target.getImage();
    }

    public void changePassword( String name, String currentPassword, String newPassword, Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        UserModel target = userRepository.findByUsername(name).orElseThrow(UserNotFoundException::new);
        if (userRepository.existsByUsername(name)) {
            if (encoder.matches(currentPassword, target.getPassword())) {
                target.setPassword(encoder.encode(newPassword));
                userRepository.save(target);
            } else {
                throw new WrongPasswordException("Текущий пароль не совпадает!");
            }
        } else {
            throw new UserNotFoundException();
        }
    }

}
