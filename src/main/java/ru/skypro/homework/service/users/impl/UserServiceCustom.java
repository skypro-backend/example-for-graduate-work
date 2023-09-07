package ru.skypro.homework.service.users.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.repository.users.UsersRepository;

@Service
public class UserServiceCustom implements UserDetailsService, UserDetailsManager {

    private final UsersRepository usersRepository;

    public UserServiceCustom(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String name = currentUser.getName();
        User user = usersRepository.findByUsername(name);
        String currentPassword = user.getPassword();
        if (currentPassword.equals(oldPassword)) {
            user.setPassword(newPassword);
            usersRepository.save(user);
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
