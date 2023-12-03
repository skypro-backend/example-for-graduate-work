package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
     * @param id
     * @return
     */

    public Comments getCommentsByAdId(Integer id){
        AdEntity ad = adRepository.findById(id).orElseThrow();

        Comments commentsDto = new Comments();

        List<CommentEntity> commentEntityList = ad.getCommentEntities();
        List<Comment> commentDtoList = commentMapper.listCommentToListCommentDTO(commentEntityList);
        var test = commentDtoList.size();
        commentsDto.setCount(commentDtoList.size());
        commentsDto.setResults(commentDtoList);

        return commentsDto;
    }

    public Comment addCommentToAd(Integer id, CreateOrUpdateComment commentDetails, UserDetails userDetails){
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
    public ResponseEntity<String> deleteComment(Integer adId, Integer commentId, UserDetails userDetails){
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);

        if (commentEntityOptional.isPresent()){
           commentRepository.deleteByPkAndAd_Pk(commentId, adId);
           return new ResponseEntity<>("Комментарий удален", HttpStatus.OK);
        } else return new ResponseEntity<>("Комментарий не найден", HttpStatus.BAD_REQUEST);
    }

    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDetails){

        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        commentEntity.setText(commentDetails.getText());

        commentRepository.save(commentEntity);
        return commentMapper.commentToCommentDTO(commentEntity);
    }
}
