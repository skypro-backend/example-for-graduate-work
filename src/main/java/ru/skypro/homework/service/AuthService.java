package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Сервис для аутентификации и регистрации пользователей.
 * Обеспечивает проверку учетных данных при входе и сохранение новых пользователей.
 */
public interface AuthService {

    /**
     * Проверяет корректность введенного логина и пароля при входе в систему.
     *
     * @param userName логин пользователя (email)
     * @param password чистый (незахешированный) пароль пользователя
     * @return {@index true}, если учетные данные верны, иначе {@index false}
     */
    boolean login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе с шифрованием его пароля.
     *
     * @param register форма с регистрационными данными пользователя {@link Register}
     * @return {@index true}, если регистрация прошла успешно, или {@index false}, если логин уже занят
     */
    boolean register(Register register);
}
