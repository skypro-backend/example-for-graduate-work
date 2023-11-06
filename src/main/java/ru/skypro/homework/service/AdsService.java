package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAdds;

import java.util.List;
import java.util.Optional;

public interface AdsService {
    public List<Ads> getAds();
    public void addAds(CreateAds properties, MultipartFile image);

    List<Comment> getComments(int id);

    Comment addComments(int id, Comment comment);

    FullAdds getFullAd(int id);

    int removeAds(int id);

    int updateAds(int id, CreateAds createAds);

    //Comment getComments(int adId, int id);

    Optional<Comment> deleteComments(int adId, int id);

    Optional<Comment> updateComments(int adId, int commentId);

    List<Ads> getAdsMe();

    int updateAdsImage(int id, MultipartFile image);
}