package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.authentication.ExtendedLoginViaDB;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.NoAccessToUserException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapping.UserMapper;

@Service
public class CustomUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CustomUserDetailService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);
        ExtendedLoginViaDB userEntityDTO = userMapper.extendedLoginViaDB(user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userEntityDTO);
    }
}
