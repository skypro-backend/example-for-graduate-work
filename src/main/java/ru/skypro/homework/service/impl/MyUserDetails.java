package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.MyUserPrincipal;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepo;

    /*Интерфейс UserDetailsService используется
        для получения данных,
        связанных с пользователем.*/
@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private  UserRepo userRepository;

    /*процесс поиска пользователя.*/
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByFirstName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
