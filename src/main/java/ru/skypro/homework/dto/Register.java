package ru.skypro.homework.dto;

/**
 * DTO для регистрации нового пользователя.
 * Содержит все необходимые данные для создания учетной записи.
 */
public class Register {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

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

    /**
     * Получает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param firstName имя пользователя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Получает фамилию пользователя.
     *
     * @return фамилия пользователя
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Устанавливает фамилию пользователя.
     *
     * @param lastName фамилия пользователя
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Получает номер телефона пользователя.
     *
     * @return номер телефона
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Устанавливает номер телефона пользователя.
     *
     * @param phone номер телефона
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Получает роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя
     */
    public void setRole(Role role) {
        this.role = role;
    }
}