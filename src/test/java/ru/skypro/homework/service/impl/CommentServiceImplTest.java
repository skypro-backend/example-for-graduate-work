package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-тесты сервиса работы с комментариями.
 */
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    private static final Long AD_ID = 1L;
    private static final Long COMMENT_ID = 10L;

    private static final String OWNER_USERNAME =
            "owner@mail.ru";

    private static final String OTHER_USERNAME =
            "other@mail.ru";

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentMapper commentMapper;

    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentServiceImpl(
                commentRepository,
                adRepository,
                userRepository,
                commentMapper
        );
    }

    @Test
    @DisplayName("Получение комментариев возвращает список DTO")
    void getCommentsShouldReturnComments() {
        AdEntity ad = createAd();

        CommentEntity firstEntity =
                createCommentEntity(ad);

        CommentEntity secondEntity =
                createCommentEntity(ad);

        Comment firstDto = new Comment();
        Comment secondDto = new Comment();

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository
                .findAllByAdOrderByCreatedAtAsc(ad))
                .thenReturn(
                        List.of(
                                firstEntity,
                                secondEntity
                        )
                );

        when(commentMapper.toDto(firstEntity))
                .thenReturn(firstDto);

        when(commentMapper.toDto(secondEntity))
                .thenReturn(secondDto);

        Comments result =
                commentService.getComments(AD_ID);

        assertEquals(2, result.getCount());

        assertEquals(
                List.of(firstDto, secondDto),
                result.getResults()
        );

        verify(commentRepository)
                .findAllByAdOrderByCreatedAtAsc(ad);
    }

    @Test
    @DisplayName("Добавление комментария сохраняет его и возвращает DTO")
    void addCommentShouldSaveAndReturnDto() {
        AdEntity ad = createAd();
        UserEntity author =
                createUser(OWNER_USERNAME);

        CreateOrUpdateComment request =
                createRequest("Новый комментарий");

        CommentEntity mappedComment =
                createCommentEntity(ad);

        CommentEntity savedComment =
                createCommentEntity(ad);

        Comment expectedDto = new Comment();

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(userRepository
                .findByUsernameIgnoreCase(
                        OWNER_USERNAME
                ))
                .thenReturn(Optional.of(author));

        when(commentMapper.toEntity(
                request,
                author,
                ad
        )).thenReturn(mappedComment);

        when(commentRepository.save(mappedComment))
                .thenReturn(savedComment);

        when(commentMapper.toDto(savedComment))
                .thenReturn(expectedDto);

        Comment result =
                commentService.addComment(
                        AD_ID,
                        OWNER_USERNAME,
                        request
                );

        assertSame(expectedDto, result);

        verify(commentMapper).toEntity(
                request,
                author,
                ad
        );

        verify(commentRepository)
                .save(mappedComment);
    }

    @Test
    @DisplayName("Владелец может изменить свой комментарий")
    void updateCommentShouldAllowOwner() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        CreateOrUpdateComment request =
                createRequest(
                        "Изменённый комментарий"
                );

        Comment expectedDto = new Comment();

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        when(commentRepository.save(comment))
                .thenReturn(comment);

        when(commentMapper.toDto(comment))
                .thenReturn(expectedDto);

        Comment result =
                commentService.updateComment(
                        AD_ID,
                        COMMENT_ID,
                        OWNER_USERNAME,
                        "USER",
                        request
                );

        assertSame(expectedDto, result);

        verify(commentMapper)
                .updateEntity(comment, request);

        verify(commentRepository)
                .save(comment);
    }

    @Test
    @DisplayName("Обычный пользователь не может изменить чужой комментарий")
    void updateCommentShouldDenyOtherUser() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        CreateOrUpdateComment request =
                createRequest(
                        "Попытка изменения"
                );

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        AccessDeniedException exception =
                assertThrows(
                        AccessDeniedException.class,
                        () ->
                                commentService.updateComment(
                                        AD_ID,
                                        COMMENT_ID,
                                        OTHER_USERNAME,
                                        "USER",
                                        request
                                )
                );

        assertEquals(
                "Недостаточно прав для изменения комментария",
                exception.getMessage()
        );

        verify(commentMapper, never())
                .updateEntity(any(), any());

        verify(commentRepository, never())
                .save(any());
    }

    @Test
    @DisplayName("Администратор может изменить чужой комментарий")
    void updateCommentShouldAllowAdmin() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        CreateOrUpdateComment request =
                createRequest(
                        "Изменено администратором"
                );

        Comment expectedDto = new Comment();

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        when(commentRepository.save(comment))
                .thenReturn(comment);

        when(commentMapper.toDto(comment))
                .thenReturn(expectedDto);

        Comment result =
                commentService.updateComment(
                        AD_ID,
                        COMMENT_ID,
                        OTHER_USERNAME,
                        "ADMIN",
                        request
                );

        assertSame(expectedDto, result);

        verify(commentMapper)
                .updateEntity(comment, request);

        verify(commentRepository)
                .save(comment);
    }

    @Test
    @DisplayName("Владелец может удалить свой комментарий")
    void deleteCommentShouldAllowOwner() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        commentService.deleteComment(
                AD_ID,
                COMMENT_ID,
                OWNER_USERNAME,
                "USER"
        );

        verify(commentRepository)
                .delete(comment);
    }

    @Test
    @DisplayName("Обычный пользователь не может удалить чужой комментарий")
    void deleteCommentShouldDenyOtherUser() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        assertThrows(
                AccessDeniedException.class,
                () ->
                        commentService.deleteComment(
                                AD_ID,
                                COMMENT_ID,
                                OTHER_USERNAME,
                                "USER"
                        )
        );

        verify(commentRepository, never())
                .delete(any());
    }

    @Test
    @DisplayName("Администратор может удалить чужой комментарий")
    void deleteCommentShouldAllowAdmin() {
        AdEntity ad = createAd();

        CommentEntity comment =
                createCommentEntity(ad);

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.of(comment));

        commentService.deleteComment(
                AD_ID,
                COMMENT_ID,
                OTHER_USERNAME,
                "ADMIN"
        );

        verify(commentRepository)
                .delete(comment);
    }

    @Test
    @DisplayName("Если объявление не найдено, выбрасывается исключение")
    void getCommentsShouldThrowWhenAdDoesNotExist() {
        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () ->
                                commentService.getComments(
                                        AD_ID
                                )
                );

        assertEquals(
                "Объявление с id 1 не найдено",
                exception.getMessage()
        );

        verify(commentRepository, never())
                .findAllByAdOrderByCreatedAtAsc(any());
    }

    @Test
    @DisplayName("Если комментарий не найден в объявлении, выбрасывается исключение")
    void updateCommentShouldThrowWhenCommentDoesNotExist() {
        AdEntity ad = createAd();

        CreateOrUpdateComment request =
                createRequest(
                        "Изменённый комментарий"
                );

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(commentRepository.findByIdAndAd(
                COMMENT_ID,
                ad
        )).thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () ->
                                commentService.updateComment(
                                        AD_ID,
                                        COMMENT_ID,
                                        OWNER_USERNAME,
                                        "USER",
                                        request
                                )
                );

        assertEquals(
                "Комментарий с id 10 не найден в объявлении 1",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Если автор комментария не найден, комментарий не создаётся")
    void addCommentShouldThrowWhenUserDoesNotExist() {
        AdEntity ad = createAd();

        CreateOrUpdateComment request =
                createRequest(
                        "Новый комментарий"
                );

        when(adRepository.findById(AD_ID))
                .thenReturn(Optional.of(ad));

        when(userRepository
                .findByUsernameIgnoreCase(
                        OWNER_USERNAME
                ))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () ->
                                commentService.addComment(
                                        AD_ID,
                                        OWNER_USERNAME,
                                        request
                                )
                );

        assertEquals(
                "Пользователь owner@mail.ru не найден",
                exception.getMessage()
        );

        verify(commentRepository, never())
                .save(any());
    }

    private AdEntity createAd() {
        AdEntity ad = new AdEntity();
        ad.setId(AD_ID);

        return ad;
    }

    private UserEntity createUser(
            String username
    ) {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername(username);

        return user;
    }

    private CommentEntity createCommentEntity(
            AdEntity ad
    ) {
        CommentEntity comment =
                new CommentEntity();

        comment.setId(COMMENT_ID);
        comment.setText("Исходный комментарий");
        comment.setAd(ad);
        comment.setAuthor(
                createUser(OWNER_USERNAME)
        );

        return comment;
    }

    private CreateOrUpdateComment createRequest(
            String text
    ) {
        CreateOrUpdateComment request =
                new CreateOrUpdateComment();

        request.setText(text);

        return request;
    }
}
