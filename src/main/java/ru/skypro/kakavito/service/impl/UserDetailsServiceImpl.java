package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.kakavito.model.UserPrincipal;
import ru.skypro.kakavito.dto.UserPrincipalDTO;
import ru.skypro.kakavito.exceptions.UserNotFoundException;
import ru.skypro.kakavito.mappers.UserMapper;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.UserRepo;


/**Интерфейс UserDetailsService используется
        для получения данных,
        связанных с пользователем.*/
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;


    /*процесс поиска пользователя.*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserPrincipalDTO userPrincipalDTO = userMapper.convertToUserPrincipalDTO(user);
        return new UserPrincipal(userPrincipalDTO);
    }

}
