package ru.skypro.homework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;

@ContextConfiguration(classes = {AdController.class})
@ExtendWith(SpringExtension.class)
class AdControllerTest {
    @Autowired
    private AdController adController;

    @MockBean
    private AdService adService;

    /**
     * Method under test: {@link AdController#getAdInfo(long)}
     */
    @Test
    void testGetAdInfo() throws Exception {
        when(adService.getAdInfo(Mockito.<Long>any())).thenReturn(new ExtendedAdDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{adId}", 1L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"pk\":null,\"authorFirstName\":null,\"authorLastName\":null,\"description\":null,\"email\":null,\"image\":null"
                                        + ",\"phone\":null,\"price\":null,\"title\":null}"));
    }

    /**
     * Method under test: {@link AdController#getAdInfo(long)}
     */
    @Test
    void testGetAdInfo2() throws Exception {
        when(adService.getAllAds()).thenReturn(new AdsDTO());
        when(adService.getAdInfo(Mockito.<Long>any())).thenReturn(new ExtendedAdDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{adId}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test: {@link AdController#removeAds(Long)}
     */
    @Test
    void testRemoveAds() {


        AdRepository adRepository = mock(AdRepository.class);
        doNothing().when(adRepository).deleteById(Mockito.<Long>any());
        ResponseEntity<Void> actualRemoveAdsResult = (new AdController(
                new AdServiceImpl(mock(CommentRepository.class), mock(UserRepository.class), adRepository))).removeAds(1L);
        verify(adRepository).deleteById(Mockito.<Long>any());
        assertEquals(HttpStatus.OK, actualRemoveAdsResult.getStatusCode());
    }

    /**
     * Method under test: {@link AdController#getComments(Long)}
     */
    @Test
    void testGetComments() throws Exception {
        when(adService.getComments(Mockito.<Long>any())).thenReturn(new CommentsDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{adId}/comments", 1L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test: {@link AdController#addAds(CreateOrUpdateAdDTO, MultipartFile)}
     */
    @Test
    void testAddAds() throws Exception {
        when(adService.getAllAds()).thenReturn(new AdsDTO());

        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        createOrUpdateAdDTO.description("The characteristics of someone or something");
        createOrUpdateAdDTO.price(1);
        createOrUpdateAdDTO.setDescription("The characteristics of someone or something");
        createOrUpdateAdDTO.setPrice(1);
        createOrUpdateAdDTO.setTitle("Dr");
        createOrUpdateAdDTO.title("Dr");
        String content = (new ObjectMapper()).writeValueAsString(createOrUpdateAdDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ads");
        MockHttpServletRequestBuilder requestBuilder = getResult
                .param("image", String.valueOf(new MockMultipartFile("Name", (InputStream) null)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test: {@link AdController#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments() {

        CommentRepository commentRepository = mock(CommentRepository.class);
        doNothing().when(commentRepository).deleteById(Mockito.<Long>any());
        ResponseEntity<Void> actualDeleteCommentsResult = (new AdController(
                new AdServiceImpl(commentRepository, mock(UserRepository.class), mock(AdRepository.class)))).deleteComments(1L,
                1L);
        verify(commentRepository).deleteById(Mockito.<Long>any());
        assertEquals(HttpStatus.OK, actualDeleteCommentsResult.getStatusCode());
    }

    /**
     * Method under test: {@link AdController#addComments(Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testAddComments() throws Exception {
        when(adService.addComment(Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());

        CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
        createOrUpdateCommentDTO.setText("Text");
        createOrUpdateCommentDTO.text("Text");
        String content = (new ObjectMapper()).writeValueAsString(createOrUpdateCommentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/{adId}/comments", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"author\":null,\"authorImage\":null,\"authorFirstName\":null,\"createdAt\":null,\"pk\":null,\"text\":null}"));
    }

    /**
     * Method under test: {@link AdController#getAds()}
     */
    @Test
    void testGetAds() throws Exception {
        when(adService.getAllAds()).thenReturn(new AdsDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test: {@link AdController#getAdsMe()}
     */
    @Test
    void testGetAdsMe() throws Exception {
        when(adService.getAllAdsByAuthor()).thenReturn(new AdsDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/me");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test: {@link AdController#updateAdImage(Long, MultipartFile)}
     */
    @Test
    void testUpdateAdImage() throws Exception {
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/ads/{adId}/image", 1L);
        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image",
                String.valueOf(new MockMultipartFile("Name", (InputStream) null)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    /**
     * Method under test: {@link AdController#updateAds(Long, CreateOrUpdateAdDTO)}
     */
    @Test
    void testUpdateAds() throws Exception {
        when(adService.patchAd(Mockito.<Long>any(), Mockito.<CreateOrUpdateAdDTO>any())).thenReturn(new AdDTO());

        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        createOrUpdateAdDTO.description("The characteristics of someone or something");
        createOrUpdateAdDTO.price(1);
        createOrUpdateAdDTO.setDescription("The characteristics of someone or something");
        createOrUpdateAdDTO.setPrice(1);
        createOrUpdateAdDTO.setTitle("Dr");
        createOrUpdateAdDTO.title("Dr");
        String content = (new ObjectMapper()).writeValueAsString(createOrUpdateAdDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/ads/{adId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"author\":null,\"image\":null,\"pk\":null,\"price\":null,\"title\":null}"));
    }

    /**
     * Method under test: {@link AdController#updateComments(Long, Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testUpdateComments() throws Exception {
        when(adService.patchComment(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/ads/{adId}/comments/{commentId}", 1L,
                1L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"author\":null,\"authorImage\":null,\"authorFirstName\":null,\"createdAt\":null,\"pk\":null,\"text\":null}"));
    }
}
