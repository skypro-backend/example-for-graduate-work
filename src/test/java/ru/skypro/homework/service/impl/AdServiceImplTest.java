package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

@ContextConfiguration(classes = {AdServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdServiceImplTest {
    @MockBean
    private AdRepository adRepository;

    @Autowired
    private AdServiceImpl adServiceImpl;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AdServiceImpl#getAllAds()}
     */
    @Test
    void testGetAllAds() {
        when(adRepository.findAll()).thenThrow(new AdNotFoundException("An error occurred"));
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.getAllAds());
        verify(adRepository).findAll();
    }

    /**
     * Method under test: {@link AdServiceImpl#addAd(CreateOrUpdateAdDTO, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddAd() throws IOException {
        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        adServiceImpl.addAd(createOrUpdateAdDTO,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link AdServiceImpl#getAdInfo(Long)}
     */
    @Test
    void testGetAdInfo() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage("Image");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult2 = Optional.of(ad);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        ExtendedAdDTO actualAdInfo = adServiceImpl.getAdInfo(1L);
        verify(adRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
        assertEquals("6625550144", actualAdInfo.getPhone());
        assertEquals("Doe", actualAdInfo.getAuthorLastName());
        assertEquals("Dr", actualAdInfo.getTitle());
        assertEquals("Image", actualAdInfo.getImage());
        assertEquals("Jane", actualAdInfo.getAuthorFirstName());
        assertEquals("The characteristics of someone or something", actualAdInfo.getDescription());
        assertEquals("jane.doe@example.org", actualAdInfo.getEmail());
        assertEquals(1, actualAdInfo.getPrice().intValue());
        assertEquals(1L, actualAdInfo.getPk().longValue());
    }

    /**
     * Method under test: {@link AdServiceImpl#getAdInfo(Long)}
     */
    @Test
    void testGetAdInfo2() {
        when(userRepository.findById(Mockito.<Long>any())).thenThrow(new AdNotFoundException("An error occurred"));

        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.getAdInfo(1L));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#deleteAd(Long)}
     */
    @Test
    void testDeleteAd() {
        doNothing().when(adRepository).deleteById(Mockito.<Long>any());
        Void actualDeleteAdResult = adServiceImpl.deleteAd(1L);
        verify(adRepository).deleteById(Mockito.<Long>any());
        assertNull(actualDeleteAdResult);
    }

    /**
     * Method under test: {@link AdServiceImpl#deleteAd(Long)}
     */
    @Test
    void testDeleteAd2() {
        doThrow(new AdNotFoundException("An error occurred")).when(adRepository).deleteById(Mockito.<Long>any());
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.deleteAd(1L));
        verify(adRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#patchAd(Long, CreateOrUpdateAdDTO)}
     */
    @Test
    void testPatchAd() {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);

        User author2 = new User();
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Ad ad2 = new Ad();
        ad2.setAuthor(author2);
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");
        when(adRepository.save(Mockito.<Ad>any())).thenReturn(ad2);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        AdDTO actualPatchAdResult = adServiceImpl.patchAd(1L, new CreateOrUpdateAdDTO());
        verify(adRepository).findById(Mockito.<Long>any());
        verify(adRepository).save(Mockito.<Ad>any());
        assertEquals("Dr", actualPatchAdResult.getTitle());
        assertEquals("Image", actualPatchAdResult.getImage());
        assertEquals(1, actualPatchAdResult.getPk().intValue());
        assertEquals(1, actualPatchAdResult.getPrice().intValue());
        assertEquals(1L, actualPatchAdResult.getAuthor().longValue());
    }

    /**
     * Method under test: {@link AdServiceImpl#patchAd(Long, CreateOrUpdateAdDTO)}
     */
    @Test
    void testPatchAd2() {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        when(adRepository.save(Mockito.<Ad>any())).thenThrow(new AdNotFoundException("An error occurred"));
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.patchAd(1L, new CreateOrUpdateAdDTO()));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(adRepository).save(Mockito.<Ad>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#getAllAdsByAuthor()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllAdsByAuthor() {

        adServiceImpl.getAllAdsByAuthor();
    }

    /**
     * Method under test: {@link AdServiceImpl#patchAdImage(Long, MultipartFile)}
     */
    @Test
    void testPatchAdImage() throws IOException {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);

        User author2 = new User();
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Ad ad2 = new Ad();
        ad2.setAuthor(author2);
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");
        when(adRepository.save(Mockito.<Ad>any())).thenReturn(ad2);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualPatchAdImageResult = adServiceImpl.patchAdImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(adRepository).save(Mockito.<Ad>any());
        assertEquals("Image", actualPatchAdImageResult);
    }

    /**
     * Method under test: {@link AdServiceImpl#patchAdImage(Long, MultipartFile)}
     */
    @Test
    void testPatchAdImage2() throws IOException {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        when(adRepository.save(Mockito.<Ad>any())).thenThrow(new AdNotFoundException("An error occurred"));
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.patchAdImage(1L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(adRepository).save(Mockito.<Ad>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#getComments(Long)}
     */
    @Test
    void testGetComments() {
        when(commentRepository.findAllByAd(Mockito.<Ad>any())).thenThrow(new AdNotFoundException("An error occurred"));

        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.getComments(1L));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).findAllByAd(Mockito.<Ad>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#addComment(Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testAddComment() {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");

        User author2 = new User();
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(author2);
        comment.setCreatedAt(1L);
        comment.setId(1L);
        comment.setText("Text");
        when(commentRepository.save(Mockito.<Comment>any())).thenReturn(comment);

        User author3 = new User();
        author3.setEmail("jane.doe@example.org");
        author3.setFirstName("Jane");
        author3.setId(1L);
        author3.setImage("Image");
        author3.setLastName("Doe");
        author3.setPassword("iloveyou");
        author3.setPhone("6625550144");
        author3.setRole(Role.USER);

        Ad ad2 = new Ad();
        ad2.setAuthor(author3);
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad2);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CommentDTO actualAddCommentResult = adServiceImpl.addComment(1L, new CreateOrUpdateCommentDTO());
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).save(Mockito.<Comment>any());
        assertEquals("Image", actualAddCommentResult.getAuthorImage());
        assertEquals("Jane", actualAddCommentResult.getAuthorFirstName());
        assertEquals("Text", actualAddCommentResult.getText());
        assertEquals(1L, actualAddCommentResult.getAuthor().longValue());
        assertEquals(1L, actualAddCommentResult.getCreatedAt().longValue());
        assertEquals(1L, actualAddCommentResult.getPk().longValue());
    }

    /**
     * Method under test: {@link AdServiceImpl#addComment(Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testAddComment2() {
        when(commentRepository.save(Mockito.<Comment>any())).thenThrow(new AdNotFoundException("An error occurred"));

        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");
        Optional<Ad> ofResult = Optional.of(ad);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.addComment(1L, new CreateOrUpdateCommentDTO()));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).save(Mockito.<Comment>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment() {
        doNothing().when(commentRepository).deleteById(Mockito.<Long>any());
        Void actualDeleteCommentResult = adServiceImpl.deleteComment(1L, 1L);
        verify(commentRepository).deleteById(Mockito.<Long>any());
        assertNull(actualDeleteCommentResult);
    }

    /**
     * Method under test: {@link AdServiceImpl#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment2() {
        doThrow(new AdNotFoundException("An error occurred")).when(commentRepository).deleteById(Mockito.<Long>any());
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.deleteComment(1L, 1L));
        verify(commentRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdServiceImpl#patchComment(Long, Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testPatchComment() {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");

        User author2 = new User();
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(author2);
        comment.setCreatedAt(1L);
        comment.setId(1L);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);

        User author3 = new User();
        author3.setEmail("jane.doe@example.org");
        author3.setFirstName("Jane");
        author3.setId(1L);
        author3.setImage("Image");
        author3.setLastName("Doe");
        author3.setPassword("iloveyou");
        author3.setPhone("6625550144");
        author3.setRole(Role.USER);

        Ad ad2 = new Ad();
        ad2.setAuthor(author3);
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");

        User author4 = new User();
        author4.setEmail("jane.doe@example.org");
        author4.setFirstName("Jane");
        author4.setId(1L);
        author4.setImage("Image");
        author4.setLastName("Doe");
        author4.setPassword("iloveyou");
        author4.setPhone("6625550144");
        author4.setRole(Role.USER);

        Comment comment2 = new Comment();
        comment2.setAd(ad2);
        comment2.setAuthor(author4);
        comment2.setCreatedAt(1L);
        comment2.setId(1L);
        comment2.setText("Text");
        when(commentRepository.save(Mockito.<Comment>any())).thenReturn(comment2);
        when(commentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User author5 = new User();
        author5.setEmail("jane.doe@example.org");
        author5.setFirstName("Jane");
        author5.setId(1L);
        author5.setImage("Image");
        author5.setLastName("Doe");
        author5.setPassword("iloveyou");
        author5.setPhone("6625550144");
        author5.setRole(Role.USER);

        Ad ad3 = new Ad();
        ad3.setAuthor(author5);
        ad3.setDescription("The characteristics of someone or something");
        ad3.setId(1L);
        ad3.setImage("Image");
        ad3.setPrice(1);
        ad3.setTitle("Dr");
        Optional<Ad> ofResult2 = Optional.of(ad3);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        CommentDTO actualPatchCommentResult = adServiceImpl.patchComment(1L, 1L, new CreateOrUpdateCommentDTO());
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).findById(Mockito.<Long>any());
        verify(commentRepository).save(Mockito.<Comment>any());
        assertEquals("Image", actualPatchCommentResult.getAuthorImage());
        assertEquals("Jane", actualPatchCommentResult.getAuthorFirstName());
        assertEquals("Text", actualPatchCommentResult.getText());
        assertEquals(1L, actualPatchCommentResult.getAuthor().longValue());
        assertEquals(1L, actualPatchCommentResult.getCreatedAt().longValue());
        assertEquals(1L, actualPatchCommentResult.getPk().longValue());
    }

    /**
     * Method under test: {@link AdServiceImpl#patchComment(Long, Long, CreateOrUpdateCommentDTO)}
     */
    @Test
    void testPatchComment2() {
        User author = new User();
        author.setEmail("jane.doe@example.org");
        author.setFirstName("Jane");
        author.setId(1L);
        author.setImage("Image");
        author.setLastName("Doe");
        author.setPassword("iloveyou");
        author.setPhone("6625550144");
        author.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setDescription("The characteristics of someone or something");
        ad.setId(1L);
        ad.setImage("Image");
        ad.setPrice(1);
        ad.setTitle("Dr");

        User author2 = new User();
        author2.setEmail("jane.doe@example.org");
        author2.setFirstName("Jane");
        author2.setId(1L);
        author2.setImage("Image");
        author2.setLastName("Doe");
        author2.setPassword("iloveyou");
        author2.setPhone("6625550144");
        author2.setRole(Role.USER);

        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(author2);
        comment.setCreatedAt(1L);
        comment.setId(1L);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        when(commentRepository.save(Mockito.<Comment>any())).thenThrow(new AdNotFoundException("An error occurred"));
        when(commentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User author3 = new User();
        author3.setEmail("jane.doe@example.org");
        author3.setFirstName("Jane");
        author3.setId(1L);
        author3.setImage("Image");
        author3.setLastName("Doe");
        author3.setPassword("iloveyou");
        author3.setPhone("6625550144");
        author3.setRole(Role.USER);

        Ad ad2 = new Ad();
        ad2.setAuthor(author3);
        ad2.setDescription("The characteristics of someone or something");
        ad2.setId(1L);
        ad2.setImage("Image");
        ad2.setPrice(1);
        ad2.setTitle("Dr");
        Optional<Ad> ofResult2 = Optional.of(ad2);
        when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(AdNotFoundException.class, () -> adServiceImpl.patchComment(1L, 1L, new CreateOrUpdateCommentDTO()));
        verify(adRepository).findById(Mockito.<Long>any());
        verify(commentRepository).findById(Mockito.<Long>any());
        verify(commentRepository).save(Mockito.<Comment>any());
    }
}
