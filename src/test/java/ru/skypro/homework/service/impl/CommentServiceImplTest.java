package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class CommentServiceImplTest {


    private CommentRepository commentRepository;
    private AdRepository adRepository;
    private UserRepository userRepository;
    private CommentMapper commentMapper;

    private CommentServiceImpl commentService;



    @BeforeEach
    void setUp() {


        commentRepository = mock(CommentRepository.class);
        adRepository = mock(AdRepository.class);
        userRepository = mock(UserRepository.class);
        commentMapper = mock(CommentMapper.class);



        commentService =
                new CommentServiceImpl(
                        commentRepository,
                        adRepository,
                        userRepository,
                        commentMapper
                );



        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                "user@mail.ru",
                                null
                        )
                );
    }



    @AfterEach
    void clearContext() {

        SecurityContextHolder.clearContext();
    }





    @Test
    void shouldAddComment() {


        User user = new User();

        user.setEmail(
                "user@mail.ru"
        );


        Ad ad = new Ad();

        ad.setId(1);



        Comment comment = new Comment();



        ru.skypro.homework.dto.Comment dto =
                new ru.skypro.homework.dto.Comment();



        when(adRepository.findById(1))
                .thenReturn(
                        Optional.of(ad)
                );


        when(userRepository.findByEmail(
                "user@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );



        when(commentMapper.toEntity(
                any(CreateOrUpdateComment.class)
        ))
                .thenReturn(comment);



        when(commentRepository.save(comment))
                .thenReturn(comment);



        when(commentMapper.toDto(comment))
                .thenReturn(dto);



        CreateOrUpdateComment createDto =
                new CreateOrUpdateComment();



        ru.skypro.homework.dto.Comment result =
                commentService.addComment(
                        1,
                        createDto
                );



        assertThat(result)
                .isEqualTo(dto);



        assertThat(comment.getAuthor())
                .isEqualTo(user);


        assertThat(comment.getAd())
                .isEqualTo(ad);



        verify(commentRepository)
                .save(comment);
    }







    @Test
    void shouldDeleteCommentByOwner() {


        User user = new User();

        user.setEmail(
                "user@mail.ru"
        );


        Comment comment = new Comment();

        comment.setAuthor(user);



        when(commentRepository.findById(1))
                .thenReturn(
                        Optional.of(comment)
                );



        commentService.deleteComment(
                1,
                1
        );



        verify(commentRepository)
                .delete(comment);
    }








    @Test
    void shouldUpdateCommentByOwner() {


        User user = new User();

        user.setEmail(
                "user@mail.ru"
        );


        Comment comment = new Comment();

        comment.setAuthor(user);



        when(commentRepository.findById(1))
                .thenReturn(
                        Optional.of(comment)
                );


        when(commentRepository.save(comment))
                .thenReturn(comment);



        ru.skypro.homework.dto.Comment dto =
                new ru.skypro.homework.dto.Comment();



        when(commentMapper.toDto(comment))
                .thenReturn(dto);



        CreateOrUpdateComment updateDto =
                new CreateOrUpdateComment();



        ru.skypro.homework.dto.Comment result =
                commentService.updateComment(
                        1,
                        1,
                        updateDto
                );



        assertThat(result)
                .isEqualTo(dto);



        verify(commentMapper)
                .updateCommentFromDto(
                        updateDto,
                        comment
                );


        verify(commentRepository)
                .save(comment);
    }

}