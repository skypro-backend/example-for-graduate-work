package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.IncorrectArgumentException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsServiceImpl adsService;
    private final UserServiceImpl userService;

    @Override
    public CommentDto create(Integer id, CommentDto commentDto, Authentication authentication) {
        log.debug("Adding comment for ads with id: {}", id);

        if(commentDto.getText() == null || commentDto.getText().isBlank()) throw new IncorrectArgumentException();

        Comment comment = CommentMapper.INSTANSE.toEntity(commentDto);
        User user = userService.getByUsername(authentication.getName());
        comment.setAuthor(user);
        comment.setAds(adsService.findAdsById(id));
        comment.setCreatedAt(Instant.now());
        commentRepository.save(comment);
        return CommentMapper.INSTANSE.toDto(comment);
    }

    @Override
    public CommentDto update(Integer adId, Integer commentId, CommentDto commentDto) {
        log.debug("Updating comment with id: {} for ads with id: {}", commentId, adId);

        if(commentDto.getText() == null || commentDto.getText().isBlank()) throw new IncorrectArgumentException();

        Comment adsComment = getAdsComment(commentId, adId);
        adsComment.setText(commentDto.getText());
        commentRepository.save(adsComment);
        return CommentMapper.INSTANSE.toDto(adsComment);
    }

    @Override
    public List<CommentDto> get(Integer id) {
        log.debug("Getting comments for ads with id: {}", id);
        return commentRepository.findAllByAdsId(id)
                .stream()
                .map(CommentMapper.INSTANSE::toDto)
                .collect(Collectors.toList());
    }
    public Comment getAdsComment(Integer commentId, Integer adId) {
        log.debug("Getting comment with id: {} for ads with id: {}", commentId, adId);
        return commentRepository.findByIdAndAdsId(commentId, adId).orElseThrow(CommentNotFoundException::new);
    }
    @Override
    public Comment getById(Integer id) {
        log.debug("Getting comment with id: {}", id);
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public void remove(Integer adId, Integer commentId) {
        log.debug("Deleting comment with id: {} for ads with id: {}", commentId, adId);
        Comment comment = getAdsComment(commentId, adId);
        commentRepository.delete(comment);
        log.info("Comment removed successfully");
    }
}
