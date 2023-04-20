package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperAdsDTO;
import ru.skypro.homework.dto.ResponseWrapperCommentDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;

    public ResponseWrapperCommentDTO getAdComments(Long adId) {
        List<Comment> comments = commentsRepository.findAllByAdIdOrderByCreationDateTimeAsc(adId);
        if (comments.isEmpty()) {
            throw new NotFoundException("Комментарии к объявлению " + adId + " не найдены");
        }
        List<CommentDTO> commentDTOList = comments.stream()
                .map(CommentDTO::fromComment)
                .collect(Collectors.toList());
        ResponseWrapperCommentDTO responseDTO = new ResponseWrapperCommentDTO();
        responseDTO.setResults(commentDTOList);
        responseDTO.setCount(commentDTOList.size());
        return responseDTO;
    }

    public CommentDTO addComment(Long adId, CommentDTO commentDTO) {
        Comment comment = commentDTO.toComment();
        Ad ad = adsRepository.findById(adId).orElseThrow(() -> new NotFoundException("Объявление "+ adId +
                " не найдено"));
        comment.setId(null);
        comment.setAd(ad);
        // todo comment.setAuthor();
        User user = new User();
        user.setId(1L);
        comment.setAuthor(user);
        comment.setCreationDateTime(Instant.now());
        //
        Comment savedComment = commentsRepository.save(comment);
        return CommentDTO.fromComment(savedComment);
    }

    public void deleteComment(Long adId, Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    public CommentDTO updateComment(Long adId, Long commentId, CommentDTO commentDTO) {
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Комментарий "+ commentId +
                " не найден"));
        comment.setText(commentDTO.getText());
        // todo comment.setAuthor();
//        User user = new User();
//        user.setId(1L);
//        comment.setAuthor(user);
        //
        Comment savedComment = commentsRepository.save(comment);
        return CommentDTO.fromComment(savedComment);
    }
}
