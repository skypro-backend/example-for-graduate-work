package ru.skypro.homework.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;
import ru.skypro.homework.repository.UserRepository;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<?> setPassword(NewPassword newPassword) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCurrentUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateImage(MultipartFile image) {
        return null;
    }
}
