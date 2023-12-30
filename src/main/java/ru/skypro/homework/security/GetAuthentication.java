package ru.skypro.homework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

public class GetAuthentication {
    /**
    Метод для получения аутентифицированного пользователя
    * @param userName - логин передается из объекта Authentication
    * @return объект User
    */
    public User getAuthenticationUser(String userName) throws UserNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals(userName)){
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails.getUser();
        }
        throw new UsernameNotFoundException("Не авторизованный пользователь");
    }
}
