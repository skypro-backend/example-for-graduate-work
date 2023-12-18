package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdIsNotFoundException;
import ru.skypro.homework.exception.CommentIsNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.UsernameIsNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /**
     * Получить все комментарии к объявлению по номеру (id) объявления
     *
     * @param id номер объявления
     * @return CommentsDTO
     */
    @Override
    public Comments getCommentsByAdId(int id) {
        if (adRepository.existsById(id)) {
            return new Comments(commentRepository.findByAd_pk(id).stream()
                    .map(commentMapper::commentToCommentDTO)
                    .collect(Collectors.toList()));
        } else throw new AdIsNotFoundException("Ad is not found");
    }

    @Override
    public Comment addCommentToAd(int id, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        CommentEntity commentEntity = CommentEntity.builder()
                .author(userEntity)
                .ad(adEntity)
                .createdAt(Instant.now())
                .text(commentDetails.getText())
                .authorFirstName(userEntity.getFirstName()).build();

        commentRepository.save(commentEntity);

        return commentMapper.commentToCommentDTO(commentEntity);
    }

    @Override
    @Transactional
    public void deleteComment(int adId, int commentId, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new CommentIsNotFoundException("Comment is not found"));
        checkAccess(userDetails, commentEntity);
        commentRepository.deleteByPkAndAd_Pk(commentId, adId);
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new CommentIsNotFoundException("Comment is not found"));
        checkAccess(userDetails, commentEntity);
        commentEntity.setText(commentDetails.getText());
        commentRepository.save(commentEntity);
        return commentMapper.commentToCommentDTO(commentEntity);
    }

    private void checkAccess(UserDetails userDetails, CommentEntity commentEntity) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !userDetails.getUsername().equals(commentEntity.getAuthor().getEmail())) {
            throw new ForbiddenException();
        }
    }
}
