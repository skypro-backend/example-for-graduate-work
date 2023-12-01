package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.exception.PasswordIsNotMatchException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Сервис хранящий логику для управления данными пользователей.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;

    @Value("${path.to.avatars.folder}")
    private String avatarDir;


    public UserServiceImpl(UserRepository userRepository, AuthServiceImpl authService, ImageServiceImpl imageService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authService = authService;
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
    public UserEntity updateUser(UpdateUser updateUser, Authentication authentication) {
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

    @Override
    public UserEntity checkUserByUsername(String username) {
        UserEntity user = userRepository.findUserEntityByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("User not found"); // todo придумать сообщение
        }
        return user;
    }

    //@Override ругается method does not override or implement a method from a supertype
    public boolean updateUserImage(MultipartFile image) throws IOException {

        UserEntity user = getUser();

        Path filePath = Path.of(avatarDir, user.getId() + "."
                + imageService.getExtension(image.getOriginalFilename()));

        imageService.saveFileOnDisk(image, filePath);
        imageService.updateUserImage(user, image, filePath);

        user.setImage("/" + avatarDir + "/" + user.getId());
        userRepository.save(user);

        return true;
    }

//    @Override
//    public UserEntity updateUser(UpdateUser updateUser, Authentication authentication) {
//        String login = userDetails.getUsername();
////        String login = inMemoryUserDetailsManager.updateUser();
//
//        UserEntity user = userRepository.findUserEntityByUserName(login);
//        user.setFirstName(updateUser.getFirstName());
//        user.setLastName(updateUser.getLastName());
//        user.setPhone(updateUser.getPhone());
//        userRepository.save(user);
//        return user;
//    }


}
