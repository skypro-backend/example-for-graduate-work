package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.exception.PasswordIsNotMatchException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.AvatarEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

/**
 * Сервис хранящий логику для управления данными пользователей.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;

    @Value("${path.to.avatars.folder}")
    private String avatarDir;


    public UserServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository, ImageServiceImpl imageService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
        this.imageService = imageService;
        this.encoder = encoder;
    }

    /**
     * Метод обновляет пароль текущего, авторизованного пользователя.
     * <p>Метод получает объект newPass, который содержит два поля со старым и новым паролями.
     * А так же объект {@link Authentication} из которого можно получить логин
     * авторизованного пользователя.</p>
     * Далее метод ищет пользователя с соответствующим логином в репозитории и сохраняет его
     * в переменную userEntity. Логин получаем из объекта {@link Authentication}.
     * <p>Далее, метод делает проверку на совпадение старого пароля, введенного пользователем,
     * и пароля сохраненного в БД. Если пароли совпали, от используя сеттер, в переменную, содержащую пользователя,
     * сохраняется новый пароль. Переменная (объект userEntity) с новым, измененным паролем
     * сохраняется в БД.</p>
     *
     * @param newPass        объект {@link NewPassword}, содержащий старый и новый пароли.
     * @param authentication содержит логин авторизованного пользователя.
     */
    @Override
    public void setPassword(NewPassword newPass, Authentication authentication) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        //получаем в переменную старый пароль
        String oldPassword = newPass.getCurrentPassword();
        //получаем в переменную новый пароль и кодируем его
        String encodeNewPassword = encoder.encode(newPass.getNewPassword());
        //Находим в БД сущность авторизованного пользователя используя логин из authentication
        //проверка на null не нужна, т.к. тот факт, что пользователь авторизовался,
        //говорит о том, что он есть в БД
        UserEntity userEntity = userRepository.findUserEntityByUserName(authentication.getName());
        //проверяем совпадают ли старый пароль, введенный пользователем, и пароль сохраненный в БД
        if (!encoder.matches(oldPassword, userEntity.getPassword())) {
            throw new PasswordIsNotMatchException("Пароли не совпадают");
        } else {
            //пароли совпадают, а значит устанавливаем новый пароль в соответствующее поле сущности
            userEntity.setPassword(encodeNewPassword);
        }
        //сохраняем сущность в БД
        userRepository.save(userEntity);
    }

    /**
     * Метод возвращает информацию о текущем, авторизованном пользователе.
     * Метод, используя объект {@link Authentication}, находит в БД {@link UserRepository},
     * пользователя с соответствующими данными и возвращает его.
     *
     * @return объект userEntity
     */
    @Override
    public UserEntity getUser(Authentication authentication) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        return userRepository.findUserEntityByUserName(authentication.getName());
    }

    /**
     * Метод изменяет данные пользователя, а именно имя, фамилию и номер телефона.
     * <p>В начале метод получает из {@link Authentication} логин авторизованного пользователя
     * и записывает его в переменную.</p>
     * <p>По логину находит данные пользователя в БД и кладет их в сущность user.
     * Сущность user заполняется измененными данными из парамера updateUser.</p>
     * <p>В итоге измененный объект user сохраняется в БД, и он же возвращается из метода.</p>
     *
     * @param updateUser     объект содержащий поля с именем, фамилией и номером телефона.
     * @param authentication
     * @return объект user
     */
    @Override
    public UserEntity updateUser(UpdateUser updateUser, Authentication authentication) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        //Получаем логин авторизованного пользователя из БД
        String userName = authentication.getName();
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

    /**
     * Метод возвращает объект {@link UserEntity} из базы данных. Поиск выполняется по логину,
     * подаваемому в метод в качестве параметра.
     *
     * @param username
     * @return {@link UserEntity}
     */
    @Override
    public UserEntity checkUserByUsername(String username) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        UserEntity user = userRepository.findUserEntityByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("Пользователя с таким логином в базе данных нет");
        }
        return user;
    }

    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        //достаем пользователя из БД
        UserEntity user = userRepository.findUserEntityByUserName(authentication.getName());
        //мапим MultipartFile в сущность avatarEntity
        AvatarEntity avatar = imageService.mapMuptipartFileToAvatar(image);
        //сохраняем аватар в БД, юзеру устанавливаем новый аватар и сохраняем в БД
        avatarRepository.save(avatar);
        user.setAvatar(avatar);

        userRepository.save(user);

    }
}
