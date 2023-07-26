package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository commentsRepository;
    private final UserService userService;
    private final AdsRepository adsRepository;

    @Override
    public List<CommentDto> listCommentsAdById(Long id) {

        List<Comment> commentList = commentsRepository.findAllByAdId(id);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentDtoList.add(CommentMapper.INSTANCE.commentToDto(comment));
        }

        return commentDtoList;

    }

    @Override
    public Optional<CommentDto> createComment(String userLogin, Long id, CreateOrUpdateComment createComment) {

        Optional<User> optionalUser = userService.getUserByLogin(userLogin);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        Optional<Ad> optionalAd = adsRepository.findById(id);

        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }

        User user = optionalUser.get();
        Ad ad = optionalAd.get();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAd(ad);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(createComment.getText());

        commentsRepository.save(comment);

        return Optional.ofNullable(CommentMapper.INSTANCE.commentToDto(comment));

    }

    @Override
    public boolean deleteById(String userLogin, Long adId, Long commentId) {

        Optional<User> optionalUser = userService.getUserByLogin(userLogin);

        if (optionalUser.isEmpty()) {
            return false;
        }

        if (!adsRepository.existsById(adId)) {
            return false;
        }

        if (commentsRepository.existsById(commentId)) {

            commentsRepository.deleteById(commentId);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public Optional<CommentDto> editComment(String userLogin,
                                            Long adId,
                                            Long commentId,
                                            CreateOrUpdateComment updateComment) {

        Optional<User> optionalUser = userService.getUserByLogin(userLogin);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        if (!adsRepository.existsById(adId)) {
            return Optional.empty();
        }

        Optional<Comment> optionalComment = commentsRepository.findById(commentId);

        if (optionalComment.isEmpty()) {
            return Optional.empty();
        }

        Comment comment = optionalComment.get();

        comment.setText(updateComment.getText());
        commentsRepository.save(comment);

        return Optional.ofNullable(CommentMapper.INSTANCE.commentToDto(comment));

    }

}
