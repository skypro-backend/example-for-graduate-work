package ru.skypro.homework.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.model.UserEntity; // ИМПОРТИРУЕМ ENTITY

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private final UserEntity userEntity; // Используем UserEntity

    public MyUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword(); // ТЕПЕРЬ ОШИБКИ НЕ БУДЕТ
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail(); // В качестве username используем почту
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Превращаем роль из БД в формат Spring Security ("ROLE_USER" или "ROLE_ADMIN")
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name());
        return Collections.singletonList(authority);
    }

    // Остальные методы просто возвращают true
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}