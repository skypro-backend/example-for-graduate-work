package ru.skypro.homework.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

public class GetAuthentication {

    public User getAuthenticationUser(String userName) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals(userName)){
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails.getUser();
        }
        throw new UsernameNotFoundException("Не авторизованный пользователь");
    }
}
