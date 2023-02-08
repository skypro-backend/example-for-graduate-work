package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.*;

import java.util.Collection;

public interface AdsService {


    Collection<AdsDto> getAllAds(String title);

    AdsDto save(CreateAdsDto ads, String email, MultipartFile photo);
    Collection<CommentDto> getAdsComments(Integer adsId);
    CommentDto addComment(Integer adsId, CommentDto adsComment, Authentication authentication);
    CommentDto getAdsComment(Integer adsId, Integer commentId);
    void deleteComment(Integer adsId, Integer commentId, Authentication authentication);
    CommentDto updateAdsComment(Integer adsId, Integer commentId, CommentDto comment, Authentication authentication);
    void removeAds(Integer adsId, Authentication authentication);
    FullAdsDto getFullAds(Integer adsId);
    AdsDto updateAds(Integer id, CreateAdsDto updatedAds, Authentication authentication);
    Collection<AdsDto> getAdsByUser(String email);
}
