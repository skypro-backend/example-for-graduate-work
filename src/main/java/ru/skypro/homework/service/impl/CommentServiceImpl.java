package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Collection<CommentsDTO> getAllComments(int idAd) {
        log.info("getAllComments method");
        List<CommentEntity> comments = commentRepository.findAllByAdId(idAd);
        return commentMapper.toCommentsListDto(comments);
    }

    @Override
    public CommentEntity getComment(long commentId) {
        log.info("getComment method");
        return commentRepository.findById(commentId).orElseThrow();
    }

    @Override
    public CommentDTO addComment(int id, CreateOrUpdateCommentDTO comment, Authentication authentication) {
        log.info("addComment method");
        CommentEntity newComment = commentMapper.toComment(comment);
        newComment.setAd(adRepository.findById((long) id)
                .orElseThrow(AdNotFoundException::new));
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setAuthor(userRepository.getUserByEmail(authentication.getName()));
        commentRepository.save(newComment);

        return commentMapper.toCommentDTO(newComment);
    }

    @Transactional
    @Override
    public void deleteComment(int adId, int commentId) {
        log.info("deleteComment method");
        commentRepository.deleteByIdAndAdId(commentId, adId);
    }

    @Override
    public void deleteAllByAdId(long adId) {
        log.info("deleteAllByAdId method");
        commentRepository.deleteAllByAdId(adId);
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, CommentDTO commentDTO) {
        log.info("updateComment method");
        CommentEntity updatedComment = commentRepository.findByIdAndAd_Id(commentId, adId);
        updatedComment.setText(commentDTO.getText());
        commentRepository.save(updatedComment);

        return commentMapper.toCommentDTO(updatedComment);
    }
}
