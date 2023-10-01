package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.service.CommentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    CommentService commentService;

    @InjectMocks
    CommentController controller;

    //название тестируемого метода_блок проверяемого метода_ожидаемое поведение метода
    @Test
    void receivingAdComments() {
        //given
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(1);
        commentDTO.setAuthorImage("TestImage");
        commentDTO.setAuthorFirstName("TestFirstName");
        commentDTO.setCreatedAt(123L);
        commentDTO.setPk(12);
        commentDTO.setText("TestText");

        CommentsDTO commentsDTO = new CommentsDTO(1, java.util.List.of(commentDTO));
        System.out.println(commentsDTO);

        //должен быть возвращен / когда commentService
//        doReturn(commentsDTO).when(commentService.receivingAdComments(1));
        when(commentService.receivingAdComments(1)).thenReturn(commentsDTO);



        //вызов тестируемого метода
        //when
        var responseEntity = this.controller.receivingAdComments(1);;

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(commentsDTO, responseEntity.getBody());
    }

    @Test
    void addComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void updateComment() {
    }
}