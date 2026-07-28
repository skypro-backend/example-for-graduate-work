package ru.skypro.homework.service.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    public User getCurrentUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    public boolean isAdmin(Authentication authentication) {
        return getCurrentUser(authentication).getRole() == Role.ADMIN;
    }

    public void checkOwnership(User owner, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        if (!isAdmin && !owner.getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Недостаточно прав для выполнения операции");
        }
    }
}
