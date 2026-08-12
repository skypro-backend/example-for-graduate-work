package ru.skypro.homework.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.mapper.CommentMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(Long adId) {
        List<CommentEntity> comments = commentRepository.findAllByAdId(adId);
        Comments result = new Comments();
        result.setCount((long) comments.size());
        result.setResults(comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    public Comment addComment(Long adId, CreateOrUpdateComment text, Authentication authentication) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Ad not found"));
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        CommentEntity comment = new CommentEntity();
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setText(text.getText());
        comment.setCreatedAt(System.currentTimeMillis());

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long adId, Long commentId, Authentication authentication) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        checkPermission(comment, authentication);
        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment text, Authentication authentication) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        checkPermission(comment, authentication);
        comment.setText(text.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    private void checkPermission(CommentEntity comment, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!comment.getAuthor().getEmail().equals(authentication.getName()) && !isAdmin) {
            throw new RuntimeException("No permission");
        }
    }
}