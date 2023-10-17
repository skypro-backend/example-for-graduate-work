package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.MyUserDetailsService;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Имплеменация сервиса для работы с пользователем
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

      private final UserRepository userRepository;
      private final UserMapper userMapper;
      private final PasswordEncoder passwordEncoder;
      private final MyUserDetailsService userDetailsServicer;

      @Override
      public void newPassword (String newPassword , String currentPassword) {
            User user = userRepository.findByEmail (SecurityContextHolder.getContext ()
                      .getAuthentication ().getName ()).orElseThrow ();
            if (passwordEncoder.matches (currentPassword , user.getPassword ())) {
                  user.setPassword (passwordEncoder.encode (newPassword));
                  userRepository.save (user);
                  userDetailsServicer.loadUserByUsername (user.getEmail ());
            }
      }
      @Override
      public Optional <User> findAuthUser () {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            return userRepository.findByEmail(currentPrincipalName);
      }
      @Override
      public UserDto updateUserDto (UserDto newUserDto) {
            Optional<User> currentUser = findAuthUser();
            User newCurrentUser = new User();
            if (currentUser.isPresent()) {
                  newCurrentUser = currentUser.get();
                  newCurrentUser.setFirstName(newUserDto.getFirstName());
                  newCurrentUser.setLastName(newUserDto.getLastName());
                  newCurrentUser.setPhone(newUserDto.getPhone());
                  userRepository.save(newCurrentUser);
            }
            return userMapper.toUserDto(newCurrentUser);
      }

      public String updateUserImage(MultipartFile image, String email) {
            User user = userRepository.findByEmail(email).orElseThrow();
            return "/users/image/" + userRepository.save(user).getImage().getId();
      }

}
