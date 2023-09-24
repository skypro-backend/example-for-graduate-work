package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService{

      private final UserRepository userRepository;

      @Override
      public User findUserById (Integer userId) {
            Optional <User> userFromDb = userRepository.findById (Math.toIntExact (userId));
            return userFromDb.orElse (new User ());
      }
      @Override
      public User getUsers (String email) {
            return userRepository.findByEmail (email).orElseThrow ();
      }

     @Override
      public User getUserById (Integer id) {
            return userRepository.findById(id).orElseThrow ();
      }

      @Override
      public List <User> allUsers () {
            return userRepository.findAll();
      }

      @Override
      public User addUser (User user) {
            if (user.getRole () == null) {
                  user.setRole (Role.USER);
            }
            if (userRepository.existsByEmailContains (user.getEmail ())) {
                  PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder ();
                  user.setPassword (passwordEncoder.encode (user.getPassword ()));
            }
            return userRepository.save (user);
      }
      @Override
      public boolean deleteUser (Integer userId) {
            if (userRepository.findById(userId).isPresent()) {
                  userRepository.deleteById(userId);
                  return true;
            }
            return false;
      }
}
