package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private UserEntity user;
    private UserEntity admin;
    private AdEntity ad;
    private CommentEntity comment;
    private Comment commentDto;
    private LocalDateTime createdAt;

    @BeforeEach
    void setUp() {
        createdAt = LocalDateTime.now();

        user = new UserEntity();
        user.setId(1);
        user.setEmail("user@test.com");
        user.setRole(ru.skypro.homework.entity.Role.USER);

        admin = new UserEntity();
        admin.setId(2);
        admin.setEmail("admin@test.com");
        admin.setRole(ru.skypro.homework.entity.Role.ADMIN);

        ad = new AdEntity();
        ad.setPk(100);

        comment = new CommentEntity();
        comment.setPk(500);
        comment.setText("Отличное объявление!");
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setCreatedAt(createdAt);

        commentDto = new Comment();
        commentDto.setPk(500);
        commentDto.setText("Отличное объявление!");
        commentDto.setAuthor(1);
        commentDto.setAuthorImage("/image/user/1");
        commentDto.setAuthorFirstName("Иван");
        // Преобразуем LocalDateTime → Long (timestamp)
        commentDto.setCreatedAt(LocalDateTimeToTimestamp(createdAt));
    }

    private Long LocalDateTimeToTimestamp(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Test
    void getComments_shouldReturnAllCommentsForAd() {
        // Given
        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(commentRepository.findByAdPk(100)).thenReturn(Arrays.asList(comment));
        when(commentMapper.toDto(comment)).thenReturn(commentDto);

        // When
        Comments result = commentService.getComments(100);

        // Then
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getResults()).hasSize(1);
        assertThat(result.getResults().get(0).getText()).isEqualTo("Отличное объявление!");
        verify(adRepository).findById(100);
        verify(commentRepository).findByAdPk(100);
    }

    @Test
    void getComments_whenAdNotFound_shouldThrowException() {
        // Given
        when(adRepository.findById(999)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> commentService.getComments(999)).isInstanceOf(EntityNotFoundException.class).hasMessage("Объявление с ID 999 не найдено");
    }

    @Test
    void addComment_shouldCreateNewComment() {
        // Given
        CreateOrUpdateComment dto = new CreateOrUpdateComment();
        dto.setText("Новый комментарий");

        when(adRepository.findById(100)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        when(commentRepository.save(any(CommentEntity.class))).thenAnswer(i -> {
            CommentEntity saved = i.getArgument(0);
            saved.setPk(501);
            return saved;
        });

        Comment expectedDto = new Comment();
        expectedDto.setPk(501);
        expectedDto.setText("Новый комментарий");
        expectedDto.setAuthor(1);
        expectedDto.setAuthorImage("/image/user/1");
        expectedDto.setAuthorFirstName("Иван");
        expectedDto.setCreatedAt(LocalDateTimeToTimestamp(createdAt)); // как в маппере

        when(commentMapper.toDto(any(CommentEntity.class))).thenAnswer(i -> {
            CommentEntity entity = i.getArgument(0);
            Comment result = new Comment();
            result.setPk(entity.getPk());
            result.setText(entity.getText());
            result.setAuthor(entity.getAuthor().getId());
            result.setAuthorImage("/image/user/" + entity.getAuthor().getId());
            result.setAuthorFirstName(entity.getAuthor().getFirstName());
            result.setCreatedAt(LocalDateTimeToTimestamp(entity.getCreatedAt()));
            return result;
        });

        // When
        Comment result = commentService.addComment(100, dto, "user@test.com");

        // Then
        assertThat(result.getText()).isEqualTo("Новый комментарий");
        assertThat(result.getAuthor()).isEqualTo(1);
        verify(commentRepository).save(argThat(c -> c.getText().equals("Новый комментарий") && c.getAuthor().getId().equals(1) && c.getAd().getPk().equals(100)));
    }

    @Test
    void deleteComment_shouldDeleteIfAuthor() {
        // Given
        when(commentRepository.findById(500)).thenReturn(Optional.of(comment));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        // When
        commentService.deleteComment(100, 500, "user@test.com");

        // Then
        verify(commentRepository).deleteById(500);
    }

    @Test
    void deleteComment_whenUserIsAdmin_shouldDelete() {
        // Given
        when(commentRepository.findById(500)).thenReturn(Optional.of(comment));
        when(userRepository.findByEmail("admin@test.com")).thenReturn(Optional.of(admin));

        // When
        commentService.deleteComment(100, 500, "admin@test.com");

        // Then
        verify(commentRepository).deleteById(500);
    }

    @Test
    void deleteComment_whenNotAuthorOrAdmin_shouldThrowAccessDenied() {
        // Given
        UserEntity otherUser = new UserEntity();
        otherUser.setId(3);
        otherUser.setRole(ru.skypro.homework.entity.Role.USER);

        when(commentRepository.findById(500)).thenReturn(Optional.of(comment));
        when(userRepository.findByEmail("other@test.com")).thenReturn(Optional.of(otherUser));

        // When & Then
        assertThatThrownBy(() -> commentService.deleteComment(100, 500, "other@test.com")).isInstanceOf(org.springframework.security.access.AccessDeniedException.class).hasMessage("Нет прав на удаление");
    }

    @Test
    void updateComment_shouldUpdateTextIfAuthor() {
        // Given
        CreateOrUpdateComment dto = new CreateOrUpdateComment();
        dto.setText("Обновлённый комментарий");

        when(commentRepository.findById(500)).thenReturn(Optional.of(comment));
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        when(commentRepository.save(any(CommentEntity.class))).thenAnswer(i -> {
            CommentEntity updated = i.getArgument(0);
            updated.setCreatedAt(createdAt);
            return updated;
        });

        when(commentMapper.toDto(any(CommentEntity.class))).thenAnswer(i -> {
            CommentEntity entity = i.getArgument(0);
            Comment result = new Comment();
            result.setPk(entity.getPk());
            result.setText(entity.getText());
            result.setAuthor(entity.getAuthor().getId());
            result.setAuthorImage("/image/user/" + entity.getAuthor().getId());
            result.setAuthorFirstName("Иван");
            result.setCreatedAt(LocalDateTimeToTimestamp(entity.getCreatedAt()));
            return result;
        });

        // When
        Comment result = commentService.updateComment(100, 500, dto, "user@test.com");

        // Then
        assertThat(result.getText()).isEqualTo("Обновлённый комментарий");
        verify(commentRepository).save(argThat(c -> c.getText().equals("Обновлённый комментарий")));
    }
}