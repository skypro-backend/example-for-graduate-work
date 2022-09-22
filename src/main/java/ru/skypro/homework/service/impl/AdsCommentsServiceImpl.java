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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsCommentsServiceImpl implements AdsCommentsService {

    private final Logger logger = LoggerFactory.getLogger(AdsCommentsServiceImpl.class);
    private final AdsCommentsRepository adsCommentsRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;

    @Override
    public List<AdsCommentDto> getAdsComments(String adPk) {
        Integer adPkInt = Integer.parseInt(adPk);
        checkOnExistingAds(adPkInt);
        Ads ads = adsRepository.findAdsByPk(adPkInt);
        List<Comments> commentsList = adsCommentsRepository.findCommentsByAds(ads);
        if (commentsList.isEmpty()) {
            logger.warn("Ads comments are not exist");
            throw new NotFoundException("Comments to ads with id " + adPk + " are not found");
        }
        logger.info("Comment model list is found");
        List<AdsCommentDto> commentsDtoList = new ArrayList<>();
        for (Comments comment :
                commentsList) {
            commentsDtoList.add(commentsMapper.toCommentsDto(comment));
        }
        return commentsDtoList;
    }

    @Override
    public AdsCommentDto addAdsComments(String adPk, AdsCommentDto adsCommentDto) {
        Integer adPkInt = Integer.parseInt(adPk);
        checkOnExistingAds(adPkInt);
        checkOnExistingAuthor(adsCommentDto);

        Comments comment = commentsMapper.toComments(adsCommentDto);
        Ads ads = adsRepository.findAdsByPk(adPkInt);
        comment.setAds(ads);

        Comments savedComment = adsCommentsRepository.save(comment);
        logger.info("Comment with pk {} was saved ", savedComment.getPk());
        return commentsMapper.toCommentsDto(savedComment);
    }

    @Transactional
    @Override
    public void deleteAdsComments(String adPk, Integer id) {
        Integer adPkInt = Integer.parseInt(adPk);
        checkOnExistingAds(adPkInt);
        checkOnExistingComment(id);

        logger.info("ads comment with id = {} was deleted", id);
        adsCommentsRepository.deleteById(id);
    }

    @Override
    public AdsCommentDto getAdsComments(String adPk, Integer id) {
        Integer adPkInt = Integer.parseInt(adPk);
        checkOnExistingAds(adPkInt);
        checkOnExistingComment(id);
        Comments comments = adsCommentsRepository.findCommentsByPk(id);
        logger.info("ads comment with id = {} was found", id);
        return commentsMapper.toCommentsDto(comments);
    }

    @Override
    public AdsCommentDto updateAdsComments(String adPk, Integer id, AdsCommentDto adsCommentDto) {
        Integer adPkInt = Integer.parseInt(adPk);
        checkOnExistingAds(adPkInt);
        checkOnExistingAuthor(adsCommentDto);
        Comments commentsModel = commentsMapper.toComments(adsCommentDto);

        Ads ads = adsRepository.findAdsByPk(adPkInt);
        commentsModel.setAds(ads);
        commentsModel.setPk(id);
        adsCommentsRepository.save(commentsModel);
        logger.info("ads comment with id = {} was changed", id);
        return commentsMapper.toCommentsDto(commentsModel);
    }

    private void checkOnExistingAds(Integer adPk) {
        if (adsRepository.findById(adPk).isEmpty()) {
            logger.warn("Ads does not exist");
            throw new NotFoundException("Ad with id " + adPk + " does not exist");
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
