package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.PropertiesDTO;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;

@Service
public class AdsServiceMockImpl implements AdsService {
    @Override
    public AdsDTO getAds(int id) {
        return null;
    }

    @Override
    public AdsDTO updateAds(int id) {
        return null;
    }

    @Override
    public CommentDTO getComments(String adPk, int id) {
        return null;
    }

    @Override
    public void deleteComments(String adPk, int id) {

    }

    @Override
    public CommentDTO updateComments(String adPk, int id, CommentDTO commentDTO) {
        return null;
    }

    @Override
    public void removeAds(int id) {

    }

    @Override
    public Collection<CommentDTO> getAdsComments(Integer pk) {
        return null;
    }

    @Override
    public void addAdsComments(Integer pk) {

    }

    @Override
    public void deleteAdsComment(Integer pk, Integer id) {

    }

    @Override
    public Collection<AdsDTO> getALLAds() {
        return null;
    }

    @Override
    public AdsDTO addAds(PropertiesDTO properties, MultipartFile multipartFile) {
        return null;
    }
}
