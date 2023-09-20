package ru.skypro.homework.service.comments.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.dto.comments.in.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.repository.comments.CommentsRepository;
import ru.skypro.homework.service.comments.CommentsService;
import ru.skypro.homework.service.users.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;

    public CommentsServiceImpl(CommentsRepository commentsRepository,
                               AdsRepository adsRepository,
                               CommentMapper commentMapper,
                               UserService userService) {
        this.commentsRepository = commentsRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public CommentsDto getComments(Integer adId) {
        Optional<Ad> adOptional = adsRepository.findByPkIs(adId);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + adId);
        } else {
            List<Comment> adList = adOptional.get().getComments();
            return commentMapper.toCommentsDto(adList);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeAds(authentication, #id))")
    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Optional<Ad> adOptional = adsRepository.findById(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Объявление с таким id не найдено: " + id);
        } else {
            Ad ad = adOptional.get();
            User author = userService.getAuthor();
            Comment comment = new Comment();
            comment.setText(createOrUpdateCommentDto.getText());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setAuthor(author);
            comment.setAd(ad);
            commentsRepository.save(comment);
            return commentMapper.toCommentDto(comment);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeComments(authentication, #adId, #commentId))")
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Optional<Comment> commentOptional = commentsRepository.findByAd_PkAndPk(adId, commentId);
        if (commentOptional.isEmpty()) {
            throw new NotFoundException("Комментарий с таким id не найден: " + commentId);
        } else {
            String text = createOrUpdateCommentDto.getText();
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentsRepository.save(comment);
            return commentMapper.toCommentDto(comment);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and @authServiceImpl.isUserAllowedToChangeComments(authentication, #adId, #commentId))")
    public void deleteComment(Integer adId, Integer commentId) {
        Optional<Comment> commentOptional = commentsRepository.findByAd_PkAndPk(adId, commentId);
        if (commentOptional.isEmpty()) {
            throw new NotFoundException("Комментарий с таким id не найден: " + commentId);
        } else {
            Comment comment = commentOptional.get();
            commentsRepository.delete(comment);
        }
    }

}
