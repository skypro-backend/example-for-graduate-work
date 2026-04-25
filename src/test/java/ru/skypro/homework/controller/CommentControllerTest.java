package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comments comments;
    private Comment comment;
    private CreateOrUpdateComment createOrUpdateComment;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setPk(1);
        comment.setAuthor(1);
        comment.setText("Great ad!");
        comment.setAuthorFirstName("Ivan");
        comment.setAuthorImage("/users/1/image");
        comment.setCreatedAt(System.currentTimeMillis());

        comments = new Comments();
        comments.setCount(1);
        comments.setResults(List.of(comment));

        createOrUpdateComment = new CreateOrUpdateComment();
        createOrUpdateComment.setText("Updated text");
    }

    @Test
    @WithMockUser
    void getComments_AdExists_ReturnsComments() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);

        mockMvc.perform(get("/ads/1/comments").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.count").value(1)).andExpect(jsonPath("$.results[0].text").value("Great ad!"));

        verify(commentService, times(1)).getComments(1);
    }

    @Test
    void getComments_AdNotFound_ReturnsNotFound() throws Exception {
        when(commentService.getComments(999)).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/ads/999/comments")).andExpect(status().isNotFound());

        verify(commentService, times(1)).getComments(999);
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void addComment_ValidData_ReturnsCreatedComment() throws Exception {
        when(commentService.addComment(eq(1), any(Comment.class), eq("user@test.com"))).thenReturn(comment);

        mockMvc.perform(post("/ads/1/comments").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"New comment\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.text").value("Great ad!"));
    }

    @Test
    void addComment_UnauthenticatedUser_ReturnsUnauthorized() throws Exception {
        mockMvc.perform(post("/ads/1/comments").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"No auth\"}")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void addComment_TextTooShort_ReturnsBadRequest() throws Exception {
        String invalidJson = "{\"text\": \"Hi\"}";

        mockMvc.perform(post("/ads/1/comments").contentType(MediaType.APPLICATION_JSON).content(invalidJson)).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void deleteComment_ByAuthor_Success() throws Exception {
        // Мокаем проверку через SpEL: @commentService.getComments(...)
        when(commentService.getComments(1)).thenReturn(comments);

        doNothing().when(commentService).deleteComment(1, 1, "user@test.com");

        mockMvc.perform(delete("/ads/1/comments/1")).andExpect(status().isNoContent());

        verify(commentService).deleteComment(1, 1, "user@test.com");
    }

    @Test
    @WithMockUser(username = "admin@test.com", roles = "ADMIN")
    void deleteComment_ByAdmin_Success() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);
        doNothing().when(commentService).deleteComment(1, 1, "admin@test.com");

        mockMvc.perform(delete("/ads/1/comments/1")).andExpect(status().isNoContent());

        verify(commentService).deleteComment(1, 1, "admin@test.com");
    }

    @Test
    @WithMockUser(username = "otheruser@test.com")
    void deleteComment_ByOtherUser_ReturnsForbidden() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);

        mockMvc.perform(delete("/ads/1/comments/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateComment_ByAuthor_Success() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);
        when(commentService.updateComment(eq(1), eq(1), any(Comment.class), eq("user@test.com"))).thenReturn(comment);

        mockMvc.perform(patch("/ads/1/comments/1").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"Updated text\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.text").value("Great ad!"));
    }

    @Test
    @WithMockUser(username = "admin@test.com", roles = "ADMIN")
    void updateComment_ByAdmin_Success() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);
        when(commentService.updateComment(eq(1), eq(1), any(Comment.class), eq("admin@test.com"))).thenReturn(comment);

        mockMvc.perform(patch("/ads/1/comments/1").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"Admin edit\"}")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "otheruser@test.com")
    void updateComment_ByOtherUser_ReturnsForbidden() throws Exception {
        when(commentService.getComments(1)).thenReturn(comments);

        mockMvc.perform(patch("/ads/1/comments/1").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"Hacked!\"}")).andExpect(status().isForbidden());
    }

    @Test
    void updateComment_Unauthenticated_ReturnsUnauthorized() throws Exception {
        mockMvc.perform(patch("/ads/1/comments/1").contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"No auth\"}")).andExpect(status().isUnauthorized());
    }
}