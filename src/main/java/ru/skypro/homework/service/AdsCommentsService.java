package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.AdsCommentDto;

import java.util.List;

public interface AdsCommentsService {

    List<AdsCommentDto> getAdsComments(String adPk);

    AdsCommentDto addAdsComments(String adPk, AdsCommentDto adsCommentDto);

    void deleteAdsComment(String adPk, Integer id);

    AdsCommentDto getAdsComment(String adPk, Integer id);

    AdsCommentDto updateAdsComment(String adPk, Integer id, AdsCommentDto adsCommentDto);

}
