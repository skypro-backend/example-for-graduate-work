package ru.skypro.homework.dto.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.model.UserModel;

import java.util.Collection;
import java.util.List;

@Data
public class MyUserDetails implements UserDetails {
    private Integer pk;
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String password;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        userModel.setPk(this.getPk());
        userModel.setUsername(this.getUsername());
        userModel.setFirstName(this.getFirstName());
        userModel.setLastName(this.getLastName());
        userModel.setPhone(this.getPhone());
        userModel.setPassword(this.getPassword());
        userModel.setRole(this.getRole());
        return userModel;
    }

    public static MyUserDetails fromModel( UserModel model ) {
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setPk(model.getPk());
        myUserDetails.setRole(model.getRole());
        myUserDetails.setPassword(model.getPassword());
        myUserDetails.setPhone(model.getPhone());
        myUserDetails.setUsername(model.getUsername());
        myUserDetails.setFirstName(model.getFirstName());
        myUserDetails.setLastName(model.getLastName());
        return myUserDetails;
    }
}
