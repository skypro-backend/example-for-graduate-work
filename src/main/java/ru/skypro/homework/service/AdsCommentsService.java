package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.AdsCommentDto;

import java.util.List;

public interface AdsCommentsService {

    List<AdsCommentDto> getAdsComments(String ad_pk);

    AdsCommentDto addAdsComments(String ad_pk, AdsCommentDto comment);

    void deleteAdsComments(String ad_pk, Integer id);

    AdsCommentDto getAdsComments(String ad_pk, Integer id);

    AdsCommentDto updateAdsComments(String ad_pk, Integer id, AdsCommentDto comment);

}
