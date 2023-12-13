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
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
//TODO: add exceptions
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
        List<CommentEntity> commentEntityList = commentRepository.findByAd_pk(id);
        List<Comment> commentDtoList = commentMapper.listCommentToListCommentDTO(commentEntityList);

        Comments commentsDto = new Comments();

        commentsDto.setCount(commentDtoList.size());
        commentsDto.setResults(commentDtoList);

        return commentsDto;
    }

    @Override
    public Comment addCommentToAd(int id, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        CommentEntity commentEntity = CommentEntity.builder().
                author(userEntity).
                ad(adEntity).
                createdAt(Instant.now().toEpochMilli()).
                text(commentDetails.getText()).
                authorImage(userEntity.getImageEntity()).
                authorFirstName(userEntity.getFirstName()).build();

        commentRepository.save(commentEntity);

        return commentMapper.commentToCommentDTO(commentEntity);
    }

    //TODO: think how to delete with adId, check transactional
    @Override
    @Transactional
    public void deleteComment(int adId, int commentId, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        if (checkAccess(userDetails, commentEntity)) {
            commentRepository.deleteByPkAndAd_Pk(commentId, adId);
        } else throw new ForbiddenException();
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        if (checkAccess(userDetails, commentEntity)) {
            commentEntity.setText(commentDetails.getText());
            commentRepository.save(commentEntity);
            return commentMapper.commentToCommentDTO(commentEntity);
        } else throw new ForbiddenException();
    }

    private boolean checkAccess(UserDetails userDetails, CommentEntity commentEntity) {
        return userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || userDetails.getUsername().equals(commentEntity.getAuthor().getEmail());
    }
}
