package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.model.repository.AdsRepository;
import ru.skypro.homework.model.repository.CommentRepository;
import ru.skypro.homework.model.repository.UserRepository;
@Service
public class AccessService {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    public AccessService(CommentRepository commentRepository, AdsRepository adsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Проверить доступ к комментариям
     *
     * @param adsId          id объявления
     * @param commentId          id комментария
     * @param authentication сущность с данными по авторизированному пользователю
     */
    void checkCommentAccess(Integer adsId, Integer commentId, Authentication authentication) {
        int userIdFromComments = commentRepository.getUserProfileId(adsId, commentId);
        int userIdFromUserProfiles = userRepository.getUserProfileId(authentication.getName());
        boolean isNotEqualsId = userIdFromComments != userIdFromUserProfiles;
        checkAccess(isNotEqualsId, authentication);
    }

    /**
     * Проверить доступ к объявлениям
     *
     * @param adsId          id объявления
     * @param authentication сущность с данными по авторизированному пользователю
     */
    void checkAdsAccess(Integer adsId, Authentication authentication) {

        int userIdFromAds = adsRepository.getUserProfileId(adsId);
        int userIdFromUserProfiles = userRepository.getUserProfileId(authentication.getName());
        boolean isNotEqualsId = userIdFromAds != userIdFromUserProfiles;
        checkAccess(isNotEqualsId, authentication);
    }

    /**
     * Проверить доступ к данным в роли ADMIN
     *
     * @param isNotEqualsId  сравнение ID пользователя
     * @param authentication сущность с данными по авторизированному пользователю
     * @throws AccessDeniedException если пользователь не имеет права доступа к данным
     */
    private void checkAccess(boolean isNotEqualsId, Authentication authentication) {

        boolean noAdminRoots = authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isNotEqualsId && noAdminRoots) {
            throw new AccessDeniedException();
        }
    }

}
