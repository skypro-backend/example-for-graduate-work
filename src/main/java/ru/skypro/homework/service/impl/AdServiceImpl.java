package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final CommentMapper commentMapper;
    private final ImageService imageService;

    @Override
    public Ads getAllAds() {
        List<Ad> list = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(list.size());
        ads.setResults(list);
        return ads;
    }

    @Override
    @SneakyThrows
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image, String username) {
        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Мастер не найден"));

        AdEntity entity = new AdEntity();
        adMapper.updateAdFromDto(properties, entity);
        entity.setAuthor(author);

        String fileName = imageService.uploadImage(image);
        entity.setImage("/ads/image/" + fileName);

        adRepository.save(entity);
        return adMapper.toDto(entity);
    }

    @Override
    public ExtendedAd getAd(int id) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));
        return adMapper.toExtendedDto(entity);
    }

    @Override
    public void removeAd(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public ExtendedAd updateAds(int id, CreateOrUpdateAd createOrUpdateAd) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));
        adMapper.updateAdFromDto(createOrUpdateAd, entity);
        adRepository.save(entity);
        return adMapper.toExtendedDto(entity);
    }

    @Override
    public Ad updateAdsBasic(int id, CreateOrUpdateAd createOrUpdateAd) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));
        adMapper.updateAdFromDto(createOrUpdateAd, entity);
        adRepository.save(entity);
        return adMapper.toDto(entity);
    }

    @Override
    public Ads getAdsMe(String username) {
        List<Ad> list = adRepository.findAllByAuthorEmail(username).stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
        Ads ads = new Ads();
        ads.setCount(list.size());
        ads.setResults(list);
        return ads;
    }

    @Override
    @SneakyThrows
    public byte[] updateImage(int id, MultipartFile image) {
        AdEntity entity = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));

        String fileName = imageService.uploadImage(image);
        entity.setImage("/ads/image/" + fileName);
        adRepository.save(entity);

        return image.getBytes();
    }

    @Override
    public Comments getComments(int adId) {
        List<Comment> list = commentRepository.findAllByAdId(adId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        Comments comments = new Comments();
        comments.setCount(list.size());
        comments.setResults(list);
        return comments;
    }

    @Override
    public Comment addComment(int adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));

        CommentEntity entity = new CommentEntity();
        commentMapper.updateCommentFromDto(createOrUpdateComment, entity);
        entity.setAuthor(author);
        entity.setAd(ad);
        entity.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(entity);
        return commentMapper.toDto(entity);
    }

    @Override
    public void deleteComment(int adId, int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Отзыв не найден"));
        commentMapper.updateCommentFromDto(createOrUpdateComment, entity);
        commentRepository.save(entity);
        return commentMapper.toDto(entity);
    }

    @Override
    public String getCommentAuthorUsername(int commentId) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Отзыв не найден"));
        return entity.getAuthor().getEmail();
    }
}