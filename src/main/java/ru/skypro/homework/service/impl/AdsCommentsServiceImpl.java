package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.dto.AdsCommentDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Comments;
import ru.skypro.homework.models.mappers.CommentsMapper;
import ru.skypro.homework.repository.AdsCommentsRepository;
import ru.skypro.homework.service.AdsCommentsService;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsCommentsServiceImpl implements AdsCommentsService {

    private final AdsCommentsRepository adsCommentsRepository;
    private final AdsService adsService;
    private final UserService userService;
    private final CommentsMapper commentsMapper;

    @Override
    public List<AdsCommentDto> getAdsComments(String adPk) {
        Integer adPkInt = Integer.parseInt(adPk);
        Ads ads = adsService.getAds(adPkInt);
        List<Comments> commentsList = adsCommentsRepository.findCommentsByAds(ads);
        if (commentsList.isEmpty()) {
            log.info("Ads comments are not exist");
        }
        log.info("Comment model list is found");

        return commentsList.stream()
                .map(commentsMapper::toCommentsDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsCommentDto addAdsComments(String adPk, AdsCommentDto adsCommentDto) {
        Integer adPkInt = Integer.parseInt(adPk);
        Ads ads = adsService.getAds(adPkInt);

        Comments comment = commentsMapper.toComments(adsCommentDto);
        comment.setAds(ads);
        comment.setAuthor(userService.getUser(0)); //todo взять принципиал юзера
        comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Comments savedComment = adsCommentsRepository.save(comment);
        log.info("Comment with pk {} was saved ", savedComment.getPk());

        return commentsMapper.toCommentsDto(savedComment);
    }

    @Transactional
    @Override
    public void deleteAdsComment(String adPk, Integer id) {
        getCommentsIfPresent(adPk, id);
        adsCommentsRepository.deleteById(id);
        log.info("ads comment with id = {} was deleted", id);
    }

    @Transactional
    @Override
    public AdsCommentDto getAdsComment(String adPk, Integer id) {
        Comments comments = getCommentsIfPresent(adPk, id);

        return commentsMapper.toCommentsDto(comments);
    }

    @Override
    public AdsCommentDto updateAdsComment(String adPk, Integer id, AdsCommentDto adsCommentDto) {
        Integer adPkInt = Integer.parseInt(adPk);
        Ads ads = adsService.getAds(adPkInt);
        Comments commentsModel = commentsMapper.toComments(adsCommentDto);
        commentsModel.setAuthor(userService.getUser(0)); //todo взять принципиал юзера
        commentsModel.setAds(ads);
        commentsModel.setPk(id);
        commentsModel.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        adsCommentsRepository.save(commentsModel);
        log.info("ads comment with id = {} was changed", id);

        return commentsMapper.toCommentsDto(commentsModel);
    }

    private Comments getCommentsIfPresent(String adPk, Integer id) {
        Integer adPkInt = Integer.parseInt(adPk);
        adsService.getAds(adPkInt);
        Comments comments = adsCommentsRepository.findById(id).orElseThrow(() -> {
            log.warn("Comment does not exist");
            return new NotFoundException("Comment with id " + id + " does not exist");
        });
        log.info("ads comment with id = {} was found", id);

        return comments;
    }
}
