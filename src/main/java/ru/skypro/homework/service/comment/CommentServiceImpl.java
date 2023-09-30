package ru.skypro.homework.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.exception.AdsNotFound;
import ru.skypro.homework.exception.UserNotFound;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;


    @Override
    public List<CommentDTO> getAllCommentsByAdId(Integer id) {
        return commentRepository.getAllCommentsByAdId(id).stream()
                .map(CommentMapper::fromComment)
                .collect(Collectors.toList());
    }

    @Override
    public void createComment(Integer id, CreateOrUpdateComment comment) {
        User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFound::new);
        commentRepository.save(new Comment(ad.getPk(), Instant.now(), comment.getText(), user, ad));
    }

    @Override
    public void deleteComment(Integer commentId,Integer adId) {
        commentRepository.deleteCommentByPkAndAd_Pk(commentId, adId);
    }

    @Override
    public ResponseEntity<?> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return null;
    }
}