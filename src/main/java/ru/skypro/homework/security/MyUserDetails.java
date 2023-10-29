package ru.skypro.homework.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;

import java.util.*;

/**
 * Класс является реализацией UserDetails интерфейса, предоставляемого Spring Security
 */
public class MyUserDetails implements UserDetails {
      private User user ;

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {

            Set <Role> roles = Collections.singleton (user.getRole ());
            List <SimpleGrantedAuthority> authorities = new ArrayList <> ();

            for (Role role : roles) {
                  authorities.add(new SimpleGrantedAuthority(role.name ()));
            }
            return authorities;
      }

      @Override
      public String getPassword () {
            return null;
      }

      @Override
      public String getUsername () {
            return null;
      }

      @Override
      public boolean isAccountNonExpired () {
            return false;
      }

      @Override
      public boolean isAccountNonLocked () {
            return false;
      }

      @Override
      public boolean isCredentialsNonExpired () {
            return false;
      }

      @Override
      public boolean isEnabled () {
            return false;
      }
}
