package ru.skypro.homework.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MyDatabaseUserDetails implements UserDetails {

    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                this.userEntity.getRole().equals(Role.ADMIN)
                        ? new SimpleGrantedAuthority("ADMIN")
                        : new SimpleGrantedAuthority("USER");
        // todo: проверить
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

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

    public static MyDatabaseUserDetails fromUserEntity(UserEntity userEntity) {
        return new MyDatabaseUserDetails(userEntity);
    }

    public UserEntity toUserEntity() {
        return this.userEntity;
    }
}
