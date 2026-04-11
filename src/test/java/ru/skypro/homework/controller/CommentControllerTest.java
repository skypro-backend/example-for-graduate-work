package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.security.TestSecurityConfig;
import ru.skypro.homework.service.CommentService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@Import(TestSecurityConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Тесты контроллера комментариев")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("GET /ads/{id}/comments - получение комментариев объявления")
    void getComments_ReturnsOk() throws Exception {
        Comments comments = Comments.builder()
                .count(0)
                .results(Collections.emptyList())
                .build();

        when(commentService.getCommentsByAdId(1)).thenReturn(comments);

        mockMvc.perform(get("/ads/1/comments"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@example.com")
    @DisplayName("POST /ads/{id}/comments - создание комментария авторизованным пользователем")
    void addComment_ByAuthorizedUser_ReturnsOk() throws Exception {
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();
        commentRequest.setText("Test comment");

        Comment createdComment = new Comment();
        createdComment.setPk(1);
        createdComment.setText("Test comment");

        when(commentService.addComment(eq(1), any(CreateOrUpdateComment.class), anyString()))
                .thenReturn(createdComment);

        mockMvc.perform(post("/ads/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "owner@example.com")
    @DisplayName("PATCH /ads/{id}/comments/{commentId} - обновление своего комментария")
    void updateComment_ByOwner_ReturnsOk() throws Exception {
        CreateOrUpdateComment commentRequest = new CreateOrUpdateComment();
        commentRequest.setText("Updated comment");

        Comment updatedComment = new Comment();
        updatedComment.setPk(1);
        updatedComment.setText("Updated comment");

        when(commentService.updateComment(eq(1), eq(1), any(CreateOrUpdateComment.class)))
                .thenReturn(updatedComment);

        mockMvc.perform(patch("/ads/1/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "owner@example.com")
    @DisplayName("DELETE /ads/{id}/comments/{commentId} - удаление своего комментария")
    void deleteComment_ByOwner_ReturnsOk() throws Exception {
        doNothing().when(commentService).deleteComment(1, 1);

        mockMvc.perform(delete("/ads/1/comments/1"))
                .andExpect(status().isOk());
    }
}