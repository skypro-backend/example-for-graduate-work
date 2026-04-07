package ru.skypro.homework.component;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.repository.AdvertisementRepository;

@Component("adSecurity")
public class AdSecurity {

    private final AdvertisementRepository adRepository;

    public AdSecurity(AdvertisementRepository adRepository) {
        this.adRepository = adRepository;
    }

    public boolean hasAccess(Long adId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AdvertisementEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));
        boolean isAuthor = ad.getAuthor().getEmail().equals(username);
        boolean isAdmin = hasRole("ROLE_ADMIN");
        return isAuthor || isAdmin;
    }

    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
