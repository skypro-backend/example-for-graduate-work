package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByAdId(Integer adId) {
        return commentRepository.findByAdId(adId).stream()
                .map(commentMapper::toDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<Comment> addComment(Integer adId, CreateOrUpdateComment commentDto, Integer authorId) {
        return adRepository.findById(adId)
                .flatMap(ad -> userRepository.findById(authorId)
                        .flatMap(author -> commentMapper.toEntity(commentDto)
                                .map(entity -> {
                                    entity.setAd(ad);
                                    entity.setAuthor(author);
                                    return commentRepository.save(entity);
                                })))
                .flatMap(commentMapper::toDto);
    }

    public Optional<Comment> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDto,  Authentication authentication) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(comment -> {
                    if (!hasAdminRole(authentication) && !comment.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this comment");
                    }
                    comment.setText(commentDto.getText());
                    return commentRepository.save(comment);
                })
                .flatMap(commentMapper::toDto);
    }

    public boolean deleteComment(Integer adId, Integer commentId,  Authentication authentication) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(comment -> {
                    if (!hasAdminRole(authentication) && !comment.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this comment");
                    }
                    commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }

    private boolean hasAdminRole(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private Integer getUserId(Authentication authentication) {
        if (authentication == null) {
            throw new AccessDeniedException("User not authenticated");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}
