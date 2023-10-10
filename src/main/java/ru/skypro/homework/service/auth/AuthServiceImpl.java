package ru.skypro.homework.service.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUserDetailsService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    /**
     * Проверяет существует ли в БД пользователь с указанным логином и паролем
     *
     * @param userName логин
     * @param password пароль
     * @return true если пользователь с указанным логином и паролем существует, или false в противном случае
     */
    @Override
    public boolean login(String userName, String password) {
        if (!userRepository.existsByEmailIgnoreCase(userName)) {
            return false;
        }
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Регистрирует нового пользователя, если в БД еще нет пользователя с указанным логином
     *
     * @param register информация о пользователе
     * @return true если новый пользователь зарегестрирован, или false если пользователь с указанным логином уже существует
     */
    @Override
    public boolean register(Register register) {
        if (userRepository.existsByEmailIgnoreCase(register.getUsername())) {
            return false;
        }
        createUser(register);
        return true;
    }

    /**
     * Сохраняет в БД нового пользователя
     *
     * @param register информация о пользователе
     */
    @Override
    public void createUser(Register register){
        User newUser = new User()
                .setPhone(register.getPhone())
                .setRole(register.getRole())
                .setEmail(register.getUsername())
                .setFirstName(register.getFirstName())
                .setLastName(register.getLastName())
                .setPassword(encoder.encode(register.getPassword()));
        userRepository.save(newUser);
    }
}
