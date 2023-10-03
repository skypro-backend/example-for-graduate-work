package ru.skypro.homework.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
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
        return null;

    }

    @Override
    public UserDTO updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile image) {

    }
}
