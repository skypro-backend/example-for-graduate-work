package ru.skypro.homework.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AdsServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AdsController.class)
class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private CommentMapperImpl commentMapper;
    @SpyBean
    private AdsServiceImpl adsService;
    @MockBean
    private AdsOtherMapper adsOtherMapper;
    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private ImageMapper imageMapper;
    @MockBean
    private AdsRepository adsRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AdMapper adMapper;
    @InjectMocks
    private AdsController adsController;

    @Test
    void updateComments() throws Exception {
        int adPk = 3;
        int id = 2;
        String url = "/ads/" + adPk + "/comments/" + id;

        JSONObject commentObject = new JSONObject();
        commentObject.put("author", 1);
        commentObject.put("createdAt", "23-02-2022 08:09:10");
        commentObject.put("pk", 2);
        commentObject.put("text", "text");

        CommentEntity savedCommentEntity = getComment();
        savedCommentEntity.setText("text");
        savedCommentEntity.setAuthor(getNewAuthor());
        savedCommentEntity.setCreatedAt(LocalDateTime.of(2022,2, 23, 8,9, 10));
        savedCommentEntity.setId(2);
        savedCommentEntity.setText("text");
        when(commentRepository.findByIdAndAd_Id(id, adPk)).thenReturn(Optional.of(getComment()));
        when(userRepository.findById(1)).thenReturn(Optional.of(getNewAuthor()));
        when(commentRepository.save(savedCommentEntity)).thenReturn(savedCommentEntity);


        mockMvc.perform(MockMvcRequestBuilders.patch(
                                url)
                        .content(commentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(1))
                .andExpect(jsonPath("$.createdAt").value("23-02-2022 08:09:10"))
                .andExpect(jsonPath("$.pk").value(2))
                .andExpect(jsonPath("$.text").value("text"));

    }

    @Test
    void updateCommentNotFoundComment() throws Exception {
        int adPk = 3;
        int id = 2;
        String url = "/ads/" + adPk + "/comments/" + id;

        JSONObject commentObject = new JSONObject();
        commentObject.put("author", 1);
        commentObject.put("createdAt", "23-02-2022 08:09:10");
        commentObject.put("pk", 2);
        commentObject.put("text", "text");

        when(commentRepository.findByIdAndAd_Id(id, adPk)).thenThrow(ElemNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.patch(
                                url)
                        .content(commentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateCommentNotFoundAuthor() throws Exception {
        int adPk = 3;
        int id = 2;
        String url = "/ads/" + adPk + "/comments/" + id;

        JSONObject commentObject = new JSONObject();
        commentObject.put("author", 1);
        commentObject.put("createdAt", "23-02-2022 08:09:10");
        commentObject.put("pk", 2);
        commentObject.put("text", "text");

        when(commentRepository.findByIdAndAd_Id(id, adPk)).thenReturn(Optional.of(getComment()));
        when(userRepository.findById(1)).thenThrow(ElemNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.patch(
                                url)
                        .content(commentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateAds() {
    }

    private CommentEntity getComment() {
        CommentEntity comment = new CommentEntity();
        comment.setId(2);
        comment.setAd(getAdEntity());
        comment.setAuthor(getCurrentAuthor());
        comment.setCreatedAt(LocalDateTime.of(2023,02,10, 10,10,10));
        comment.setText("текст");
        return comment;
    }

    private AdEntity getAdEntity() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(3);
        return adEntity;
    }

    private UserEntity getCurrentAuthor() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(5);
        return userEntity;
    }

    private UserEntity getNewAuthor() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        return userEntity;
    }
}