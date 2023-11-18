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
     * в переменную userEntity.
     * <p>Далее, используя сеттер, в переменную, содержащую пользователя,
     * сохраняется новый пароль. Переменная (объект userEntity) с новым, измененным паролем
     * сохраняется в БД.</p>
     * <p>Так же нужно изменить данные авторизованного пользователя,
     * используя метод: {@link AuthServiceImpl#getUserDetailsManager()}</p>
     * <p>Последней строкой, меняем пароль в объекте {@link AuthServiceImpl#userEntity},
     * который является связью для {@link AuthServiceImpl} и БД</p>
     * @param newPass объект NewPassword, содержащий старый и новый пароли.
     */
    @Override
    public void setNewPassword(NewPassword newPass) {
        String oldPassword = newPass.getCurrentPassword();
        String newPassword = newPass.getNewPassword();
        UserEntity userEntity = userRepository.findUserEntityByPassword(oldPassword);
        userEntity.setPassword(newPassword);
        userRepository.save(userEntity);
        //меняем данные авторизованного пользователя в AuthService
//        authService.getUserDetailsManager().changePassword(newPass.getCurrentPassword(), newPass.getNewPassword());
        authService.getUserEntity().setPassword(newPassword);
    }

    /**
     * Метод возвращает информацию о текущем, авторизованном пользователе.
     * Метод находит в {@link AuthServiceImpl} текущие логин и пароль, и присваивает их в переменные.
     * Далее метод находит в БД, используя {@link UserRepository}, пользователя с соответствующими
     * данными и возвращает его.
     * @return объект userEntity
     */
    @Override
    public UserEntity getUserInfo() {

        String userName = authService.getLogin().getUsername();
//        String login = authService.getUserDetailsManager().loadUserByUsername(userName).getUsername();
//        String password = authService.getUserDetailsManager().loadUserByUsername(userName).getPassword();
//        return userRepository.findUserEntityByUserNameAndPassword(userName, password);
        return userRepository.findUserEntityByUserName(userName);
    }


}
