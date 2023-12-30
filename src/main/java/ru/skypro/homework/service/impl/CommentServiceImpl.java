package ru.skypro.homework.service.impl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.AuthWasNotPerformedException;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingAdException;
import ru.skypro.homework.exceptions.MissingCommentException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.helper.AuthenticationCheck;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final AdEntityRepository adEntityRepository;
    private final CommentEntityRepository commentEntityRepository;
    private final CommentMapper commentMapper;
    private final AuthenticationCheck authenticationCheck;
    private final UserEntityRepository userEntityRepository;

    public CommentServiceImpl(AdEntityRepository adEntityRepository, CommentEntityRepository commentEntityRepository, CommentMapper commentMapper, AuthenticationCheck authenticationCheck, UserEntityRepository userEntityRepository) {
        this.adEntityRepository = adEntityRepository;
        this.commentEntityRepository = commentEntityRepository;
        this.commentMapper = commentMapper;
        this.authenticationCheck = authenticationCheck;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Comments getComments(Integer adId) {

        List<Comment> comments = commentMapper
                .commentEntityListToCommentList(commentEntityRepository.findByAdEntity_id(adId));
        return Comments.builder()
                .results(comments)
                .count(comments.size())
                .build();
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, CustomUserDetails userDetails) {
        if (createOrUpdateComment.getText() == null) {
            throw new BlankFieldException("Пустое поле обновленного комментария");
        }

        AdEntity adEntity = adEntityRepository.findById(adId).orElseThrow(() -> new MissingAdException("The ad with the specified id is missing "));
        authenticationCheck.accessCheck(userDetails, adEntity.getUserEntity());

        CommentEntity newComment = CommentEntity.builder()
                .text(createOrUpdateComment.getText())
                .createdAt(Instant.now())
                .userEntity(adEntity.getUserEntity())
                .adEntity(adEntity)
                .build();

        return commentMapper.commentEntityToComment(commentEntityRepository.save(newComment));
    }
    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, CustomUserDetails userDetails) {

        CommentEntity commentEntity = commentEntityRepository.findById(commentId).orElseThrow(() -> new MissingAdException(" The comment with the specified id is missing"));
        authenticationCheck.accessCheck(userDetails, commentEntity.getUserEntity());

        commentEntityRepository.deleteById(commentId);
    }
    @Override
    public Comment updateComment(Integer adId, Integer commentId,CreateOrUpdateComment createOrUpdateComment,CustomUserDetails userDetails) {

        CommentEntity commentEntity = commentEntityRepository.findById(commentId).orElseThrow(() -> new MissingAdException(" The comment with the specified id is missing"));
        authenticationCheck.accessCheck(userDetails, commentEntity.getUserEntity());

        commentEntity.setText(createOrUpdateComment.getText());
        commentEntityRepository.save(commentEntity);

        return commentMapper.commentEntityToComment(commentEntity);
    }




}
