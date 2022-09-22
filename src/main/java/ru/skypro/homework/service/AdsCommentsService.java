package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.AdsCommentDto;

import java.util.List;

public interface AdsCommentsService {

    List<AdsCommentDto> getAdsComments(String adPk);

    AdsCommentDto addAdsComments(String adPk, AdsCommentDto adsCommentDto);

    void deleteAdsComments(String adPk, Integer id);

    AdsCommentDto getAdsComments(String adPk, Integer id);

    AdsCommentDto updateAdsComments(String adPk, Integer id, AdsCommentDto adsCommentDto);

}
