package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис для обработки запроса от UsersController, обращений в БД
 * и возврата ответа в UsersController
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    /**
     * Метод, который сравнивает значения текущего пароля с новым
     * @param newPassword - новый пароль
     * @return true, если текущий пароль не совпадает с новым паролем; false, если пароли одинаковые
     */
    @Override
    public boolean setPassword(NewPassword newPassword) {
        UserEntity currentUserEntity = getCurrentUserName();
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // если текущий пароль не совпадает с новым паролем, то изменить, затем сохранить новый пароль в БД и вернуть true
        return false;
    }

    /**
     * Метод, который ищет в БД пользователя по имени
     * @return данные пользователя
     */
    @Override
    public User getUser() {
        UserEntity currentUserEntity = getCurrentUserName();
        return UserMapper.INSTANCE.userEntityToUser(currentUserEntity);
    }

    /**
     * Метод, который обновляет значения пользователя
     * @param updateUser содержит новые значения пользователя
     * @return true, если значения поменялись; false, если такой пользователь не найден
     */
    @Override
    public boolean updateUser(UpdateUser updateUser) {
        //Optional<UserEntity> currentUserEntity = userRepository.findByUsername(getCurrentUserName());
        UserEntity currentUserEntity1 = getCurrentUserName();
        //UserEntity currentUserEntity2 = getUser().getEmail();
//        currentUserEntity.ifPresent((userEntity) -> UserMapper.INSTANCE.userToUserEntity(user));
        if (!currentUserEntity1.getUsername().isEmpty()) {
            currentUserEntity1.setFirstName(updateUser.getFirstName());
            currentUserEntity1.setLastName(updateUser.getLastName());
            currentUserEntity1.setPhone(updateUser.getPhone());
            userRepository.save(currentUserEntity1);
            return true;
        }
        return false;
    }

    /**
     * Метод, который меняет аватарку пользователя, и сохраняет в БД
     * @param file - значение нового файла
     * @throws IOException
     */
    @Override
    public boolean updateUserImage(MultipartFile file) throws IOException {
        UserEntity userEntity = getCurrentUserName();
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userEntity.setData(file.getBytes());
        userRepository.save(userEntity);
        return true;
    }

    /**
     * Приватный метод, который вытаскивает имя авторизованного пользователя
     * @return имя авторизованного пользователя
     */
    private UserEntity getCurrentUserName() {
        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authenticationUser.getName());
    }
}
