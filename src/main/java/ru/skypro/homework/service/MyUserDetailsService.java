package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
      private final UserRepository userRepository;
      private final UserMapper userMapper;

      // Метод находит пользователя по email и возвращает его данные: имя пользователя и пароль
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           return (UserDetails) userRepository.findByEmail(username).orElseThrow(()->
                    new UsernameNotFoundException("User not present"));
      }


}
