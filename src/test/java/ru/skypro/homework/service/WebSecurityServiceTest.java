package ru.skypro.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.CommentRepository;
import ru.skypro.homework.service.impl.WebSecurityServiceImpl;
import ru.skypro.homework.util.exceptions.NotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class WebSecurityServiceTest {
    @Mock
    AdRepository adRepository;
    @Mock
    CommentRepository commentRepository;
    @InjectMocks
    WebSecurityServiceImpl webSecurityService;


    @Test
    void allowAccessInAd(){
        AdEntity ad = new AdEntity();
        UserEntity user = new UserEntity();
        user.setLogin("test");
        ad.setAuthor(user);
        when(adRepository.findById(anyInt())).thenReturn(Optional.of(ad));
        Assertions.assertTrue(webSecurityService.canAccessInAd(2, "test"));
    }

    @Test
    void deniedAccessInAd(){
        AdEntity ad = new AdEntity();
        UserEntity user = new UserEntity();
        user.setLogin("test");
        ad.setAuthor(user);
        when(adRepository.findById(anyInt())).thenReturn(Optional.of(ad));
        Assertions.assertFalse(webSecurityService.canAccessInAd(2, "tust"));
    }

    @Test
    void notFoundAdInWebService(){
        when(adRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,() -> {
            webSecurityService.canAccessInAd(2, "tututu");
        });
    }

    @Test
    void allowAccessInComment(){
        CommentEntity comment = new CommentEntity();
        UserEntity user = new UserEntity();
        user.setLogin("test");
        comment.setAuthor(user);
        when(commentRepository.findById(anyInt())).thenReturn(Optional.of(comment));
        Assertions.assertTrue(webSecurityService.canAccessInComment(2, "test"));
    }

    @Test
    void deniedAccessInComment(){
        CommentEntity comment = new CommentEntity();
        UserEntity user = new UserEntity();
        user.setLogin("test");
        comment.setAuthor(user);
        when(commentRepository.findById(anyInt())).thenReturn(Optional.of(comment));
        Assertions.assertFalse(webSecurityService.canAccessInComment(2, "tust"));
    }

    @Test
    void notFoundCommentInWebService(){
        when(commentRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,() -> {
            webSecurityService.canAccessInComment(2, "tututu");
        });
    }
}
