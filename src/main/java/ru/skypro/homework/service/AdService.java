package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {
    Ads getAllAds();
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, String username);
    ExtendedAd getAd(int id);
    void removeAd(int id);
    ExtendedAd updateAds(int id, CreateOrUpdateAd createOrUpdateAd);
    Ad updateAdsBasic(int id, CreateOrUpdateAd createOrUpdateAd);
    Ads getAdsMe(String username);
    byte[] updateImage(int id, MultipartFile image);

    // Методы для комментариев (отзывов)
    Comments getComments(int adId);
    Comment addComment(int adId, CreateOrUpdateComment createOrUpdateComment, String username);
    void deleteComment(int adId, int commentId);
    Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment);
    String getCommentAuthorUsername(int commentId);
}