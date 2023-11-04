package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.utils.MyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {
    private final MyMapper mapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public Comments getCommentsByAds(Integer id) {
        List<CommentDto> comments = commentRepository.findAllByAd(id).stream()
                .map(entity -> mapper.map(entity))
                .collect(Collectors.toList());
        return new Comments(comments.size(), comments);
    }

    public CommentDto addComment(Integer id, CreateOrUpdateComment text) {
        UserEntity user = userRepository.findByUsername("username");
        AdEntity ad = adRepository.findById(Long.valueOf(id)).get();
        CommentEntity comment = mapper.map(text, user, ad);
        commentRepository.save(comment);
        return mapper.map(comment);
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(Long.valueOf(commentId));
    }

    public CommentDto updateComment(Integer commentId, CreateOrUpdateComment text) {
        CommentEntity comment = commentRepository.findById(Long.valueOf(commentId)).get();
        comment.setText(text.getText());
        commentRepository.save(comment);
        return mapper.map(comment);
    }
}
