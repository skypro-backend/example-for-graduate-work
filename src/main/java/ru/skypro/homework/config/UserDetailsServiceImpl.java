package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserEntityRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserEntityRepository userEntityRepository;

    public UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    /**
     * Loading user's information.
     * <br> If user not found {@code userEntityRepository.findByUsername(username)},
     * <br> making new user;
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with provided username (email) is not found!"));
        return new CustomUserDetails(user);
    }


}
