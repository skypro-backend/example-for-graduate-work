package ru.skypro.homework.component;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.repository.AdvertisementRepository;

/**
 * Компонент для проверки доступа к объявлениям.
 * Позволяет определить, имеет ли текущий пользователь права на выполнение операций с объявлением,
 * например, редактирование или удаление, основываясь на том, является ли он автором объявления или администратором.
 */
@Component("adSecurity")
@RequiredArgsConstructor
public class AdSecurity {

    private final AdvertisementRepository adRepository;

    /**
     * Проверяет, имеет ли текущий пользователь доступ к объявлению по его ID.
     * Пользователь имеет доступ, если он автор объявления или обладает ролью администратора.
     *
     * @param adId Идентификатор объявления, к которому проверяется доступ.
     * @return true, если пользователь имеет доступ, иначе false.
     * @throws ResponseStatusException если объявление с указанным ID не найдено (HTTP статус 404).
     */
    public boolean hasAccess(Integer adId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AdvertisementEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));
        boolean isAuthor = ad.getAuthor().getEmail().equals(username);
        boolean isAdmin = hasRole("ROLE_ADMIN");
        return isAuthor || isAdmin;
    }
    /**
     * Проверяет, обладает ли текущий пользователь указанной ролью.
     *
     * @param role Имя роли, например, "ROLE_ADMIN".
     * @return true, если пользователь имеет указанную роль, иначе false.
     */
    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
