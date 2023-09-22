package ru.skypro.homework.service.comments.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.ads.out.ads.Ad;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.repository.comments.CommentsRepository;
import ru.skypro.homework.service.users.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentsServiceImplTest {
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private AdsRepository adsRepository;
    @Spy
    private CommentMapper commentMapper;
    @Mock
    private UserService userService;
    @InjectMocks
    private CommentsServiceImpl out;

    Ad testAd;
    Comment testComment;

    User testUser;
    Authentication auth;

    @BeforeEach
    public void init() {
        testUser = new User();
        testUser.setId(300);
        testUser.setUsername("test@test.com");
        auth = new UsernamePasswordAuthenticationToken(testUser, null);


        testAd = new Ad();
        testAd.setPk(100);
        testAd.setPrice(1000);
        testAd.setTitle("Test ad");

        testComment = new Comment();
        testComment.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1, 1));
        testComment.setText("Test comment");
        testComment.setPk(200);
        testComment.setAuthor(testUser);

        testAd.setComments(List.of(testComment));
    }

    @Test
    void shouldGetComments() {
        when(adsRepository.findByPkIs(any())).thenReturn(Optional.ofNullable(testAd));

        CommentsDto result = out.getComments(testAd.getPk());

        assertThat(result).isNotNull();
        assertThat(result.getCount()).isEqualTo(testAd.getComments().size());
    }

    @Test
    void shouldAddComment() {
        when(adsRepository.findByPkIs(any())).thenReturn(Optional.ofNullable(testAd));

        CommentsDto result = out.getComments(testAd.getPk());

        assertThat(result).isNotNull();
        assertThat(result.getCount()).isEqualTo(testAd.getComments().size());
    }

    @Test
    void deleteComment() {
        when(commentsRepository.findByAd_PkAndPk(anyInt(), anyInt())).thenReturn(Optional.of(testComment));

        out.deleteComment(testAd.getPk(), testComment.getPk());

        verify(commentsRepository, times(1)).findByAd_PkAndPk(anyInt(), anyInt());
        verify(commentsRepository, atMostOnce()).deleteById(testComment.getPk());
    }
}