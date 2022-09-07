package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.models.dto.AdsCommentDto;
import ru.skypro.homework.repository.AdsCommentsRepository;
import ru.skypro.homework.service.AdsCommentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsCommentsServiceImpl implements AdsCommentsService {

    private final AdsCommentsRepository adsCommentsRepository;

    @Override
    public List<AdsCommentDto> getAdsComments(String ad_pk) {
        return null;
    }

    @Override
    public AdsCommentDto addAdsComments(String ad_pk, AdsCommentDto comment) {
        return null;
    }

    @Override
    public void deleteAdsComments(String ad_pk, Integer id) {

    }

    @Override
    public AdsCommentDto getAdsComments(String ad_pk, Integer id) {
        return null;
    }

    @Override
    public AdsCommentDto updateAdsComments(String ad_pk, Integer id, AdsCommentDto comment) {
        return null;
    }

}
