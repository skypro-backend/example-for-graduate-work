package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import javax.transaction.Transactional;
import java.io.IOException;


/**
 * Имплеменация сервиса для работы с пользователем
 */
@Log
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

      private final UserRepository userRepository;
      private final UserMapper userMapper;
      private final PasswordEncoder passwordEncoder;
      private final MyUserDetailsService userDetailsService;
      private final SecurityCheck securityCheck;
      private final ImageService imageService;

      @Override
      public void newPassword(NewPasswordDto newPasswordDto , Authentication authentication) {
            log.info("вызван метод для создания нового пароля");
            User user = userRepository.findByEmail (SecurityContextHolder.getContext()
                                .getAuthentication().getName ()).orElseThrow();

            if (passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
                  user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
                  userRepository.save(user);
                  log.info("пароль обновлен");
                  userDetailsService.loadUserByUsername (user.getEmail ());
            }
      }
      @Override
      public UserDto findAuthUser(Authentication authentication) {
            log.info("получение информации об авторизованном пользователе");
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
            return userMapper.toUserDto (user);
      }
      @Override
      public UpdateUserDto updateUser(UpdateUserDto updateUserDto, Authentication authentication) {
            log.info("обновление авторизованного пользователя");
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
            user.setFirstName(updateUserDto.getFirstName());
            user.setLastName(updateUserDto.getLastName());
            user.setPhone(updateUserDto.getPhone());
            return userMapper.toUpdateUserDto(userRepository.save(user));
      }

      @Override
      public void updateAvatar(MultipartFile multipartFile, Authentication authentication) throws IOException {
            User userAvatar = securityCheck.checkUser(authentication);
            log.info("обновление изображения пользователя");
            userAvatar.setImage(imageService.uploadImage(multipartFile));
            userRepository.save(userAvatar);
      }

}
