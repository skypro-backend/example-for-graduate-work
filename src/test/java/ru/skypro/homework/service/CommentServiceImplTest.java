package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private AdRepository adRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl out;
    User user = new User(1, "Bob", "Mjn", "name", "password", "2332343212", "USER", true, "gdhfhfhdh");
    Ad ad = new Ad();

    Comment comment = new Comment(user, ad, user.getImage(),
            "Bob", Instant.now(), "Коммент");
    List<Comment> list = new ArrayList<>();



    @Test
    void getAllCommentsForAdById() {
        list.add(comment);
        when(commentRepository.findCommentsByAd_Pk(ad.getPk())).thenReturn(Optional.of(list));
        List<CommentDto> commDto = new CommentsDto().fromCommentsList(list);
        assertEquals(out.getAllCommentsForAdById(ad.getPk()).getResults(), commDto);
    }

    @Test
    void ShouldBeNotEqualsWhenCreateNewCommentBecauseOfTimeOfCreation() {
        ad.setPk(1);
        ad.setUser(user);
        Comment comment1 = new Comment(user, ad, user.getImage(),
                "Bob", Instant.now(), "Blala");
        CreateOrUpdateCommentDto crOrUpdCommDto = new CreateOrUpdateCommentDto("Blala");
        when(adRepository.findByPk(ad.getPk())).thenReturn(Optional.of(ad));
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(null);
        assertNotEquals(out.createNewComment(1,crOrUpdCommDto, "name"), CommentDto.fromComment(comment1));
    }
}
