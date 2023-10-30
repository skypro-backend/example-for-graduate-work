package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService  {

      @Autowired
      private final UserRepository userRepository;

      // Метод, который принимает имя пользователя в качестве аргумента, представленного страницей входа в систему.
      // На основе имени пользователя мы извлекаем данные пользователя из базы данных.
      @Override
      @Transactional
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email).orElseThrow();
            return new MyUserDetails(user);
      }
}


