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
import ru.skypro.homework.model.*;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.PhotoAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
    void testRemoveAd() {

        CommentRepository commentRepository = mock(CommentRepository.class);
        doNothing().when(commentRepository).deleteAllByAd_Id(Mockito.<Long>any());
        PhotoAdRepository photoAdRepository = mock(PhotoAdRepository.class);
        doNothing().when(photoAdRepository).deleteById(Mockito.<Long>any());

        User author = new User();
        author.setAds(new ArrayList<>());
        author.setComments(new ArrayList<>());
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        User author2 = new User();
        author2.setAds(new ArrayList<>());
        author2.setComments(new ArrayList<>());
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author2);
        ad.setComments(new ArrayList<>());
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");

        PhotoAd photoAd = new PhotoAd();
        photoAd.setAd(ad);
        photoAd.setFilePath("/directory/foo.txt");
        photoAd.setFileSize(3L);
        photoAd.setId(1L);
        photoAd.setMediaType("Media Type");
        Ad ad2 = mock(Ad.class);
        when(ad2.getPhotoAd()).thenReturn(photoAd);
        doNothing().when(ad2).setAuthor(Mockito.<User>any());
        doNothing().when(ad2).setComments(Mockito.<List<Comment>>any());
        doNothing().when(ad2).setDescription(Mockito.<String>any());
        doNothing().when(ad2).setId(Mockito.<Long>any());
        doNothing().when(ad2).setImage(Mockito.<String>any());
        doNothing().when(ad2).setPrice(Mockito.<Integer>any());
        doNothing().when(ad2).setTitle(Mockito.<String>any());
        ad2.setAuthor(author);
        ad2.setComments(new ArrayList<>());
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad2);
        AdRepository adRepository = mock(AdRepository.class);
        doNothing().when(adRepository).deleteById(Mockito.<Long>any());
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<Void> actualRemoveAdResult = (new AdController(
                new AdServiceImpl(commentRepository, mock(UserRepository.class), photoAdRepository, adRepository)))
                .removeAd(1L);
        verify(adRepository).deleteById(Mockito.<Long>any());
        verify(photoAdRepository).deleteById(Mockito.<Long>any());
        verify(adRepository).findById(Mockito.<Long>any());
        verify(ad2).getPhotoAd();
        verify(ad2).setAuthor(Mockito.<User>any());
        verify(ad2).setComments(Mockito.<List<Comment>>any());
        verify(ad2).setDescription(Mockito.<String>any());
        verify(ad2).setId(Mockito.<Long>any());
        verify(ad2).setImage(Mockito.<String>any());
        verify(ad2).setPrice(Mockito.<Integer>any());
        verify(ad2).setTitle(Mockito.<String>any());
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
     * Method under test:  {@link AdController#addComments(Long, CreateOrUpdateCommentDTO)}
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
