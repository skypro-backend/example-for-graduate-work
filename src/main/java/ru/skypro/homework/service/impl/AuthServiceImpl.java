package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    /**
     * создаем пустую сущность userEntity
     */
    private UserEntity userEntity;
    private Login authorizationData;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        //Если пользователь уже зарегистрирован, то получить его данные из Register не получится.
        //Поэтому создается объект authorizationData и заполняется логином и паролем.
        //По логину и паролю в UserService.getUserInfo() ищутся все данные авторизованного пользователя.
        this.authorizationData = new Login(userName, password);
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());

        //устанавливаем поля для сущности userEntity, которые берем из регистрационных данных
        this.userEntity = UserMapper.mapToUserEntity(register);

        //сохраняем нового пользователя в БД
        userRepository.save(userEntity);

        return true;
    }

    public UserDetailsManager getUserDetailsManager(){
        return manager;
    }
    public UserEntity getUserEntity(){
        return userEntity;
    }
    public Login getLogin(){
        return authorizationData;
    }

}
