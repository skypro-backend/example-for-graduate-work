package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
     * @param id
     * @return
     */

    public Comments getCommentsByAdId(Integer id) {
        AdEntity ad = adRepository.findById(id).orElseThrow();

        Comments commentsDto = new Comments();

        List<CommentEntity> commentEntityList = ad.getCommentEntities();
        List<Comment> commentDtoList = commentMapper.listCommentToListCommentDTO(commentEntityList);
        commentsDto.setCount(commentDtoList.size());
        commentsDto.setResults(commentDtoList);

        return commentsDto;
    }

    public Comment addCommentToAd(Integer id, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(userEntity);
        commentEntity.setAd(adEntity);
        commentEntity.setCreatedAt(Instant.now().toEpochMilli());
        commentEntity.setText(commentDetails.getText());
        commentEntity.setAuthorImage(userEntity.getImage());
        commentEntity.setAuthorFirstName(userEntity.getFirstName());

        commentRepository.save(commentEntity);
        return commentMapper.commentToCommentDTO(commentEntity);
    }

    @Transactional
    public boolean deleteComment(Integer adId, Integer commentId, UserDetails userDetails) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);
        if (commentEntityOptional.isPresent()) {
            if (checkAccess(userDetails, commentEntityOptional.get())) {
                commentRepository.deleteByPkAndAd_Pk(commentId, adId);
                return true;
            } else throw new ForbiddenException();
        } else return false;
    }

    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails) {

        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        if (checkAccess(userDetails, commentEntity)) {
            commentEntity.setText(commentDetails.getText());

            commentRepository.save(commentEntity);
            return commentMapper.commentToCommentDTO(commentEntity);
        } else throw new ForbiddenException();
    }

    private boolean checkAccess(UserDetails userDetails, CommentEntity commentEntity) {
        return userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || userDetails.getUsername().equals(commentEntity.getAuthor().getUsername());
    }
}
