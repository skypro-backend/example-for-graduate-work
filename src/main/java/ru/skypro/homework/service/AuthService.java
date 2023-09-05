package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AuthService {

    /**
     * Метод login с помощью имени пользователя и пароля
     *   проверяет пользователей при входе на сайт.
     * @param userName - проверяет имя пользователя
     * @param password - проверяет пароль пользователя
     * @return Если пользователь не найден или пароль не верен
     * выводит в консоль false.
     */
    boolean login(String userName, String password);


    /** Метод register регистрирует новых
     * пользователей, в случае недопустимых
     * данных выводит ошибку в консоль.
     * @param register - ввести параметры
     * для регистрации будущего пользователя
     * @return возвращает false если введённые
     * данные не корректны.
     * Возвращает true - если регистрация
     * прошла успешно.
     */
    boolean register(Register register);
}
