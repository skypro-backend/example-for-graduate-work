package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final ImageService imageService;

    public CommentServiceImpl(CommentRepository commentRepository,
                              AdRepository adRepository,
                              UserRepository userRepository,
                              CommentMapper commentMapper,
                              ImageService imageService) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
        this.imageService = imageService;
    }

    @Override
    public Comments getComments(Integer adId) {
        log.debug("Getting comments for ad id: {}", adId);
        List<CommentEntity> commentEntities = commentRepository.findByAdId(adId);
        List<Comment> comments = commentEntities.stream()
                .map(comment -> {
                    Comment commentDto = commentMapper.toDto(comment);
                    if (comment.getAuthor().getImage() != null) {
                        commentDto.setAuthorImage(imageService.getImageUrl(comment.getAuthor().getImage()));
                    }
                    return commentDto;
                })
                .collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    @Transactional
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        log.debug("Adding comment to ad id: {} by user: {}", adId, username);
        AdEntity adEntity = findAdById(adId);
        UserEntity author = findUserByUsername(username);

        CommentEntity commentEntity = commentMapper.createCommentEntity(createOrUpdateComment, author, adEntity);
        commentEntity = commentRepository.save(commentEntity);

        Comment commentDto = commentMapper.toDto(commentEntity);
        if (author.getImage() != null) {
            commentDto.setAuthorImage(imageService.getImageUrl(author.getImage()));
        }
        return commentDto;
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, String username) {
        log.debug("Deleting comment with id: {} from ad id: {} by user: {}", commentId, adId, username);
        CommentEntity commentEntity = findCommentById(commentId);

        if (!commentEntity.getAd().getId().equals(adId)) {
            throw new RuntimeException("Comment does not belong to this ad");
        }

        // Проверяем права доступа
        checkAccess(commentEntity, username);

        commentRepository.delete(commentEntity);
    }

    @Override
    @Transactional
    public Comment updateComment(Integer adId, Integer commentId,
                                 CreateOrUpdateComment createOrUpdateComment, String username) {
        log.debug("Updating comment with id: {} from ad id: {} by user: {}", commentId, adId, username);
        CommentEntity commentEntity = findCommentById(commentId);

        if (!commentEntity.getAd().getId().equals(adId)) {
            throw new RuntimeException("Comment does not belong to this ad");
        }

        // Проверяем права доступа
        checkAccess(commentEntity, username);

        commentMapper.updateCommentEntity(createOrUpdateComment, commentEntity);
        commentEntity = commentRepository.save(commentEntity);

        Comment commentDto = commentMapper.toDto(commentEntity);
        if (commentEntity.getAuthor().getImage() != null) {
            commentDto.setAuthorImage(imageService.getImageUrl(commentEntity.getAuthor().getImage()));
        }
        return commentDto;
    }

    @Override
    public boolean isAuthor(String username, Integer commentId) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    // Проверяем, является ли пользователь автором
                    boolean isAuthor = comment.getAuthor().getUsername().equals(username);
                    if (isAuthor) {
                        return true;
                    }
                    // Если не автор, проверяем, является ли администратором
                    return userRepository.findByUsername(username)
                            .map(user -> user.getRole() == Role.ADMIN)
                            .orElse(false);
                })
                .orElse(false);
    }

    private AdEntity findAdById(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    private CommentEntity findCommentById(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    private void checkAccess(CommentEntity commentEntity, String username) {
        UserEntity user = findUserByUsername(username);

        // Если пользователь администратор - разрешаем
        if (user.getRole() == Role.ADMIN) {
            log.debug("Admin {} is performing action on comment {}", username, commentEntity.getId());
            return;
        }

        // Иначе проверяем, что пользователь является автором
        if (!commentEntity.getAuthor().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not the author of this comment and not an admin");
        }
    }
}
