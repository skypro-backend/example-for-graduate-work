package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constants.ErrorMessages;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

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

        adRepository.findById(adId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorMessages.AD_NOT_FOUND
                        )
                );

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
                        () -> new ResourceNotFoundException(
                                ErrorMessages.AD_NOT_FOUND
                        )
                );

        User user =
                userRepository.findByEmail(getCurrentUserEmail())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        ErrorMessages.USER_NOT_FOUND
                                )
                        );

        ru.skypro.homework.entity.Comment comment =
                commentMapper.toEntity(dto);

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

        ru.skypro.homework.entity.Comment comment =
                getCommentById(commentId);

        checkOwner(comment);

        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(
            Integer adId,
            Integer commentId,
            CreateOrUpdateComment dto
    ) {

        ru.skypro.homework.entity.Comment comment =
                getCommentById(commentId);

        checkOwner(comment);

        commentMapper.updateCommentFromDto(
                dto,
                comment
        );

        return commentMapper.toDto(
                commentRepository.save(comment)
        );
    }

    private ru.skypro.homework.entity.Comment getCommentById(
            Integer commentId
    ) {

        return commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorMessages.COMMENT_NOT_FOUND
                        )
                );
    }

    private void checkOwner(
            ru.skypro.homework.entity.Comment comment
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        boolean isAdmin =
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(
                                role -> role.equals("ROLE_ADMIN")
                        );


        if (isAdmin) {
            return;
        }


        if (comment.getAuthor() == null ||
                !comment.getAuthor()
                        .getEmail()
                        .equals(authentication.getName())) {

            throw new AccessDeniedException(
                    ErrorMessages.YOU_ARE_NOT_THE_OWNER
            );
        }
    }

    private String getCurrentUserEmail() {

        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}