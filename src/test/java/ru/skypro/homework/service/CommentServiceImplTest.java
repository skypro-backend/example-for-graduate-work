package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты сервиса комментариев")
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdvertisementRepository advertisementRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("addComment - успешное создание комментария")
    void addComment_Success() {
        Integer adId = 1;
        String authorEmail = "user@example.com";
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();
        commentRequest.setText("Test comment");

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);

        UserEntity author = new UserEntity();
        author.setEmail(authorEmail);

        CommentEntity entity = new CommentEntity();
        CommentEntity savedEntity = new CommentEntity();
        savedEntity.setId(1);
        savedEntity.setText("Test comment");
        savedEntity.setCreatedAt(LocalDateTime.now());

        Comment expectedComment = new Comment();
        expectedComment.setPk(1);
        expectedComment.setText("Test comment");

        when(advertisementRepository.findById(adId)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail(authorEmail)).thenReturn(Optional.of(author));
        when(commentMapper.toEntity(commentRequest)).thenReturn(entity);
        when(commentRepository.save(entity)).thenReturn(savedEntity);
        when(commentMapper.toDto(savedEntity)).thenReturn(expectedComment);

        Comment result = commentService.addComment(adId, commentRequest, authorEmail);

        assertThat(result).isNotNull();
        assertThat(result.getText()).isEqualTo("Test comment");
        verify(commentRepository).save(entity);
    }

    @Test
    @DisplayName("addComment - ошибка при несуществующем объявлении")
    void addComment_AdNotFound_ThrowsException() {
        Integer adId = 999;
        String authorEmail = "user@example.com";
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();

        when(advertisementRepository.findById(adId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.addComment(adId, commentRequest, authorEmail))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("addComment - ошибка при несуществующем пользователе")
    void addComment_UserNotFound_ThrowsException() {
        Integer adId = 1;
        String authorEmail = "nonexistent@example.com";
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);

        when(advertisementRepository.findById(adId)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail(authorEmail)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.addComment(adId, commentRequest, authorEmail))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("getCommentsByAdId - успешное получение комментариев")
    void getCommentsByAdId_Success() {
        Integer adId = 1;

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1);
        commentEntity.setText("Test comment");

        Comment commentDto = new Comment();
        commentDto.setPk(1);
        commentDto.setText("Test comment");

        when(commentRepository.findByAdId(adId)).thenReturn(List.of(commentEntity));
        when(commentMapper.toDto(commentEntity)).thenReturn(commentDto);

        Comments result = commentService.getCommentsByAdId(adId);

        assertThat(result).isNotNull();
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getResults()).hasSize(1);
    }

    @Test
    @DisplayName("getCommentsByAdId - пустой список комментариев")
    void getCommentsByAdId_EmptyList() {
        Integer adId = 1;

        when(commentRepository.findByAdId(adId)).thenReturn(Collections.emptyList());

        Comments result = commentService.getCommentsByAdId(adId);

        assertThat(result).isNotNull();
        assertThat(result.getCount()).isEqualTo(0);
        assertThat(result.getResults()).isEmpty();
    }

    @Test
    @DisplayName("updateComment - успешное обновление комментария")
    void updateComment_Success() {
        Integer adId = 1;
        Integer commentId = 1;
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();
        commentRequest.setText("Updated comment");

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);

        CommentEntity existingComment = new CommentEntity();
        existingComment.setId(commentId);
        existingComment.setAd(ad);
        existingComment.setText("Old comment");

        CommentEntity updatedComment = new CommentEntity();
        updatedComment.setId(commentId);
        updatedComment.setText("Updated comment");

        Comment expectedDto = new Comment();
        expectedDto.setPk(commentId);
        expectedDto.setText("Updated comment");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(existingComment)).thenReturn(updatedComment);
        when(commentMapper.toDto(updatedComment)).thenReturn(expectedDto);

        Comment result = commentService.updateComment(adId, commentId, commentRequest);

        assertThat(result).isNotNull();
        assertThat(result.getText()).isEqualTo("Updated comment");
    }

    @Test
    @DisplayName("updateComment - ошибка при несуществующем комментарии")
    void updateComment_CommentNotFound_ThrowsException() {
        Integer adId = 1;
        Integer commentId = 999;
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.updateComment(adId, commentId, commentRequest))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("updateComment - ошибка когда комментарий не принадлежит объявлению")
    void updateComment_CommentNotBelongToAd_ThrowsException() {
        Integer adId = 1;
        Integer commentId = 1;
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();

        AdvertisementEntity differentAd = new AdvertisementEntity();
        differentAd.setId(999);

        CommentEntity existingComment = new CommentEntity();
        existingComment.setId(commentId);
        existingComment.setAd(differentAd);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        assertThatThrownBy(() -> commentService.updateComment(adId, commentId, commentRequest))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("deleteComment - успешное удаление комментария")
    void deleteComment_Success() {
        Integer adId = 1;
        Integer commentId = 1;

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(adId);

        CommentEntity existingComment = new CommentEntity();
        existingComment.setId(commentId);
        existingComment.setAd(ad);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        doNothing().when(commentRepository).delete(existingComment);

        commentService.deleteComment(adId, commentId);

        verify(commentRepository).delete(existingComment);
    }

    @Test
    @DisplayName("deleteComment - ошибка при несуществующем комментарии")
    void deleteComment_CommentNotFound_ThrowsException() {
        Integer adId = 1;
        Integer commentId = 999;

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.deleteComment(adId, commentId))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("deleteComment - ошибка когда комментарий не принадлежит объявлению")
    void deleteComment_CommentNotBelongToAd_ThrowsException() {
        Integer adId = 1;
        Integer commentId = 1;

        AdvertisementEntity differentAd = new AdvertisementEntity();
        differentAd.setId(999);

        CommentEntity existingComment = new CommentEntity();
        existingComment.setId(commentId);
        existingComment.setAd(differentAd);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        assertThatThrownBy(() -> commentService.deleteComment(adId, commentId))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("status")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}