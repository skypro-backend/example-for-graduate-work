package ru.skypro.homework.service.users.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.repository.users.UsersRepository;

@Service
public class UserCustomService implements UserDetailsService, UserDetailsManager {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public UserCustomService(UsersRepository usersRepository,
                             PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким логином не найден. Логин: " + username);
        }
        return new UserCustom(user);
    }

    @Override
    @Transactional
    public void createUser(UserDetails userCustom) {
        if (!(userCustom instanceof UserCustom)) {
            throw new IllegalArgumentException("UserDetail должен быть экземляром класса UserCustom");
        }
        User user = ((UserCustom) userCustom).getUser();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserDetails userCustom) {
        if (!(userCustom instanceof UserCustom)) {
            throw new IllegalArgumentException("UserDetail должен быть экземляром класса UserCustom");
        }
        User user = ((UserCustom) userCustom).getUser();
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        User user = usersRepository.findByUsername(username);
        if (user != null) {
            usersRepository.delete(user);
        }
    }

    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCustom userCustom = (UserCustom) authentication.getPrincipal();
        User author = userCustom.getUser();
        String currentPassword = author.getPassword();
        if (passwordEncoder.matches(oldPassword, currentPassword)) {
            String encodeNewPassword = passwordEncoder.encode(newPassword);
            author.setPassword(encodeNewPassword);
            usersRepository.save(author);
        } else {
            throw new BadCredentialsException("Введенный пароль не соответствует действующему.");
        }
    }

    @Override
    public boolean userExists(String username) {
        User user = usersRepository.findByUsername(username);
        return user != null;
    }
}
