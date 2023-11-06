package ru.skypro.homework.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.models.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
public class SecurityUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static SecurityUser fromUser(User user) {
        return SecurityUser.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(user.getRole()))
                .build();
    }

}
