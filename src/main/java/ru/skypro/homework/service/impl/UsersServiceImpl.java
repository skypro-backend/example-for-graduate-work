package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.util.UserAuthentication;

import java.io.IOException;

/**
 * Сервис для обработки запроса от UsersController, обращений в БД
 * и возврата ответа в UsersController
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final ImageService imageService;

    private final UserMapper userMapper;

    private final UserAuthentication userAuthentication;


    /**
     * Метод, который сравнивает значения текущего пароля с новым
     * @param newPassword - новый пароль
     * @return true, если текущий пароль не совпадает с новым паролем; false, если пароли одинаковые
     */
    @Override
    public boolean setPassword(NewPassword newPassword) { // TODO: 14.10.2023 требуется дороботка
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // если текущий пароль не совпадает с новым паролем, то изменить, затем сохранить новый пароль в БД и вернуть true
        return false;
    }

    /**
     * Метод, который ищет в БД пользователя по логину
     * @return данные пользователя
     */
    @Override
    public User getUser() {
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();
        return userMapper.userEntityToUser(currentUserEntity);
    }

    /**
     * Метод, который обновляет значения пользователя
     * @param updateUser содержит новые значения пользователя
     * @return true, если значения поменялись; false, если такой пользователь не найден
     */
    @Override
    public boolean updateUser(UpdateUser updateUser) {
        UserEntity currentUserEntity = userAuthentication.getCurrentUserName();

        if (!currentUserEntity.getUsername().isEmpty()) {
            currentUserEntity.setFirstName(updateUser.getFirstName());
            currentUserEntity.setLastName(updateUser.getLastName());
            currentUserEntity.setPhone(updateUser.getPhone());
            userRepository.save(currentUserEntity);
            return true;
        }
        return false;
    }

    /**
     * Метод, который меняет аватарку пользователя, и сохраняет в БД
     * @param file - значение нового файла
     * @return true, если аватарка поменялась, и сохранилась в БД
     */
    @Override
    public boolean updateUserImage(MultipartFile file) {
        try {
            imageService.uploadUserImage(file);
            return true;
        } catch (IOException e) {
            log.error("Image nou uploaded");
            return false;
        }
    }

}
