package ru.skypro.homework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @MockBean
    private AdService adService;

    @Autowired
    private CommentController commentController;

    /**
     * Method under test:  {@link CommentController#getComments(Long)}
     */
    @Test
    void testGetComments() throws Exception {
        when(adService.getComments(Mockito.<Long>any())).thenReturn(new CommentsDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comments/{adId}/comments", 1L);
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test:  {@link CommentController#addComments(Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testAddComments() throws Exception {
        when(adService.addComment(Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());

        CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
        createOrUpdateCommentDTO.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(createOrUpdateCommentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comments/{id}/comments", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"author\":null,\"authorImage\":null,\"authorFirstName\":null,\"createdAt\":null,\"pk\":null,\"text\":null}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.test.web.servlet.RequestBuilder.buildRequest(javax.servlet.ServletContext)" because "requestBuilder" is null
        //   See https://diff.blue/R013 to resolve this issue.

        CommentRepository commentRepository = mock(CommentRepository.class);
        doNothing().when(commentRepository).deleteById(Mockito.<Long>any());
        UserRepository userRepository = mock(UserRepository.class);
        ResponseEntity<Void> actualDeleteCommentsResult = (new CommentController(new AdServiceImpl(commentRepository,
                userRepository, new ImageServiceImpl(mock(ImageRepository.class)), mock(AdRepository.class))))
                .deleteComments(1L, 1L);
        verify(commentRepository).deleteById(Mockito.<Long>any());
        assertNull(actualDeleteCommentsResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteCommentsResult.getStatusCode());
        assertTrue(actualDeleteCommentsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:  {@link CommentController#updateComments(Long, Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testUpdateComments() throws Exception {
        when(adService.patchComment(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/comments/{adId}/comments/{commentId}",
                1L, 1L);
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"author\":null,\"authorImage\":null,\"authorFirstName\":null,\"createdAt\":null,\"pk\":null,\"text\":null}"));
    }
}
