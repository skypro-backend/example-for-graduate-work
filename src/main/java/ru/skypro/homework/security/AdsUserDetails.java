package ru.skypro.homework.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.Users;


import java.util.*;

@Getter
public class AdsUserDetails implements UserDetails {

    private final UsersDTO usersDTO;

    public AdsUserDetails(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(usersDTO)
                .map(UsersDTO::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    @Override
    public String getPassword() {
        return usersDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return usersDTO.getUsername();
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




    public Users toAdsUserDetails() {
        return new Users(usersDTO.getId(),
                usersDTO.getImage(),
                usersDTO.getUsername(),
                usersDTO.getPassword(),
                usersDTO.getFirstName(),
                usersDTO.getLastName(),
                usersDTO.getPhone(),
                usersDTO.getRole(),
                new ArrayList<>());
    }




}