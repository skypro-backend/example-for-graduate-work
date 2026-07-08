package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Integer register(Register data) {
        if (userRepo.existsByUsername(data.getUsername())) {
            throw new IllegalStateException("Пользователь уже существует");
        }

        UserEntity user = new UserEntity(
                data.getUsername(),
                encoder.encode(data.getPassword()),
                data.getFirstName(),
                data.getLastName(),
                data.getPhone(),
                data.getRole()
        );

        return userRepo.save(user).getId().intValue();
    }

    @Override
    public Boolean login(String userName, String password) {
        Optional<UserEntity> user = userRepo.findByUsername(userName);
        if (user.isEmpty()) {
            return false;
        }
        return encoder.matches(password, user.get().getPassword());
    }
}