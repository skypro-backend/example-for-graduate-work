package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsManager manager; // Твоя реализация UserDetailsManager
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public boolean login(String userName, String password) {
        // Проверяем, существует ли пользователь в БД
        if (!manager.userExists(userName)) {
            log.warn("Пользователь с именем {} не найден", userName);
            return false;
        }

        // Загружаем детали пользователя (включая хеш пароля)
        UserDetails userDetails = manager.loadUserByUsername(userName);

        // КРИТЕРИЙ №9: Проверяем, совпадает ли введенный пароль с тем, что в БД (используя matches)
        if (!encoder.matches(password, userDetails.getPassword())) {
            log.warn("Неверный пароль для пользователя {}", userName);
            return false;
        }

        log.info("Пользователь {} успешно авторизован", userName);
        return true;
    }

    @Override
    @Transactional
    public boolean register(Register register) {
        // Проверка: если пользователь уже есть, регистрация невозможна
        if (manager.userExists(register.getUsername())) {
            log.warn("Пользователь {} уже существует", register.getUsername());
            return false;
        }

        // Создаем новую сущность пользователя для БД
        UserEntity newUser = new UserEntity();
        newUser.setEmail(register.getUsername());

        // КРИТЕРИЙ №10: Шифруем пароль перед сохранением
        newUser.setPassword(encoder.encode(register.getPassword()));

        // Связываем остальные данные из DTO (Критерий №4 и №7)
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setPhone(register.getPhone());
        newUser.setRole(register.getRole());

        // Сохраняем пользователя в БД через репозиторий
        userRepository.save(newUser);

        log.info("Пользователь {} успешно зарегистрирован", register.getUsername());
        return true;
    }
}