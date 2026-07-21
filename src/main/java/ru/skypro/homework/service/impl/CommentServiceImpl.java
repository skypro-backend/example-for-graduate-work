package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.exception.ResourceNotFoundException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(Integer adId) {

        List<ru.skypro.homework.entity.Comment> comments =
                commentRepository.findAllByAdId(adId);

        Comments result = new Comments();

        result.setCount(comments.size());

        result.setResults(
                comments.stream()
                        .map(commentMapper::toDto)
                        .toList()
        );

        return result;
    }

    @Override
    public Comment addComment(
            Integer adId,
            CreateOrUpdateComment dto
    ) {

        Ad ad = adRepository.findById(adId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Ad not found")
                );

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        ru.skypro.homework.entity.User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("User not found")
                        );

        ru.skypro.homework.entity.Comment comment =
                new ru.skypro.homework.entity.Comment();

        comment.setText(dto.getText());

        comment.setCreatedAt(
                Instant.now().toEpochMilli()
        );

        comment.setAd(ad);

        comment.setAuthor(user);

        ru.skypro.homework.entity.Comment saved =
                commentRepository.save(comment);

        return commentMapper.toDto(saved);
    }

    @Override
    public void deleteComment(
            Integer adId,
            Integer commentId
    ) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        ru.skypro.homework.entity.Comment comment =
                commentRepository.findById(commentId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Comment not found"
                                )
                        );

        if (comment.getAuthor() == null ||
                !comment.getAuthor().getEmail().equals(email)) {

            throw new AccessDeniedException(
                    "You are not the owner"
            );
        }

        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(
            Integer adId,
            Integer commentId,
            CreateOrUpdateComment dto
    ) {
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        ru.skypro.homework.entity.Comment comment =
                commentRepository.findById(commentId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Comment not found"
                                )
                        );

        if (comment.getAuthor() == null ||
                !comment.getAuthor().getEmail().equals(email)) {

            throw new AccessDeniedException(
                    "You are not the owner"
            );
        }

        comment.setText(dto.getText());

        return commentMapper.toDto(
                commentRepository.save(comment)
        );
    }
}