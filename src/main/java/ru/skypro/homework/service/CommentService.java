package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.util.DateTimeConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private AdRepository adRepository;

    public Comment toEntity(ru.skypro.homework.dto.Comment DTO) {
        return Comment.builder().pk(DTO.getPk()).text(DTO.getText()).authorImage(DTO.getAuthorImage())
                .author(userRepository.getReferenceById(DTO.getAuthor())).authorFirstName(DTO.getAuthorFirstName())
                .createdAt(DateTimeConverter.toLocalDateTime(DTO.getCreatedAt())).build();
    }

    public ru.skypro.homework.dto.Comment toDTO(Comment entity) {
        return ru.skypro.homework.dto.Comment.builder().pk(entity.getPk()).author(entity.getAuthor().getId())
                .text(entity.getText()).authorFirstName(entity.getAuthorFirstName()).authorImage(entity.getAuthorImage())
                .createdAt(DateTimeConverter.toLong(entity.getCreatedAt())).build();
    }

    public List<ru.skypro.homework.dto.Comment> toDTOList(List<Comment> comments) {
        List<ru.skypro.homework.dto.Comment> DTOComment = new ArrayList<>();
        for (Comment comment : comments) {
            DTOComment.add(toDTO(comment));
        }
        return DTOComment;
    }

    public Comments getCommentsOfAd(long postId) {
        List<Comment> comments = adRepository.getReferenceById(postId).getComments();
        return new Comments(comments.size(), toDTOList(comments));
    }

    public void createComment(long postId, CreateOrUpdateComment comment, UserDetails userDetails) {
        User user = findUser(userDetails);
        Comment newComment = Comment.builder().ad(adRepository.getReferenceById(postId)).author(user)
                .authorFirstName(user.getFirstname()).authorImage(user.getImage()).text(comment.getText())
                .createdAt(LocalDateTime.now()).build();
        commentRepository.save(newComment);
    }

    private User findUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername());
    }

    public boolean commentExist(long commentId){
        return commentRepository.existsById(commentId);
    }

    public boolean commentPostedByUser(long commentId, UserDetails userDetails){
        return commentRepository.getReferenceById(commentId).getAuthor().equals(findUser(userDetails));
    }

    public void deleteComment(long commentId){
        commentRepository.deleteById(commentId);
    }

    public  void updateComment(long commentId, CreateOrUpdateComment comment){
        Comment newComment = commentRepository.getReferenceById(commentId);
        newComment.setText(comment.getText());
        commentRepository.save(newComment);
    }
}
