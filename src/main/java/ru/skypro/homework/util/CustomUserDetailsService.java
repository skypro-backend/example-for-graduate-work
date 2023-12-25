package ru.skypro.homework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.util.exceptions.InCorrectPasswordException;
import ru.skypro.homework.util.exceptions.NotFoundException;

public class CustomUserDetailsService implements UserDetailsManager {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
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
        logger.info("Password in manager after login: {}", user.getPassword());
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
        logger.info("Password in manager: {}", user.getPassword());
        UserEntity result = new UserEntity();
        result.setLogin(user.getUsername());
        result.setPassword(user.getPassword());
        logger.info("Password in manager after create entity: {}", result.getPassword());
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
        logger.info("Hi in CustomUserDetailsService in changePassword()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Loggin User for change password: {}", authentication.getName());
        UserEntity update = userRepo.findByLogin(authentication.getName());
        if(update == null){
            throw new NotFoundException("Пользователь для обновления информации не найден");
        }
        if(update.getPassword().equals(passwordEncoder.encode(oldPassword))){
            throw new InCorrectPasswordException("Пароль введен неверно");
        }
        logger.info("Old password: {}", update.getPassword());
        update.setPassword(passwordEncoder.encode(newPassword));
        logger.info("New password: {}", update.getPassword());
        userRepo.save(update);
    }

    @Override
    public boolean userExists(String username) {
        UserEntity check = userRepo.findByLogin(username);
        return check != null;
    }
}
