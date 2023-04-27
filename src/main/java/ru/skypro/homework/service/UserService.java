package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.MyUserDetails;
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
         UserModel target = ((MyUserDetails) authentication.getPrincipal()).getUser();
        return User.fromModel(target);
    }

    public User updateUser( User user, Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        UserModel currentUser = ((MyUserDetails) authentication.getPrincipal()).getUser();
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setPhone(user.getPhone());
        userRepository.save(currentUser);
        return User.fromModel(currentUser);
    }

    public void updateUserImage( MultipartFile image, Authentication authentication ) {
        UserModel target = ((MyUserDetails) authentication.getPrincipal()).getUser();
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

    public void changePassword(String currentPassword, String newPassword, Authentication authentication ) {
        if (!authentication.isAuthenticated()) {
            throw new NotAuthorizedException();
        }
        UserModel user = ((MyUserDetails) authentication.getPrincipal()).getUser();
        if (!encoder.matches(currentPassword, user.getPassword())) {
            throw new WrongPasswordException("Текущий пароль не совпадает!");
        }
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }

}
