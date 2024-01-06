package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.controller.CommentController;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebTestConfiguration.class)
@AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private CommentMapper commentMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CommentRepository commentRepository;
    @Autowired
    CommentServiceImpl commentService;
    @InjectMocks
    private CommentController commentController;

    private UserEntity userEntity;
    private AdEntity adEntity;
    private CommentEntity commentEntity;
    private Comment comment;
    @BeforeEach
    void init(){
        ImageEntity imageEntity = new ImageEntity(1, "path");
        userEntity = new UserEntity(1,"user@gmail.com","name","lname",null,"user", Role.USER,null,null,null);
        adEntity = new AdEntity(1,100,"title1","desc1", userEntity, imageEntity, null);
        commentEntity = new CommentEntity(1, Instant.now(), "text", userEntity, adEntity);
        comment = new Comment(1,"/image/1","name",Instant.now().toEpochMilli(),1,"text");
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void getCommentsForAdTest() throws Exception {
        when(adRepository.existsById(any(Integer.class))).thenReturn(true);
        when(commentRepository.findByAd_pk(any(Integer.class))).thenReturn(List.of(commentEntity));
        when(commentMapper.commentToCommentDTO(commentEntity)).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/{id}/comments", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void addCommentToAdTest() throws Exception {
        when(adRepository.findById(any(Integer.class))).thenReturn(Optional.of(adEntity));
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(commentMapper.commentToCommentDTO(any(CommentEntity.class))).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{id}/comments", 1)
                        .content(asJsonString(new CreateOrUpdateComment("text")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(comment.getAuthor()))
                .andExpect(jsonPath("$.authorImage").value(comment.getAuthorImage()))
                .andExpect(jsonPath("$.authorFirstName").value(comment.getAuthorFirstName()))
                .andExpect(jsonPath("$.createdAt").value(comment.getCreatedAt()))
                .andExpect(jsonPath("$.pk").value(comment.getPk()))
                .andExpect(jsonPath("$.text").value(comment.getText()));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void deleteCommentTest() throws Exception {
        when(commentRepository.findById(any(Integer.class))).thenReturn(Optional.of(commentEntity));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{adId}/comments/{commentId}", 1,1))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void updateCommentTest() throws Exception {
        Comment commentUpdated = new Comment(1, "/image/1", "name", Instant.now().toEpochMilli(), 1, "textUpdated");

        when(commentRepository.findById(any(Integer.class))).thenReturn(Optional.of(commentEntity));
        when(commentMapper.commentToCommentDTO(any(CommentEntity.class))).thenReturn(commentUpdated);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{adId}/comments/{commentId}", 1,1)
                        .content(asJsonString(new CreateOrUpdateComment("textUpdated")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(commentUpdated.getAuthor()))
                .andExpect(jsonPath("$.authorImage").value(commentUpdated.getAuthorImage()))
                .andExpect(jsonPath("$.authorFirstName").value(commentUpdated.getAuthorFirstName()))
                .andExpect(jsonPath("$.createdAt").value(commentUpdated.getCreatedAt()))
                .andExpect(jsonPath("$.pk").value(commentUpdated.getPk()))
                .andExpect(jsonPath("$.text").value(commentUpdated.getText()));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
