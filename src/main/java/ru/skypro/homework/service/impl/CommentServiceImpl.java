package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentInconsistencyToAdException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto addCommentToAd(Integer adId,
                                     CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                     Authentication authentication)
            throws AdNotFoundException, UsernameNotFoundException {
        Comment comment = commentMapper.createCommentDtoToEntity(
                getAdByAdId(adId),
                createOrUpdateCommentDto,
                getUserFromAuthentication(authentication));
        commentRepository.save(comment);
        return commentMapper.entityToCommentDto(comment);
    }

    @Override
    public CommentDto updateCommentToAd(Integer adId, Integer commentId,
                                        CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                        Authentication authentication)
            throws CommentNotFoundException, AdNotFoundException, UsernameNotFoundException, CommentInconsistencyToAdException {
        if (adId.equals(getCommentByCommentId(commentId).getAd().getId())) {
            Comment comment = commentMapper.updateCommentDtoToEntity(
                    getAdByAdId(adId),
                    commentId,
                    createOrUpdateCommentDto,
                    findCommentLocalDateTime(commentId),
                    getUserFromAuthentication(authentication));
            commentRepository.save(comment);
            return commentMapper.entityToCommentDto(comment);
        } else {
            throw new CommentInconsistencyToAdException();
        }
    }

    @Override
    public void removeComment(Integer adId, Integer commentId, Authentication authentication)
            throws CommentNotFoundException, CommentInconsistencyToAdException {
        if (adId.equals(getCommentByCommentId(commentId).getAd().getId())) {
            commentRepository.delete(getCommentByCommentId(commentId));
        } else {
            throw new CommentInconsistencyToAdException();
        }
    }

    @Override
    public CommentsDto getCommentsByAd(Integer adId) {
        List<CommentDto> commentsByAd = commentRepository.findAllByAdId(adId).stream()
                .map(commentMapper::entityToCommentDto).toList();
        return commentMapper.commentDtoListToCommentsDto(commentsByAd);
    }

    protected User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    protected Ad getAdByAdId(Integer adId) {
        return adRepository.findById(adId)
                .orElseThrow(() ->
                        new AdNotFoundException(adId));
    }

    protected Comment getCommentByCommentId(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new CommentNotFoundException(commentId));
    }

    protected LocalDateTime findCommentLocalDateTime(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId))
                .getCreatedAt();
    }
}

