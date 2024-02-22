package ru.skypro.homework.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFound;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.utils.Encoder;

import java.util.ArrayList;
import java.util.List;

@Service("UserDetailServiceImpl")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Аутентификация пользователя
     */
    public boolean login(String email, String password) {
        //вывести информацию
        log.info(FormLogInfo.getInfo());
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if (userEntity == null) {
            return false;
        }
        String encryptedPassword = userEntity.getPassword();
        return Encoder.passwordEncoderWithBCrypt().matches(password, encryptedPassword);
    }

    /**
     * Сетим поля ентити из запроса для сохранения в таблицу, проверяем на нуль
     */
    public boolean register(Register registerReq) {

        log.info(FormLogInfo.getInfo());
        UserEntity userEntity = userRepository.findByEmail(registerReq.getUsername()).orElse(null);
        if (userEntity != null) {
            log.info("Внимание: user уже зарегистрирован___:" + FormLogInfo.getInfo());
            return false;
        }
        userEntity = new UserEntity();
        userEntity.setEmail(registerReq.getUsername());
        userEntity.setPassword(Encoder.passwordEncoderWithBCrypt().encode(registerReq.getPassword()));
        userEntity.setFirstName(registerReq.getFirstName());
        userEntity.setLastName(registerReq.getLastName());
        userEntity.setPhone(registerReq.getPhone());
        userEntity.setRole(registerReq.getRole());

        log.info("Пользователь успешно сохранен в БД:" + FormLogInfo.getInfo());
        userRepository.save(userEntity);
        return true;
    }

    /**
     * передаем пароль и почту для настройки {@link WebSecurityConfig #daoAuthenticationProvider() }
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(UserNotFound::new);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (userEntity.getRole().equals(Role.ADMIN)) {
            log.info("userEntity.getRole().equals(Role.ADMIN");
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}