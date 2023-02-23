package ru.skypro.homework.security;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.UserEntity;


@Data
public class SecurityUser implements UserDetails {

  private final String userEmail;
  private final String userPassword;

  public SecurityUser(String userEmail, String userPassword) {
    this.userEmail = userEmail;
    this.userPassword = userPassword;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return userPassword;
  }

  @Override
  public String getUsername() {
    return userEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public static UserDetails fromUser(UserEntity userEntity){
    return new User(userEntity.getEmail(),userEntity.getPassword(),new ArrayList<>());
  }
}
