package ru.skypro.homework.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.util.exceptions.InCorrectPasswordException;
import ru.skypro.homework.util.exceptions.NotFoundException;

public class CustomUserDetailsService implements UserDetailsManager {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

    @Override
    public void createUser(UserDetails user) {
        UserEntity result = new UserEntity();
        result.setLogin(user.getUsername());
        result.setPassword(user.getPassword());
        if(user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals(Role.ADMIN.name()))){
            result.setRole(Role.ADMIN);
        }else {
            result.setRole(Role.USER);
        }
        userRepo.save(result);

    }

    @Override
    public void updateUser(UserDetails user) {
        UserEntity updated = userRepo.findByLogin(user.getUsername());
        if(updated == null){
            throw new NotFoundException("Пользователь для обновления информации не найден");
        }
        updated.setLogin(user.getUsername());
        updated.setPassword(user.getPassword());
        if(user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals(Role.ADMIN.name()))){
            updated.setRole(Role.ADMIN);
        }else {
            updated.setRole(Role.USER);
        }
        userRepo.save(updated);
    }

    @Override
    public void deleteUser(String username) {
        UserEntity check = userRepo.findByLogin(username);
        if(check == null){
            throw new NotFoundException("Пользователь для удаления не найден");
        }
        userRepo.delete(check);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity update = userRepo.findByLogin(authentication.getName());
        if(update == null){
            throw new NotFoundException("Пользователь для обновления информации не найден");
        }
        if(update.getPassword().equals(passwordEncoder.encode(oldPassword))){
            throw new InCorrectPasswordException("Пароль введен неверно");
        }
        update.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(update);
    }

    @Override
    public boolean userExists(String username) {
        UserEntity check = userRepo.findByLogin(username);
        return check != null;
    }
}
