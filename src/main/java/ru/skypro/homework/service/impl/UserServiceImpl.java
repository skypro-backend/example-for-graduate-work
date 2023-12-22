package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ImageService imageService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
    }

    //Метод извлекает текущего аутентифицированного пользователя.
    @Override
    public UserDTO getCurrentUser() {
        log.info("Current user received");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  // Получение информации об аутентификации из контекста безопасности
        User user = userRepository.findByEmail(authentication.getName());                          // Поиск пользователя в репозитории по его email (имени пользователя)
        return UserMapper.INSTANCE.toUserDTO(user);                                                // Преобразование объекта User в объект UserDTO с использованием маппера
    }

    //Метод изменение данных у пользователя
    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        log.info("Update user completed");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  // Получение информации об аутентификации из контекста безопасности
        User user = userRepository.findByEmail(authentication.getName());
        UserMapper.INSTANCE.updateUserDTOToUser(updateUserDTO, user);
        return UserMapper.INSTANCE.toUserDTO(userRepository.save(user));
    }

    //  Метод изменения пароля у пользователя
    @Override
    public Void setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("Password change completed");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // Получение информации об аутентификации из контекста безопасности
        User user = userRepository.findByEmail(authentication.getName());                         // Поиск пользователя в репозитории по email (имени пользователя)
        if (user == null) {                                                                        // Проверка, что пользователь существует
            throw new UserNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {   // Проверка на совпадение паролей
            throw new InvalidPasswordException("Passwords cannot be the same");
        }
        String hashedPassword = passwordEncoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(passwordEncoder.encode(newPasswordDTO.getCurrentPassword()));           //Хэширует пароль и присваивает его пользователю
        userRepository.save(user);                                                              //Сохраняет пользователя
        return null;
    }

    // Метод обновления  аватарки у пользователя
    @Override
    @Transactional
    public void updateUserImage(MultipartFile image, String userName) {
        log.info("Avatar update compleated");
        User user = userRepository.findByEmail(userName);           // Получение пользователя по его имени (email)
        if (user.getImage() == null) {                                 // Проверка, имеет ли пользователь изображение профиля
            user.setImage(imageService.addImage(image));              // Если у пользователя нет изображения, добавляем новое изображение и сохраняем пользователя
            userRepository.save(user);
            return;
        }
        Integer imageId = user.getImage().getId();                    // Получение идентификатора текущего изображения пользователя
        user.setImage(imageService.addImage(image));                  // Добавление нового изображения
        imageService.deleteImage(imageId);                            //удаление старого изображения
        userRepository.save(user);                                     // Сохранение пользователя после обновления изображения
    }
}
