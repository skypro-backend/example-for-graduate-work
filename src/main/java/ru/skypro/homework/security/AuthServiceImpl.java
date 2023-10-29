package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import javax.transaction.Transactional;

/**
 * Реализация сервиса для регистрации пользователя и входа
 */
@Log
@Transactional
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String userName, String password) {
        log.info("вызван метод для авторизации пользователя");
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        log.info("пользователь не зарегистрирован");
        if (!userRepository.existsByEmailContains(userName) ||
                  !encoder.matches(password, userDetails.getPassword()))  {
            return false;
        }
        log.info("пользователь с паролем успешно авторизован");
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register (Register register) {
        if (userRepository.existsByEmailContains (register.getUsername())) {
            log.info("пользователь зарегистрирован, если пользователь с таким именем уже существует метод заканчивает работу");
            return false;
        }
        log.info("логинимся на роль USER");
        User user = userMapper.toUserRegister (register);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        if (register.getRole() == Role.ADMIN)  {
            log.info("создан новый администратор");
        } else {
            log.info("создан USER ");
        }
        return true;

    }


}
