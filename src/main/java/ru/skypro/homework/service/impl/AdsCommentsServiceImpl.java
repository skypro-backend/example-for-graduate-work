package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.dto.AdsCommentDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Comments;
import ru.skypro.homework.models.mappers.CommentsMapper;
import ru.skypro.homework.repository.AdsCommentsRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsCommentsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsCommentsServiceImpl implements AdsCommentsService {

    private final Logger logger = LoggerFactory.getLogger(AdsCommentsServiceImpl.class);

    private final AdsCommentsRepository adsCommentsRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    @Override
    public List<AdsCommentDto> getAdsComments(String ad_pk) {
        Integer ad_pk_int = Integer.parseInt(ad_pk);
        checkOnExistingAds(ad_pk_int);
        Ads ads = adsRepository.findById(ad_pk_int).orElse(new Ads());
        List<Comments> commentsList = (List<Comments>) adsCommentsRepository.findCommentsByAds(ads);
        if (commentsList.isEmpty()) {
            logger.warn("Ads comments are not exist");
            throw new NotFoundException("Comments to ads with id " + ad_pk + " are not found");
        }
        logger.info("Comment model list is found");
        List<AdsCommentDto> commentsDtoList = new ArrayList<>();
        for (Comments comment :
                commentsList) {
            commentsDtoList.add(CommentsMapper.INSTANCE.toCommentsDto(comment));
        }
        return commentsDtoList;
    }

    @Override
    public AdsCommentDto addAdsComments(String ad_pk, AdsCommentDto adsCommentDto) {
        Integer ad_pk_int = Integer.parseInt(ad_pk);
        checkOnExistingAds(ad_pk_int);
        checkOnExistingAuthor(adsCommentDto);

        Comments comment = CommentsMapper.INSTANCE.toComments(adsCommentDto);
        Ads ads = adsRepository.findById(ad_pk_int).orElse(new Ads());
        comment.setAds(ads);

        Comments savedComment = adsCommentsRepository.save(comment);
        logger.info("Comment with pk {} was saved ", savedComment.getPk());
        return CommentsMapper.INSTANCE.toCommentsDto(savedComment);
    }

    @Override
    public void deleteAdsComments(String ad_pk, Integer id) {
        Integer ad_pk_int = Integer.parseInt(ad_pk);
        checkOnExistingAds(ad_pk_int);
        checkOnExistingComment(id);

        logger.info("ads comment with id = {} was deleted", id);
        adsCommentsRepository.deleteById(id);
    }

    @Override
    public AdsCommentDto getAdsComments(String ad_pk, Integer id) {
        Integer ad_pk_int = Integer.parseInt(ad_pk);
        checkOnExistingAds(ad_pk_int);
        checkOnExistingComment(id);
        Comments comments = adsCommentsRepository.findCommentsByPk(id);
        logger.info("ads comment with id = {} was found", id);
        return CommentsMapper.INSTANCE.toCommentsDto(comments);
    }

    @Override
    public AdsCommentDto updateAdsComments(String ad_pk, Integer id, AdsCommentDto comment) {
        Integer ad_pk_int = Integer.parseInt(ad_pk);
        checkOnExistingAds(ad_pk_int);
        checkOnExistingAuthor(comment);
        Comments commentsModel = CommentsMapper.INSTANCE.toComments(comment);

        Ads ads = adsRepository.findById(ad_pk_int).orElse(new Ads());
        commentsModel.setAds(ads);
        commentsModel.setPk(id);
        adsCommentsRepository.save(commentsModel);
        logger.info("ads comment with id = {} was changed", id);
        return CommentsMapper.INSTANCE.toCommentsDto(commentsModel);
    }

    private void checkOnExistingAds(Integer ad_pk) {
        if (adsRepository.findById(ad_pk).isEmpty()) {
            logger.warn("Ads does not exist");
            throw new NotFoundException("Ad with id " + ad_pk + " does not exist");
        }

    }

    private void checkOnExistingAuthor(AdsCommentDto adsCommentDto) {
        if (userRepository.findById(adsCommentDto.getAuthor()).isEmpty()) {
            logger.warn("Author does not exist");
            throw new NotFoundException("Author with id " + adsCommentDto.getAuthor() + " does not exist");
        }

    }

    private void checkOnExistingComment(Integer id) {
        if (adsCommentsRepository.findById(id).isEmpty()) {
            logger.warn("Comment does not exist");
            throw new NotFoundException("Comment with id " + id + " does not exist");
        }
    }

}
