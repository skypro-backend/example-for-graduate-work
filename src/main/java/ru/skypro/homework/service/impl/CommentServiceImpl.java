package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

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
    public Comments getComments(Integer adId) {
        List<Comment> comments = commentRepository.findByAdPk(adId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    public Comment addComment(Integer adId, Comment comment, String username) {
        AdEntity ad = adRepository.getReferenceById(adId);
        UserEntity author = userRepository.findByEmail(username).orElse(null);

        CommentEntity entity = commentMapper.toEntity(comment);
        entity.setAd(ad);
        entity.setAuthor(author);

        CommentEntity saved = commentRepository.save(entity);
        return commentMapper.toDto(saved);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, Comment comment) {
        CommentEntity existing = commentRepository.getReferenceById(commentId);
        existing.setText(comment.getText());
        CommentEntity updated = commentRepository.save(existing);
        return commentMapper.toDto(updated);
    }
}