package ru.skypro.homework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.model.User;
import java.util.Collection;
import java.util.Collections;

/**
 * Реализация интерфейса {@link UserDetails}, представляющая обертку (Principal)
 * над нашей доменной сущностью {@link User} для инфраструктуры Spring Security.
 * <p>
 * Класс адаптирует профиль пользователя из базы данных под требования безопасности фреймворка,
 * обеспечивая проверку ролей (USER/ADMIN), паролей и статуса активности аккаунта.
 */
public class MyUserPrincipal implements UserDetails {

    /** Сущность пользователя, извлеченная из базы данных PostgreSQL */
    private final User user;

    /**
     * Конструктор для создания объекта аутентифицированного пользователя.
     *
     * @param user доменная модель пользователя из БД
     */
    public MyUserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Предоставляет прямой доступ к исходной сущности пользователя.
     * Используется в бизнес-логике сервисов для извлечения ID, аватара или других полей профиля.
     *
     * @return объект {@link User}
     */
    public User getUser() {
        return user;
    }

    /**
     * Возвращает коллекцию прав (авторитетов) текущего пользователя на основе его роли в БД.
     * <p>
     * <b>Важная деталь реализации:</b> к названию перечисления {@link ru.skypro.homework.dto.Role}
     * принудительно добавляется стандартный префикс <b>"ROLE_"</b> (например, ROLE_USER или ROLE_ADMIN).
     * Это критически важно для корректной работы SpEL-выражений типа hasRole() и аннотаций @PreAuthorize.
     *
     * @return неизменяемый список, содержащий назначенные права пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    /**
     * Возвращает захешированный пароль пользователя, сохраненный в базе данных.
     * Используется Spring Security для сверки с паролем, введенным в форму авторизации.
     *
     * @return строка с хэшем пароля BCrypt
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Возвращает уникальное имя пользователя, которое является его главным логином в системе.
     * В рамках данного проекта логином выступает адрес электронной почты (email).
     *
     * @return строка с email пользователя
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Проверяет, не истек ли срок действия учетной записи пользователя.
     *
     * @return {@code true}, так как в текущей бизнес-логике срок действия аккаунтов не ограничен
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирован ли пользователь администрацией системы.
     *
     * @return {@code true}, так как функционал блокировки (бан-лист) на данном этапе не предусмотрен
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истек ли срок действия пароля (учетных данных) пользователя.
     *
     * @return {@code true}, так как принудительное устаревание паролей в системе отключено
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активна ли учетная запись пользователя в системе.
     * Используется для подтверждения почты или активации профиля.
     *
     * @return {@code true}, так как все зарегистрированные пользователи сразу получают активный статус
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}