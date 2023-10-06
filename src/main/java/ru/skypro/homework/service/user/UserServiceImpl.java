package ru.skypro.homework.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.custom_exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;
import ru.skypro.homework.repository.UserRepository;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public boolean setPassword(NewPassword newPassword) {
        return true;
    }

    @Override
    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UserMapper.toDTO(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDTO updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile image) {

    }
}
