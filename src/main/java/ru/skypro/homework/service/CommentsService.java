package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.mapping.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.utils.CommentDtoWithHttpStatus;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>CommentsService</h2>
 * Service managing comments added to advertisements by users
 */
@Service
@RequiredArgsConstructor
public class CommentsService {
    /**
     * Repository for comments stored in database
     */
    final private CommentRepository commentRepository;
    final private UserRepository userRepository;
    final private AdRepository adRepository;

    public CommentsDto findCommentsRelatedToAd(int id) {

        List<Comment> listOfComments = commentRepository.findByCreatedAt(id);

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setCount(listOfComments.size());
        commentsDto.setResults(
                listOfComments.stream()
                        .map(CommentMapper.INSTANCE::commentToDto)
                        .collect(Collectors.toList()));

        return commentsDto;
    }

    /**
     * @param id                       advertisement identifier
     * @param username                 received from Spring Security by @PreAuthorize annotation
     * @param createOrUpdateCommentDto content of comment to add
     * @return result of add op and HttpStatus
     */
    public CommentDtoWithHttpStatus addComment(int id, String username, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDtoWithHttpStatus result = new CommentDtoWithHttpStatus();
        Ad ad = adRepository.findById((long) /* advertisement identifier */id).orElse(null);
        if (ad == null) {
            return CommentDtoWithHttpStatus.notFound();
        }

        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            return CommentDtoWithHttpStatus.unAuthorized();
        }
        if (!user.getName().equals(username)) {
            return CommentDtoWithHttpStatus.unAuthorized();
        }

        Comment commentNew = new Comment(0, user.getId(), id, createOrUpdateCommentDto.getText());
        result.setCommentDto(CommentMapper.INSTANCE.commentToDto(commentNew));
        result.setHttpStatus(HttpStatus.OK);

        return result;
    }

    /**
     * <h2>deleteComment</h2>
     * <p>
     * Delete comment specified by provided commentId upon made sure user is allowed to do so
     *
     * @param adId      advertisement identifier
     * @param commentId comment identifier
     * @param userName  user name (email) obtained from security by @PreAuthorize annotation
     * @return HttpStatus
     */

    public ResponseEntity<HttpStatus> deleteComment(long adId, long commentId, String userName) {

        // Does comment exist?
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Does advertisement exist?
        // Ad ad = adRepository.findById(adId).orElse(null);
        // if (ad == null){ return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        // I guess it does not matter whether advertisement exist while user has a right
        // to delete own comment

        // Is user authorized?
        User user = userRepository.findById((int) comment.getUserId()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!user.getEmail().equals(userName)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public CommentDtoWithHttpStatus updateComment(long adId, long commentId, String userName,
                                                  CreateOrUpdateCommentDto updatedCommentDto) {
        CommentDtoWithHttpStatus result = new CommentDtoWithHttpStatus();

        // Whether comment exists?
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return CommentDtoWithHttpStatus.notFound();
        }

        // Whether user allowed to update specified comment?
        User author = userRepository.findById((int) comment.getUserId()).orElse(null);
        if (author == null) {
            return CommentDtoWithHttpStatus.unAuthorized();
        }
        if (!author.getName().equals(userName)) {
            return CommentDtoWithHttpStatus.unAuthorized();
        }

        // That's OK, let's update comment ))
        comment.setText(updatedCommentDto.getText());
        comment = commentRepository.save(comment);

        // Return result to controller
        result.setCommentDto(CommentMapper.INSTANCE.commentToDto(comment));
        result.setHttpStatus(HttpStatus.OK);
        return result;
    }
}
