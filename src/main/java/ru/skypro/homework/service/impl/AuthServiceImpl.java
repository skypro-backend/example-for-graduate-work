package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.controller.AuthController;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Collections;

/**
 * Реализация сервиса аутентификации и регистрации пользователей.
 * <p>
 * Обеспечивает:
 * <ul>
 *   <li>Проверку учётных данных при входе ({@link #login(String, String)})</li>
 *   <li>Регистрацию новых пользователей с хешированием пароля ({@link #register(Register)})</li>
 *   <li>Проверку занятости телефона ({@link #isPhoneExists(String)})</li>
 * </ul>
 * Все операции выполняются через {@link UserRepository}, пароли хешируются с помощью {@link PasswordEncoder}.
 *
 * @see AuthService — основной интерфейс, используемый контроллерами
 * @see AuthController — использует этот сервис для обработки запросов на /login и /register
 * @see PasswordEncoder — безопасное хранение паролей (BCrypt)
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    /**
     * Репозиторий для доступа к данным пользователей.
     */
    private final UserRepository userRepository;

    /**
     * Кодировщик паролей, использующий алгоритм BCrypt.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Выполняет проверку учётных данных пользователя.
     * <p>
     * Находит пользователя по email и сравнивает переданный пароль с хешированным значением.
     *
     * @param userName логин (email) пользователя
     * @param password пароль в открытом виде
     * @return {@code true}, если пользователь существует и пароль верен; иначе — {@code false}
     */
    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByEmail(userName).map(user -> passwordEncoder.matches(password, user.getPassword())).orElse(false);
    }

    /**
     * Регистрирует нового пользователя в системе.
     * <p>
     * Проверяет уникальность email и телефона. Если один из них уже занят — возвращает {@code false}.
     * В противном случае создаёт нового пользователя с зашифрованным паролем и сохраняет его в БД.
     *
     * @param register DTO с данными нового пользователя: email, пароль, имя, фамилия, телефон, роль
     * @return {@code true}, если регистрация успешна; {@code false}, если email или телефон уже заняты
     */
    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }
        if (userRepository.findByPhone(register.getPhone()).isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setEmail(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(register.getRole());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());

        userRepository.save(user);
        return true;
    }

    /**
     * Проверяет, существует ли пользователь с указанным телефоном.
     * <p>
     * Используется при регистрации и обновлении профиля для обеспечения уникальности номера.
     *
     * @param phone телефон в формате строки (например, "+79991234567")
     * @return {@code true}, если пользователь с таким телефоном уже есть; иначе — {@code false}
     */
    @Override
    public boolean isPhoneExists(String phone) {
        return userRepository.findByPhone(phone).isPresent();
    }
}