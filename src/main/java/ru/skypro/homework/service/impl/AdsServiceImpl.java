package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAdds;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {
    public List<Ads> getAds() {
        List<Ads> ads = new ArrayList<>();
        return ads;
    }

    public void addAds(CreateAds properties, MultipartFile image) {
    }

    @Override
    public List<Comment> getComments(int id) {
        return null;
    }

    @Override
    public Comment addComments(int id, Comment comment) {
        return null;
    }

    @Override
    public FullAdds getFullAd(int id) {
        return null;
    }

    @Override
    public int removeAds(int id) {
        return 0;
    }

    @Override
    public int updateAds(int id, CreateAds createAds) {
        return 0;
    }


    @Override
    public Optional<Comment> deleteComments(int adId, int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Comment> updateComments(int adId, int commentId) {
        return Optional.empty();
    }

    @Override
    public List<Ads> getAdsMe() {
        return null;
    }
    @Override
    public int updateAdsImage(int id, MultipartFile image) {
        return 0;
    }
}