package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.mapper.UserMapper;
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
     *
     * @param newPass объект NewPassword, содержащий старый и новый пароли.
     */
    @Override
    public void setPassword(NewPassword newPass) {
        String oldPassword = newPass.getCurrentPassword();
        String newPassword = newPass.getNewPassword();
        UserEntity userEntity = userRepository.findUserEntityByPassword(oldPassword);
        userEntity.setPassword(newPassword);
        userRepository.save(userEntity);
        //меняем пароль авторизованного пользователя в AuthService
        authService.getUserEntity().setPassword(newPassword);
    }

    /**
     * Метод возвращает информацию о текущем, авторизованном пользователе.
     * Метод находит в {@link AuthServiceImpl} текущие логин и пароль, и присваивает их в переменные.
     * Далее метод находит в БД, используя {@link UserRepository}, пользователя с соответствующими
     * данными и возвращает его.
     *
     * @return объект userEntity
     */
    @Override
    public UserEntity getUser() {
        String userName = authService.getLogin().getUsername();
        return userRepository.findUserEntityByUserName(userName);
    }

    /**
     * Метод изменяет данные пользователя, а именно имя, фамилию и номер телефона.
     * <p>В начале метод получает из {@link AuthServiceImpl} логин авторизованного пользователя
     * и записывает его в переменную.</p>
     * <p>По логину находит данные пользователя в БД и кладет их в сущность user.
     * Сущность user заполняется измененными данными из парамера updateUser.</p>
     * <p>В итоге измененный объект user сохраняется в БД, и он же возвращается из метода.</p>
     *
     * @param updateUser объект содержащий поля с именем, фамилией и номером телефона.
     * @return объект user
     */
    @Override
    public UserEntity updateUser(UpdateUser updateUser) {
        //Получаем логин авторизованного пользователя из БД
        String userName = authService.getLogin().getUsername();
        //Находим данные авторизованного пользователя
        UserEntity user = userRepository.findUserEntityByUserName(userName);
        //Меняем данные пользователя на данные из DTO updateUser
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        //сохраняем измененные данные в БД
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity updateUser(UpdateUser updateUser, Authentication authentication) {
        String login = authentication.getName();
        UserEntity user = userRepository.findUserEntityByUserName(login);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return user;
    }

}
