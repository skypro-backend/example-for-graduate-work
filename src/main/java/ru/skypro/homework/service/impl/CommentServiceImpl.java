package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
        var test = adRepository.findById(4);
        AdEntity ad = adRepository.findById(id).orElseThrow();

        Comments commentsDto = new Comments();

        List<CommentEntity> commentEntityList = ad.getCommentEntities();
        List<Comment> commentDtoList = commentMapper.listCommentToListCommentDTO(commentEntityList);

        commentsDto.setCount(commentDtoList.size());
        commentsDto.setResults(commentDtoList);

        return commentsDto;
    }

    public Comment addCommentToAd(Integer id, CreateOrUpdateComment commentDetails, UserDetails userDetails){
        AdEntity adEntity = adRepository.findById(id).get();
        //UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername()).get();
        UserEntity userEntity = userRepository.findByUsername("BobBob").get();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(userEntity);
        commentEntity.setAd(adEntity);
        commentEntity.setCreatedAt(Instant.now().toEpochMilli());
        commentEntity.setText(commentDetails.getText());

        commentRepository.save(commentEntity);
        return commentMapper.commentToCommentDTO(commentEntity);
    }

    public ResponseEntity<String> deleteComment(Long adId, Integer commentId, UserDetails userDetails){
        CommentEntity commentEntity = commentRepository.findById(commentId).get();

        if (commentEntity != null){
           //commentRepository.deleteByPkAndAdEntity_Pk(commentId, adId);
            commentRepository.deleteByPk(commentId);
           return new ResponseEntity<>(commentEntity.getText(), HttpStatus.OK);
        } else return new ResponseEntity<>("Комментарий не найден", HttpStatus.BAD_REQUEST);
    }


}
