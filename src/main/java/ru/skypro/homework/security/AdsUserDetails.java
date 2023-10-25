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
//    private final Integer id;
//    private final String username;
//    private final String password;
//    private final boolean isAdmin;
//    private final String email;
//    private final String image;
//    private final String firstName;
//    private final String lastName;
//    private final String phone;



//    public AdsUserDetails(Integer id, String username, String password, boolean isAdmin, String email, String image, String firstName, String lastName, String phone) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.isAdmin = isAdmin;
//        this.email = email;
//        this.image = image;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//    }


    public AdsUserDetails(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority =
//                isAdmin
//                        ? new SimpleGrantedAuthority("Role_" + ADMIN.toString())
//                        : new SimpleGrantedAuthority("Role_" + USER.toString());
//        return Collections.singleton(authority);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(usersDTO)
                .map(UsersDTO::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }

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

//    public static AdsUserDetails from(Users user) {
//        boolean isAdminForUser = user.getRole() == ADMIN;
//        return new AdsUserDetails(user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                isAdminForUser,
//                user.getEmail(),
//                user.getImage(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getPhone());
//    }

//    public Users toAdsUserDetails() {
//        Role role = this.isAdmin ? ADMIN: USER;
//        return new Users(this.id,
//                this.email,
//                this.image,
//                this.username,
//                this.password,
//                this.firstName,
//                this.lastName,
//                this.phone,
//                role,
//                new ArrayList<>());
//    }

    public Users toAdsUserDetails() {
        return new Users(usersDTO.getId(),
                usersDTO.getEmail(),
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