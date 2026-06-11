package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.AccessChecker;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final AccessChecker accessChecker;

    @Override
    @Transactional(readOnly = true)
    public CommentsDto getComments(Integer adId) {
        findAd(adId);
        return commentMapper.toCommentsDto(
                commentRepository.findAllByAdIdWithAuthor(adId.longValue()));
    }

    @Override
    @Transactional
    public CommentDto addComment(String email, Integer adId, CreateOrUpdateCommentDto createComment) {
        Ad ad = findAd(adId);
        User author = userService.getUserByEmail(email);

        Comment comment = commentMapper.toEntity(createComment);
        comment.setAd(ad);
        comment.setAuthor(author);
        comment = commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentDto updateComment(
            String email,
            Integer adId,
            Integer commentId,
            CreateOrUpdateCommentDto updateComment
    ) {
        Comment comment = findComment(adId, commentId);
        User currentUser = userService.getUserByEmail(email);
        accessChecker.checkCommentOwnerOrAdmin(comment, currentUser);

        commentMapper.updateEntity(updateComment, comment);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(String email, Integer adId, Integer commentId) {
        Comment comment = findComment(adId, commentId);
        User currentUser = userService.getUserByEmail(email);
        accessChecker.checkCommentOwnerOrAdmin(comment, currentUser);
        commentRepository.delete(comment);
    }

    private Ad findAd(Integer adId) {
        return adRepository.findById(adId.longValue())
                .orElseThrow(NotFoundException::new);
    }

    private Comment findComment(Integer adId, Integer commentId) {
        return commentRepository.findByIdAndAdIdWithAuthor(commentId.longValue(), adId.longValue())
                .orElseThrow(NotFoundException::new);
    }
}
