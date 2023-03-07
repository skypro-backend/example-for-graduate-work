package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;

@Service
public class SecurityService {
    AdsRepository adsRepository;

    public SecurityService(AdsRepository adsRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    /** Проверка пользователя на авторство */
    public boolean checkAuthor(int id, UserEntity user) {
        return id == user.getId();
    }
    /** Проверка автора объявления на электронную почту */
    public boolean checkAuthor(int id, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return checkAuthor(id, user);
    }
    /** Проверка авторства объявления по Authentication */
    public boolean checkAuthorEmailAndAdsId(int id, Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
        return user.getId()==adEntity.getAuthor().getId();
    }
    /** Проверка пользователя на электронную почту */
    public boolean isAuthorAuthenticated(String email, Authentication authentication) {
        return authentication.getName().equals(email) && authentication.isAuthenticated();
    }

    public boolean isAuthorAuthenticated(int id, Authentication authentication) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }

    public boolean isAuthorAuthenticated(UserEntity user, Authentication authentication) {
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }
    /** Проверка пользователя на роль администратора */
    public boolean isAdmin(UserEntity user) {
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean isAdmin(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }

    public boolean isAdmin(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.isAuthenticated() &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    /** Проверка законности доступа к методам комментариям */
    public boolean isCommentUpdateAvailable(Authentication authentication, CommentEntity commentEntity,
                                            CommentDTO commentDTO) {
        if (!authentication.isAuthenticated()) {
            return false;
        }

        if (isAdmin(authentication)) {
            return true;
        }
        if (checkAuthor(commentEntity.getAuthor().getId(), authentication.getName()) &&
                commentEntity.getAuthor().getId() == commentDTO.getAuthor()) {
            return true;
        }
        return false;
    }

    /** Проверка законности доступа к методам объявлений */
    public boolean isAdsUpdateAvailable(Authentication authentication, AdEntity adEntity) {
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (isAdmin(authentication)) {
            return true;
        }
        if (checkAuthor(adEntity.getAuthor().getId(), authentication.getName())) {
            return true;
        }
        return false;
    }
}
