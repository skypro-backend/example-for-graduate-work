package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AdController.class})
@ExtendWith(SpringExtension.class)
class AdControllerTest {
    @Autowired
    private AdController adController;

    @MockBean
    private AdService adService;

    /**
     * Method under test:  {@link AdController#getAdInfo(long)}
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
     * Method under test:  {@link AdController#getAdInfo(long)}
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
     * Method under test: {@link AdController#removeAd(Long)}
     */
    @Test
    void testRemoveAd() throws UnsupportedEncodingException {

        CommentRepository commentRepository = mock(CommentRepository.class);
        doNothing().when(commentRepository).deleteAllByAd_Id(Mockito.<Long>any());
        ImageRepository imageRepository = mock(ImageRepository.class);
        doNothing().when(imageRepository).deleteById(Mockito.<Long>any());
        ImageServiceImpl imageService = new ImageServiceImpl(imageRepository);

        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");

        User author = new User();
        author.setAds(new ArrayList<>());
        author.setComments(new ArrayList<>());
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage(image);
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Image image2 = new Image();
        image2.setData("AXAXAXAX".getBytes("UTF-8"));
        image2.setFileSize(3L);
        image2.setId(1L);
        image2.setMediaType("Media Type");

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setComments(new ArrayList<>());
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage(image2);
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        AdRepository adRepository = mock(AdRepository.class);
        doNothing().when(adRepository).deleteById(Mockito.<Long>any());
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<Void> actualRemoveAdResult = (new AdController(
                new AdServiceImpl(commentRepository, mock(UserRepository.class), imageService, adRepository))).removeAd(1L);
        verify(adRepository).deleteById(Mockito.<Long>any());
        verify(imageRepository).deleteById(Mockito.<Long>any());
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).deleteAllByAd_Id(Mockito.<Long>any());
        assertNull(actualRemoveAdResult.getBody());
        assertEquals(HttpStatus.OK, actualRemoveAdResult.getStatusCode());
        assertTrue(actualRemoveAdResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:  {@link AdController#getComments(Long)}
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
     * Method under test:  {@link AdController#addAd(CreateOrUpdateAdDTO, MultipartFile)}
     */
    @Test
    void testAddAd() throws Exception {
        when(adService.getAllAds()).thenReturn(new AdsDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ads");
        MockHttpServletRequestBuilder paramResult = getResult.param("createOrUpdateAdDTO",
                String.valueOf(new CreateOrUpdateAdDTO()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("image",
                String.valueOf(new MockMultipartFile("Name", (InputStream) null)));
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":null,\"results\":null}"));
    }

    /**
     * Method under test:  {@link AdController#addComment(Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testAddComment() throws Exception {
        when(adService.addComment(Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());

        CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
        createOrUpdateCommentDTO.setText("Text");
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
     * Method under test: {@link AdController#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:  {@link AdController#getAds()}
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
     * Method under test:  {@link AdController#getAdsMe()}
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
     * Method under test:  {@link AdController#updateAd(Long, CreateOrUpdateAdDTO)}
     */
    @Test
    void testUpdateAd() throws Exception {
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
     * Method under test:  {@link AdController#updateComment(Long, Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testUpdateComment() throws Exception {
        when(adService.patchComment(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<CreateOrUpdateCommentDTO>any()))
                .thenReturn(new CommentDTO());

        CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
        createOrUpdateCommentDTO.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(createOrUpdateCommentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/ads/{adId}/comments/{commentId}", 1L, 1L)
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
}
