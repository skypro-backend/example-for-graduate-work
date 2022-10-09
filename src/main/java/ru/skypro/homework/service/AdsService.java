package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;

public interface AdsService {
    ResponseWrapperAds getAllAds();

    AdsDto addAds(CreateAds createAds);

    ResponseWrapperAds getAdsMe(Boolean authenticated, String authorities0Authority, Object credentials, Object details, Object principal);

    ResponseWrapperAdsComment getAdsComments(int pk);

    AdsCommentDto addAdsComment(int pk, AdsCommentDto adsCommentDto);

    AdsCommentDto deleteAdsComment(int pk, int id);

    AdsCommentDto getAdsComment(int pk, int id);

    AdsCommentDto updateAdsComment(int pk, int id, AdsCommentDto adsCommentDto);

    AdsDto removeAds(int id);

    FullAds getAds(int id);

    AdsDto updateAds(int id, AdsDto adsDto);
}
