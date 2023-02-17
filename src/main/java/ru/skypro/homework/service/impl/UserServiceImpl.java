package ru.skypro.homework.service.impl;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Value("./user_photo")
  private String userPhotoDit;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserDTO getUser(Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    String nameEmail = authentication.getName();
    boolean authenticated = authentication.isAuthenticated();
    UserEntity userEntity = findEntityByEmail(nameEmail);
    return userMapper.toDTO(userEntity);
  }

  @Override
  public UserDTO updateUser(UserDTO newUserDto) {
    log.info(FormLogInfo.getInfo());

    int id = newUserDto.getId();

    UserEntity oldUser = null;

    oldUser = findById(id);
    oldUser.setEmail(newUserDto.getEmail());
    oldUser.setFirstName(newUserDto.getFirstName());
    oldUser.setLastName(newUserDto.getLastName());
    oldUser.setPhone(newUserDto.getPhone());

    try {
      oldUser.setRegDate(LocalDateTime.parse(newUserDto.getRegDate()));
    } catch (Exception e) {
      log.info("Ошибка изменения даты регистрации");
    }

    oldUser.setCity(newUserDto.getCity());
    oldUser.setImage(newUserDto.getImage());
    userRepository.save(oldUser);

    return newUserDto;
  }

  @Override
  public NewPassword setPassword(NewPassword newPassword) {
    log.info(FormLogInfo.getInfo());
    return null;
  }

  @Override
  public void updateUserImage(MultipartFile image, Authentication authentication) {
    log.info(FormLogInfo.getInfo());

    String nameEmail = authentication.getName();
    UserEntity userEntity = findEntityByEmail(nameEmail);

    Path filePath = Path.of("");

    try (InputStream is = image.getInputStream();
        OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {

      long id = userEntity.getId();
      filePath = Path.of(userPhotoDit, id + "." + getExtension(
          Objects.requireNonNull(image.getOriginalFilename())));
      Files.createDirectories(filePath.getParent());
      Files.deleteIfExists(filePath);

      bis.transferTo(bos);

      String photo = Base64.getEncoder().encodeToString(image.getBytes());
      userEntity.setPhone(photo);
      userRepository.save(userEntity);

    } catch (Exception e) {
      log.info("Ошибка сохранения файла");
    }

  }

  /**
   * найти пользователя по id
   *
   * @param id id пользователя
   * @return пользователь
   */
  private UserEntity findById(int id) {
    log.info(FormLogInfo.getInfo());
    return userRepository.findById(id).orElseThrow(ElemNotFound::new);
  }

  /**
   * найти пользователя по email - логину
   *
   * @param email email - логину пользователя
   * @return пользователь
   */
  private UserEntity findEntityByEmail(String email) {
    log.info(FormLogInfo.getInfo());
    return userRepository.findByEmail(email);
  }

  /**
   * вспомогательный медот для загрузки фотографий
   *
   * @return расширение файла
   */
  private String getExtension(String fileName) {
    log.info(FormLogInfo.getInfo());

    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }

}
