package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.manager.CustomUserDetailsService;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final CustomUserDetailsService manager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(CustomUserDetailsService manager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    //Метод аутентификации пользователя.
    @Override
    public boolean login(String userName, String password) {
        log.info("Attempting login for user: {}", userName);
        UserDetails userDetails = manager.loadUserByUsername(userName);  // Загрузка информации о пользователе по его имени пользователя
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {    // Проверка соответствия введенного пароля хэшированному паролю пользователя
            throw new InvalidPasswordException("Invalid password");   // В случае несоответствия, выбрасывается исключение InvalidPasswordException
        }
        return true;                                                  // Возврат true в случае успешной аутентификации
    }

    // Метод регестрации нового  пользователя.
    @Override
    public boolean register(RegisterDTO register) {
        log.info("Attempting registration");
        if (userRepository.findByEmail(register.getUsername()) != null) {     // Проверка, существует ли пользователь с указанным email
            return false;                                                    // Возвращение false, если пользователь с таким email уже существует
        }
        register.setPassword(passwordEncoder.encode(register.getPassword()));         // Хэширование пароля перед сохранением в базе данных
        userRepository.save(UserMapper.INSTANCE.registerDTOToUser(register));  // Сохранение нового пользователя в базе данных
        return true;                                                           // Возвращение true в случае успешной регистрации
    }

}
