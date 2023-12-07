package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.CommentService;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private AdEntityRepository adEntityRepository;

    private CommentEntityRepository commentEntityRepository;
    private CommentMapper commentMapper;
    private final String imageDir;
    private UserEntityRepository userEntityRepository;

    public CommentServiceImpl(AdEntityRepository adEntityRepository,
                              @Value("${path.to.images.folder}") String imageDir,
                              CommentEntityRepository commentEntityRepository,
                              CommentMapper commentMapper,
                              UserEntityRepository userEntityRepository) {
        this.commentEntityRepository = commentEntityRepository;
        this.commentMapper = commentMapper;
        this.adEntityRepository = adEntityRepository;
        this.imageDir = imageDir;
        this.userEntityRepository = userEntityRepository;
    }

    public Comments getComments(Integer id) {

        List<CommentEntity> commentEntities = commentEntityRepository.findAll();

        if (commentEntities.isEmpty()) {
            throw new EntityNotFoundException("Cообщения отсутствуют!");
        }
        List<Comment> comments = commentMapper.commentEntityListToCommentList(commentEntities);

        return Comments.builder()
                .results(comments)
                .count(comments.size())
                .build();
    }

    public Comment addComment(Integer id, CreateOrUpdateComment createOrUpdateComment) {

        if (createOrUpdateComment.getText() == null) {
            throw new BlankFieldException("Пустое поле обновленного комментария");
        }
        AdEntity adEntity = adEntityRepository.findById(id).get();

        UserEntity userEntity = adEntity.getUserEntity();

        CommentEntity commentEntity = commentMapper.commentToCommentEntity(createOrUpdateComment);
        commentEntity.setAdId(adEntity);
        commentEntity.setCreatedAt(Instant.now());
        commentEntity.setUserEntity(userEntity);

        commentEntityRepository.save(commentEntity);

        return commentMapper.commentEntityToComment(commentEntity);
    }
    public void deleteComment(Integer adId, Integer commentId) {

        AdEntity adEntity = adEntityRepository.findById(adId).get();
        Collection<CommentEntity> collection = adEntity.getCommentEntities();
        CommentEntity commentEntity = collection.stream()
                .skip(commentId-2).findFirst().orElse(null);

        if (commentEntity == null) {
            throw new EntityNotFoundException("Cообщение для удаления отсутствует!");
        }

        commentEntityRepository.deleteById(commentId);
    }
    public Comment updateComment(Integer adId, Integer commentId
            ,CreateOrUpdateComment createOrUpdateComment) {
        AdEntity adEntity = adEntityRepository.findById(adId).get();
        Collection<CommentEntity> collection = adEntity.getCommentEntities();
        CommentEntity commentEntity = collection.stream()
                .skip(commentId-2).findFirst().orElse(null);

        if (commentEntity == null) {
            throw new EntityNotFoundException("Cообщение для редактирования отсутствует!");
        }
        commentEntity.setText(createOrUpdateComment.getText());

        commentEntityRepository.save(commentEntity);

        return commentMapper.commentEntityToComment(commentEntity);
    }




}
