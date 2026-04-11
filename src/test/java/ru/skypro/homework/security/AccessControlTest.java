package ru.skypro.homework.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Тесты проверки запрета на редактирование чужих ресурсов")
class AccessControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvertisementRepository advertisementRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    @WithMockUser(username = "otheruser@example.com")
    @DisplayName("DELETE /ads/{id} - запрет на удаление чужого объявления")
    void deleteAd_NotOwner_ReturnsForbidden() throws Exception {
        UserEntity owner = new UserEntity();
        owner.setEmail("owner@example.com");
        owner.setId(1);

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(1);
        ad.setAuthor(owner);

        when(advertisementRepository.findById(1)).thenReturn(Optional.of(ad));

        mockMvc.perform(delete("/ads/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "otheruser@example.com")
    @DisplayName("PATCH /ads/{id} - запрет на редактирование чужого объявления")
    void updateAd_NotOwner_ReturnsForbidden() throws Exception {
        UserEntity owner = new UserEntity();
        owner.setEmail("owner@example.com");
        owner.setId(1);

        AdvertisementEntity ad = new AdvertisementEntity();
        ad.setId(1);
        ad.setAuthor(owner);

        when(advertisementRepository.findById(1)).thenReturn(Optional.of(ad));

        String updateRequest = "{\"title\":\"New Title\",\"price\":2000,\"description\":\"New Desc\"}";

        mockMvc.perform(patch("/ads/1")
                        .contentType("application/json")
                        .content(updateRequest))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "otheruser@example.com")
    @DisplayName("PATCH /ads/{id}/comments/{commentId} - запрет на редактирование чужого комментария")
    void updateComment_NotOwner_ReturnsForbidden() throws Exception {
        UserEntity owner = new UserEntity();
        owner.setEmail("owner@example.com");
        owner.setId(1);

        CommentEntity comment = new CommentEntity();
        comment.setId(1);
        comment.setAuthor(owner);

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        String updateRequest = "{\"text\":\"Updated comment\"}";

        mockMvc.perform(patch("/ads/1/comments/1")
                        .contentType("application/json")
                        .content(updateRequest))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "otheruser@example.com")
    @DisplayName("DELETE /ads/{id}/comments/{commentId} - запрет на удаление чужого комментария")
    void deleteComment_NotOwner_ReturnsForbidden() throws Exception {
        UserEntity owner = new UserEntity();
        owner.setEmail("owner@example.com");
        owner.setId(1);

        CommentEntity comment = new CommentEntity();
        comment.setId(1);
        comment.setAuthor(owner);

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        mockMvc.perform(delete("/ads/1/comments/1"))
                .andExpect(status().isForbidden());
    }
}