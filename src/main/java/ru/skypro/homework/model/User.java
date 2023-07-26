package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.RoleDto;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pk_id")
    private List<Ad> ad;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleDto roleDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleDto> roles = new HashSet<>();
        roles.add(this.roleDto);
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
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

}
