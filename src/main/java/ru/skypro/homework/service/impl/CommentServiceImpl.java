package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.Comments;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ICommentService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepo;
    private final AdRepository adRepo;
    private final UserRepository userRepo;
    private final CommentMapper mapper;

    @Override
    public Comments getCommentsForAd(long adId) {
        var list = commentRepo.findByAdId(adId);
        return mapToComments(list);
    }

    @Override
    public Comments getMyAds(Authentication auth) {
        long userId = getCurrentUserId(auth);
        var page = commentRepo.findByAuthorId(userId, PageRequest.of(0, 10));
        return mapToComments(page.getContent());
    }

    @Override
    public Comment createComment(long adId, CreateOrUpdateComment data, Authentication auth) {
        var ad = adRepo.findById(adId).orElseThrow();
        var entity = mapper.dtoToEntity(data);
        entity.setAuthor(getCurrentUser(auth));
        entity.setAd(ad);
        return mapper.entityToDto(commentRepo.save(entity));
    }

    @Override
    public Comment updateComment(long adId, long commentId, CreateOrUpdateComment data, Authentication auth) {
        var existing = commentRepo.findById(commentId).orElseThrow();
        if (!isAdmin(auth) && !existing.getAuthor().getId().equals(getCurrentUserId(auth))) {
            throw new AccessDeniedException("You can't edit other user's comments");
        }
        mapper.updateFromDto(data, existing);
        return mapper.entityToDto(commentRepo.save(existing));
    }

    @Override
    public void deleteComment(long adId, long commentId, Authentication auth) {
        var comment = commentRepo.findById(commentId).orElseThrow();
        if (!isAdmin(auth) && !comment.getAuthor().getId().equals(getCurrentUserId(auth))) {
            throw new AccessDeniedException("You can't delete other user's comments");
        }
        commentRepo.deleteById(commentId);
    }

    private Comments mapToComments(Collection<CommentEntity> entities) {
        var result = new Comments();
        result.setCount(entities.size());
        result.setResults(entities.stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList()));
        return result;
    }

    private UserEntity getCurrentUser(Authentication auth) {
        return userRepo.findByUsername(auth.getName()).orElseThrow();
    }

    private long getCurrentUserId(Authentication auth) {
        return getCurrentUser(auth).getId();
    }

    private boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"));
    }
}