package ru.skypro.homework.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.custom_exception.UserNotFoundException;
import ru.skypro.homework.exception.custom_exception.WrongPassException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;
import ru.skypro.homework.projection.UserView;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.image.ImageService;

import java.io.IOException;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;


    @Override
    public void setPassword(NewPassword newPassword,Authentication authentication) {
        UserDTO user = getCurrentUser(authentication);
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new WrongPassException();
        }
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(UserMapper.fromDTO(user));
    }

    @Override
    public UserDTO getCurrentUser(Authentication authentication) {
        return UserMapper.toDTO(userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }
    @Override
    public UserView getUserView(Authentication authentication) {
        return userRepository.findViewByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        UserDTO current = getCurrentUser(authentication)
                .setPhone(updateUser.getPhone())
                .setFirstName(updateUser.getFirstName())
                .setLastName(updateUser.getLastName());
        UserDTO actual = UserMapper.toDTO(userRepository.save(UserMapper.fromDTO(current)));
        return new UpdateUser()
                .setPhone(actual.getPhone())
                .setLastName(actual.getLastName())
                .setFirstName(actual.getFirstName());
    }

    @Override
    public void updateImage(MultipartFile image, Authentication authentication) throws IOException {
        String URL = imageService.createImage(image);
        UserDTO userDTO = UserMapper.toDTO(userRepository.findByEmailIgnoreCase(getCurrentUser(authentication).getEmail()).orElseThrow(UserNotFoundException::new));
        userDTO.setImage(URL);
        userRepository.save(UserMapper.fromDTO(userDTO));
    }
}
