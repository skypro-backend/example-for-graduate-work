package ru.skypro.homework.dto;

/**
 * DTO для запроса авторизации пользователя.
 * Содержит учетные данные для входа в систему.
 */
public class Login {
    private String username;
    private String password;

    /**
     * Получает имя пользователя (логин).
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя (логин).
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
