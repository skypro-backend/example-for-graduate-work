package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

/**
 * Сервис хранящий логику для управления данными пользователей.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthServiceImpl authService;

    public UserServiceImpl(UserRepository userRepository, AuthServiceImpl authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    /**
     * Метод обновляет пароль текущего, авторизованного пользователя.
     * <p>Метод получает объект newPass, который содержит два поля со старым и новым паролями.</p>
     * Далее метод ищет пользователя с соответствующим паролем в репозитории и сохраняет его
     * в переменную userEntity. Далее, используя сеттер, в переменную, содержащую пользователя,
     * сохраняется новый пароль. Переменная с новым, измененным паролем сохраняется в БД.
     * Последней строкой, меняем пароль в объекте {@link AuthServiceImpl#manager}
     * @param newPass новый пароль
     */
    @Override
    public void setNewPassword(NewPassword newPass) {
        String oldPassword = newPass.getCurrentPassword();
        String newPassword = newPass.getNewPassword();
        UserEntity userEntity = userRepository.findUserEntityByPassword(oldPassword);
        userEntity.setPassword(newPassword);
        userRepository.save(userEntity);
        authService.getUserDetailsManager().changePassword(newPass.getCurrentPassword(), newPass.getNewPassword());
    }

    @Override
    public UserEntity getUserInfo(String userName) { //userName надо получить из authService
        String login = authService.getUserDetailsManager().loadUserByUsername(userName).getUsername();
        userRepository.findUserEntityByUsername(login);
        return null;
    }


}
