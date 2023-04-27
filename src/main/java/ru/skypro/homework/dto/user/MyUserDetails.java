package ru.skypro.homework.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.model.UserModel;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class MyUserDetails implements UserDetails {
    private final UserModel user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setPk(this.user.getPk());
        userModel.setUsername(this.user.getUsername());
        userModel.setFirstName(this.user.getFirstName());
        userModel.setLastName(this.user.getLastName());
        userModel.setPhone(this.user.getPhone());
        userModel.setPassword(this.user.getPassword());
        userModel.setRole(this.user.getRole());
        return userModel;
    }
}
