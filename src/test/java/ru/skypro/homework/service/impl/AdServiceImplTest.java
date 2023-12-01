package ru.skypro.homework.service.impl;

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
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.model.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
  private ImageService imageService;

  @MockBean
  private UserRepository userRepository;

  /**
   * Method under test: {@link AdServiceImpl#getAllAds()}
   */
  @Test
  void testGetAllAds() {
    ArrayList<Ad> adList = new ArrayList<>();
    when(adRepository.findAll()).thenReturn(adList);
    AdsDTO actualAllAds = adServiceImpl.getAllAds();
    verify(adRepository).findAll();
    assertEquals(0, actualAllAds.getCount().intValue());
    assertEquals(adList, actualAllAds.getResults());
  }

  /**
   * Method under test: {@link AdServiceImpl#getAllAds()}
   */
  @Test
  void testGetAllAds2() {
    Image image = new Image();
    image.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Использован метод сервиса: {}");

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
    image2.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Использован метод сервиса: {}");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image2);
    ad.setPrice(2);
    ad.setTitle("Dr");

    ArrayList<Ad> adList = new ArrayList<>();
    adList.add(ad);
    when(adRepository.findAll()).thenReturn(adList);
    AdsDTO actualAllAds = adServiceImpl.getAllAds();
    verify(adRepository).findAll();
    List<AdDTO> results = actualAllAds.getResults();
    AdDTO getResult = results.get(0);
    assertEquals("/image/1", getResult.getImage());
    assertEquals("Dr", getResult.getTitle());
    assertEquals(1, getResult.getPk().intValue());
    assertEquals(1, actualAllAds.getCount().intValue());
    assertEquals(1, results.size());
    assertEquals(1L, getResult.getAuthor().longValue());
    assertEquals(2, getResult.getPrice().intValue());
  }

  /**
   * Method under test: {@link AdServiceImpl#getAllAds()}
   */
  @Test
  void testGetAllAds3() {
    Image image = new Image();
    image.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Использован метод сервиса: {}");

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
    image2.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Использован метод сервиса: {}");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image2);
    ad.setPrice(2);
    ad.setTitle("Dr");

    Image image3 = new Image();
    image3.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image3.setFileSize(2L);
    image3.setId(2L);
    image3.setMediaType("Media Type");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("john.smith@example.org");
    author2.setFirstName("John");
    author2.setId(2L);
    author2.setImage(image3);
    author2.setLastName("Smith");
    author2.setPassword("Использован метод сервиса: {}");
    author2.setPhone("8605550118");
    author2.setRole(Role.ADMIN);

    Image image4 = new Image();
    image4.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image4.setFileSize(2L);
    image4.setId(2L);
    image4.setMediaType("Media Type");

    Ad ad2 = new Ad();
    ad2.setAuthor(author2);
    ad2.setComments(new ArrayList<>());
    ad2.setDescription("Использован метод сервиса: {}");
    ad2.setId(2L);
    ad2.setImage(image4);
    ad2.setPrice(1);
    ad2.setTitle("Mr");

    ArrayList<Ad> adList = new ArrayList<>();
    adList.add(ad2);
    adList.add(ad);
    when(adRepository.findAll()).thenReturn(adList);
    AdsDTO actualAllAds = adServiceImpl.getAllAds();
    verify(adRepository).findAll();
    List<AdDTO> results = actualAllAds.getResults();
    AdDTO getResult = results.get(1);
    assertEquals("/image/1", getResult.getImage());
    AdDTO getResult2 = results.get(0);
    assertEquals("/image/2", getResult2.getImage());
    assertEquals("Dr", getResult.getTitle());
    assertEquals("Mr", getResult2.getTitle());
    assertEquals(1, getResult.getPk().intValue());
    assertEquals(1, getResult2.getPrice().intValue());
    assertEquals(1L, getResult.getAuthor().longValue());
    assertEquals(2, getResult2.getPk().intValue());
    assertEquals(2, getResult.getPrice().intValue());
    assertEquals(2, actualAllAds.getCount().intValue());
    assertEquals(2, results.size());
    assertEquals(2L, getResult2.getAuthor().longValue());
  }

  /**
   * Method under test:  {@link AdServiceImpl#getAllAds()}
   */
  @Test
  void testGetAllAds4() {
    when(adRepository.findAll()).thenThrow(new AdNotFoundException("An error occurred"));
    assertThrows(AdNotFoundException.class, () -> adServiceImpl.getAllAds());
    verify(adRepository).findAll();
  }

  /**
   * Method under test:
   * {@link AdServiceImpl#addAd(CreateOrUpdateAdDTO, MultipartFile)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAddAd() throws IOException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
    //       at ru.skypro.homework.service.impl.AdServiceImpl.addAd(AdServiceImpl.java:74)
    //   See https://diff.blue/R013 to resolve this issue.

    CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
    adServiceImpl.addAd(createOrUpdateAdDTO,
            new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
  }

  /**
   * Method under test: {@link AdServiceImpl#getAdInfo(Long)}
   */
  @Test
  void testGetAdInfo() throws UnsupportedEncodingException {
    Image image = new Image();
    image.setData("AXAXAXAX".getBytes("UTF-8"));
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Media Type");

    User user = new User();
    user.setAds(new ArrayList<>());
    user.setComments(new ArrayList<>());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setId(1L);
    user.setImage(image);
    user.setLastName("Doe");
    user.setPassword("iloveyou");
    user.setPhone("6625550144");
    user.setRole(Role.USER);
    Optional<User> ofResult = Optional.of(user);
    when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Image image2 = new Image();
    image2.setData("AXAXAXAX".getBytes("UTF-8"));
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Media Type");

    User author = new User();
    author.setAds(new ArrayList<>());
    author.setComments(new ArrayList<>());
    author.setEmail("jane.doe@example.org");
    author.setFirstName("Jane");
    author.setId(1L);
    author.setImage(image2);
    author.setLastName("Doe");
    author.setPassword("iloveyou");
    author.setPhone("6625550144");
    author.setRole(Role.USER);

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image3);
    ad.setPrice(1);
    ad.setTitle("Dr");
    Optional<Ad> ofResult2 = Optional.of(ad);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    ExtendedAdDTO actualAdInfo = adServiceImpl.getAdInfo(1L);
    verify(adRepository).findById(Mockito.<Long>any());
    verify(userRepository).findById(Mockito.<Long>any());
    assertEquals("/image/1", actualAdInfo.getImage());
    assertEquals("6625550144", actualAdInfo.getPhone());
    assertEquals("Doe", actualAdInfo.getAuthorLastName());
    assertEquals("Dr", actualAdInfo.getTitle());
    assertEquals("Jane", actualAdInfo.getAuthorFirstName());
    assertEquals("The characteristics of someone or something", actualAdInfo.getDescription());
    assertEquals("jane.doe@example.org", actualAdInfo.getEmail());
    assertEquals(1, actualAdInfo.getPrice().intValue());
    assertEquals(1L, actualAdInfo.getPk().longValue());
  }

  /**
   * Method under test:  {@link AdServiceImpl#getAdInfo(Long)}
   */
  @Test
  void testGetAdInfo2() throws UnsupportedEncodingException {
    when(userRepository.findById(Mockito.<Long>any())).thenThrow(new AdNotFoundException("An error occurred"));

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
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertThrows(AdNotFoundException.class, () -> adServiceImpl.getAdInfo(1L));
    verify(adRepository).findById(Mockito.<Long>any());
    verify(userRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link AdServiceImpl#getAdInfo(Long)}
   */
  @Test
  void testGetAdInfo3() throws UnsupportedEncodingException {
    Image image = new Image();
    image.setData("AXAXAXAX".getBytes("UTF-8"));
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Media Type");
    User user = mock(User.class);
    when(user.getEmail()).thenReturn("jane.doe@example.org");
    when(user.getFirstName()).thenReturn("Jane");
    when(user.getLastName()).thenReturn("Doe");
    when(user.getPhone()).thenReturn("6625550144");
    doNothing().when(user).setAds(Mockito.<List<Ad>>any());
    doNothing().when(user).setComments(Mockito.<List<Comment>>any());
    doNothing().when(user).setEmail(Mockito.<String>any());
    doNothing().when(user).setFirstName(Mockito.<String>any());
    doNothing().when(user).setId(Mockito.<Long>any());
    doNothing().when(user).setImage(Mockito.<Image>any());
    doNothing().when(user).setLastName(Mockito.<String>any());
    doNothing().when(user).setPassword(Mockito.<String>any());
    doNothing().when(user).setPhone(Mockito.<String>any());
    doNothing().when(user).setRole(Mockito.<Role>any());
    user.setAds(new ArrayList<>());
    user.setComments(new ArrayList<>());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setId(1L);
    user.setImage(image);
    user.setLastName("Doe");
    user.setPassword("iloveyou");
    user.setPhone("6625550144");
    user.setRole(Role.USER);
    Optional<User> ofResult = Optional.of(user);
    when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Image image2 = new Image();
    image2.setData("AXAXAXAX".getBytes("UTF-8"));
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Media Type");

    User author = new User();
    author.setAds(new ArrayList<>());
    author.setComments(new ArrayList<>());
    author.setEmail("jane.doe@example.org");
    author.setFirstName("Jane");
    author.setId(1L);
    author.setImage(image2);
    author.setLastName("Doe");
    author.setPassword("iloveyou");
    author.setPhone("6625550144");
    author.setRole(Role.USER);

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image3);
    ad.setPrice(1);
    ad.setTitle("Dr");
    Optional<Ad> ofResult2 = Optional.of(ad);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    ExtendedAdDTO actualAdInfo = adServiceImpl.getAdInfo(1L);
    verify(adRepository).findById(Mockito.<Long>any());
    verify(userRepository).findById(Mockito.<Long>any());
    verify(user).getEmail();
    verify(user).getFirstName();
    verify(user).getLastName();
    verify(user).getPhone();
    verify(user).setAds(Mockito.<List<Ad>>any());
    verify(user).setComments(Mockito.<List<Comment>>any());
    verify(user).setEmail(Mockito.<String>any());
    verify(user).setFirstName(Mockito.<String>any());
    verify(user).setId(Mockito.<Long>any());
    verify(user).setImage(Mockito.<Image>any());
    verify(user).setLastName(Mockito.<String>any());
    verify(user).setPassword(Mockito.<String>any());
    verify(user).setPhone(Mockito.<String>any());
    verify(user).setRole(Mockito.<Role>any());
    assertEquals("/image/1", actualAdInfo.getImage());
    assertEquals("6625550144", actualAdInfo.getPhone());
    assertEquals("Doe", actualAdInfo.getAuthorLastName());
    assertEquals("Dr", actualAdInfo.getTitle());
    assertEquals("Jane", actualAdInfo.getAuthorFirstName());
    assertEquals("The characteristics of someone or something", actualAdInfo.getDescription());
    assertEquals("jane.doe@example.org", actualAdInfo.getEmail());
    assertEquals(1, actualAdInfo.getPrice().intValue());
    assertEquals(1L, actualAdInfo.getPk().longValue());
  }

  /**
   * Method under test: {@link AdServiceImpl#deleteAd(Long)}
   */
  @Test
  void testDeleteAd() throws UnsupportedEncodingException {
    doNothing().when(commentRepository).deleteAllByAd_Id(Mockito.<Long>any());
    doNothing().when(imageService).deleteImage(Mockito.<Long>any());

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
    doNothing().when(adRepository).deleteById(Mockito.<Long>any());
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    Void actualDeleteAdResult = adServiceImpl.deleteAd(1L);
    verify(adRepository).deleteById(Mockito.<Long>any());
    verify(adRepository).findById(Mockito.<Long>any());
    verify(commentRepository).deleteAllByAd_Id(Mockito.<Long>any());
    verify(imageService).deleteImage(Mockito.<Long>any());
    assertNull(actualDeleteAdResult);
  }

  /**
   * Method under test: {@link AdServiceImpl#deleteAd(Long)}
   */
  @Test
  void testDeleteAd2() throws UnsupportedEncodingException {
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
    doThrow(new AdNotFoundException("An error occurred")).when(adRepository).deleteById(Mockito.<Long>any());
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertThrows(AdNotFoundException.class, () -> adServiceImpl.deleteAd(1L));
    verify(adRepository).deleteById(Mockito.<Long>any());
    verify(adRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link AdServiceImpl#patchAd(Long, CreateOrUpdateAdDTO)}
   */
  @Test
  void testPatchAd() throws UnsupportedEncodingException {
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

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("jane.doe@example.org");
    author2.setFirstName("Jane");
    author2.setId(1L);
    author2.setImage(image3);
    author2.setLastName("Doe");
    author2.setPassword("iloveyou");
    author2.setPhone("6625550144");
    author2.setRole(Role.USER);

    Image image4 = new Image();
    image4.setData("AXAXAXAX".getBytes("UTF-8"));
    image4.setFileSize(3L);
    image4.setId(1L);
    image4.setMediaType("Media Type");

    Ad ad2 = new Ad();
    ad2.setAuthor(author2);
    ad2.setComments(new ArrayList<>());
    ad2.setDescription("The characteristics of someone or something");
    ad2.setId(1L);
    ad2.setImage(image4);
    ad2.setPrice(1);
    ad2.setTitle("Dr");
    when(adRepository.save(Mockito.<Ad>any())).thenReturn(ad2);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    AdDTO actualPatchAdResult = adServiceImpl.patchAd(1L, new CreateOrUpdateAdDTO());
    verify(adRepository).findById(Mockito.<Long>any());
    verify(adRepository).save(Mockito.<Ad>any());
    assertEquals("/image/1", actualPatchAdResult.getImage());
    assertEquals("Dr", actualPatchAdResult.getTitle());
    assertEquals(1, actualPatchAdResult.getPk().intValue());
    assertEquals(1, actualPatchAdResult.getPrice().intValue());
    assertEquals(1L, actualPatchAdResult.getAuthor().longValue());
  }

  /**
   * Method under test: {@link AdServiceImpl#patchAd(Long, CreateOrUpdateAdDTO)}
   */
  @Test
  void testPatchAd2() throws UnsupportedEncodingException {
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
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
    //       at ru.skypro.homework.service.impl.AdServiceImpl.getAllAdsByAuthor(AdServiceImpl.java:119)
    //   See https://diff.blue/R013 to resolve this issue.

    adServiceImpl.getAllAdsByAuthor();
  }

  /**
   * Method under test: {@link AdServiceImpl#patchAdImage(Long, MultipartFile)}
   */
  @Test
  void testPatchAdImage() throws IOException {
    Image image = new Image();
    image.setData("AXAXAXAX".getBytes("UTF-8"));
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Media Type");
    when(imageService.addImage(Mockito.<MultipartFile>any())).thenReturn(image);
    doNothing().when(imageService).deleteImage(Mockito.<Long>any());

    Image image2 = new Image();
    image2.setData("AXAXAXAX".getBytes("UTF-8"));
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Media Type");

    User author = new User();
    author.setAds(new ArrayList<>());
    author.setComments(new ArrayList<>());
    author.setEmail("jane.doe@example.org");
    author.setFirstName("Jane");
    author.setId(1L);
    author.setImage(image2);
    author.setLastName("Doe");
    author.setPassword("iloveyou");
    author.setPhone("6625550144");
    author.setRole(Role.USER);

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image3);
    ad.setPrice(1);
    ad.setTitle("Dr");
    Optional<Ad> ofResult = Optional.of(ad);

    Image image4 = new Image();
    image4.setData("AXAXAXAX".getBytes("UTF-8"));
    image4.setFileSize(3L);
    image4.setId(1L);
    image4.setMediaType("Media Type");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("jane.doe@example.org");
    author2.setFirstName("Jane");
    author2.setId(1L);
    author2.setImage(image4);
    author2.setLastName("Doe");
    author2.setPassword("iloveyou");
    author2.setPhone("6625550144");
    author2.setRole(Role.USER);

    Image image5 = new Image();
    image5.setData("AXAXAXAX".getBytes("UTF-8"));
    image5.setFileSize(3L);
    image5.setId(1L);
    image5.setMediaType("Media Type");

    Ad ad2 = new Ad();
    ad2.setAuthor(author2);
    ad2.setComments(new ArrayList<>());
    ad2.setDescription("The characteristics of someone or something");
    ad2.setId(1L);
    ad2.setImage(image5);
    ad2.setPrice(1);
    ad2.setTitle("Dr");
    when(adRepository.save(Mockito.<Ad>any())).thenReturn(ad2);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    Void actualPatchAdImageResult = adServiceImpl.patchAdImage(1L,
            new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(adRepository).findById(Mockito.<Long>any());
    verify(adRepository).save(Mockito.<Ad>any());
    verify(imageService).addImage(Mockito.<MultipartFile>any());
    verify(imageService).deleteImage(Mockito.<Long>any());
    assertNull(actualPatchAdImageResult);
  }

  /**
   * Method under test: {@link AdServiceImpl#patchAdImage(Long, MultipartFile)}
   */
  @Test
  void testPatchAdImage2() throws IOException {
    Image image = new Image();
    image.setData("AXAXAXAX".getBytes("UTF-8"));
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Media Type");
    when(imageService.addImage(Mockito.<MultipartFile>any())).thenReturn(image);
    doNothing().when(imageService).deleteImage(Mockito.<Long>any());

    Image image2 = new Image();
    image2.setData("AXAXAXAX".getBytes("UTF-8"));
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Media Type");

    User author = new User();
    author.setAds(new ArrayList<>());
    author.setComments(new ArrayList<>());
    author.setEmail("jane.doe@example.org");
    author.setFirstName("Jane");
    author.setId(1L);
    author.setImage(image2);
    author.setLastName("Doe");
    author.setPassword("iloveyou");
    author.setPhone("6625550144");
    author.setRole(Role.USER);

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image3);
    ad.setPrice(1);
    ad.setTitle("Dr");
    Optional<Ad> ofResult = Optional.of(ad);
    when(adRepository.save(Mockito.<Ad>any())).thenThrow(new AdNotFoundException("An error occurred"));
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertThrows(AdNotFoundException.class, () -> adServiceImpl.patchAdImage(1L,
            new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
    verify(adRepository).findById(Mockito.<Long>any());
    verify(adRepository).save(Mockito.<Ad>any());
    verify(imageService).addImage(Mockito.<MultipartFile>any());
    verify(imageService).deleteImage(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link AdServiceImpl#getComments(Long)}
   */
  @Test
  void testGetComments() throws UnsupportedEncodingException {
    ArrayList<Comment> commentList = new ArrayList<>();
    when(commentRepository.findAllByAd(Mockito.<Ad>any())).thenReturn(commentList);

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
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    CommentsDTO actualComments = adServiceImpl.getComments(1L);
    verify(adRepository).findById(Mockito.<Long>any());
    verify(commentRepository).findAllByAd(Mockito.<Ad>any());
    assertEquals(0, actualComments.getCount().intValue());
    assertEquals(commentList, actualComments.getResults());
  }

  /**
   * Method under test:  {@link AdServiceImpl#getComments(Long)}
   */
  @Test
  void testGetComments2() throws UnsupportedEncodingException {
    when(commentRepository.findAllByAd(Mockito.<Ad>any())).thenThrow(new AdNotFoundException("An error occurred"));

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
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertThrows(AdNotFoundException.class, () -> adServiceImpl.getComments(1L));
    verify(adRepository).findById(Mockito.<Long>any());
    verify(commentRepository).findAllByAd(Mockito.<Ad>any());
  }

  /**
   * Method under test: {@link AdServiceImpl#getComments(Long)}
   */
  @Test
  void testGetComments3() throws UnsupportedEncodingException {
    Image image = new Image();
    image.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Использован метод сервиса: {}");

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
    image2.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Использован метод сервиса: {}");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image2);
    ad.setPrice(2);
    ad.setTitle("Dr");

    Image image3 = new Image();
    image3.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Использован метод сервиса: {}");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("jane.doe@example.org");
    author2.setFirstName("Jane");
    author2.setId(1L);
    author2.setImage(image3);
    author2.setLastName("Doe");
    author2.setPassword("iloveyou");
    author2.setPhone("6625550144");
    author2.setRole(Role.USER);

    Comment comment = new Comment();
    comment.setAd(ad);
    comment.setAuthor(author2);
    comment.setCreatedAt(2L);
    comment.setId(1L);
    comment.setText("Использован метод сервиса: {}");

    ArrayList<Comment> commentList = new ArrayList<>();
    commentList.add(comment);
    when(commentRepository.findAllByAd(Mockito.<Ad>any())).thenReturn(commentList);

    Image image4 = new Image();
    image4.setData("AXAXAXAX".getBytes("UTF-8"));
    image4.setFileSize(3L);
    image4.setId(1L);
    image4.setMediaType("Media Type");

    User author3 = new User();
    author3.setAds(new ArrayList<>());
    author3.setComments(new ArrayList<>());
    author3.setEmail("jane.doe@example.org");
    author3.setFirstName("Jane");
    author3.setId(1L);
    author3.setImage(image4);
    author3.setLastName("Doe");
    author3.setPassword("iloveyou");
    author3.setPhone("6625550144");
    author3.setRole(Role.USER);

    Image image5 = new Image();
    image5.setData("AXAXAXAX".getBytes("UTF-8"));
    image5.setFileSize(3L);
    image5.setId(1L);
    image5.setMediaType("Media Type");

    Ad ad2 = new Ad();
    ad2.setAuthor(author3);
    ad2.setComments(new ArrayList<>());
    ad2.setDescription("The characteristics of someone or something");
    ad2.setId(1L);
    ad2.setImage(image5);
    ad2.setPrice(1);
    ad2.setTitle("Dr");
    Optional<Ad> ofResult = Optional.of(ad2);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    CommentsDTO actualComments = adServiceImpl.getComments(1L);
    verify(adRepository).findById(Mockito.<Long>any());
    verify(commentRepository).findAllByAd(Mockito.<Ad>any());
    assertEquals(1, actualComments.getCount().intValue());
    assertEquals(1, actualComments.getResults().size());
  }

  /**
   * Method under test: {@link AdServiceImpl#getComments(Long)}
   */
  @Test
  void testGetComments4() throws UnsupportedEncodingException {
    Image image = new Image();
    image.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image.setFileSize(3L);
    image.setId(1L);
    image.setMediaType("Использован метод сервиса: {}");

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
    image2.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image2.setFileSize(3L);
    image2.setId(1L);
    image2.setMediaType("Использован метод сервиса: {}");

    Ad ad = new Ad();
    ad.setAuthor(author);
    ad.setComments(new ArrayList<>());
    ad.setDescription("The characteristics of someone or something");
    ad.setId(1L);
    ad.setImage(image2);
    ad.setPrice(2);
    ad.setTitle("Dr");

    Image image3 = new Image();
    image3.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Использован метод сервиса: {}");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("jane.doe@example.org");
    author2.setFirstName("Jane");
    author2.setId(1L);
    author2.setImage(image3);
    author2.setLastName("Doe");
    author2.setPassword("iloveyou");
    author2.setPhone("6625550144");
    author2.setRole(Role.USER);

    Comment comment = new Comment();
    comment.setAd(ad);
    comment.setAuthor(author2);
    comment.setCreatedAt(2L);
    comment.setId(1L);
    comment.setText("Использован метод сервиса: {}");

    Image image4 = new Image();
    image4.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image4.setFileSize(2L);
    image4.setId(2L);
    image4.setMediaType("Media Type");

    User author3 = new User();
    author3.setAds(new ArrayList<>());
    author3.setComments(new ArrayList<>());
    author3.setEmail("john.smith@example.org");
    author3.setFirstName("John");
    author3.setId(2L);
    author3.setImage(image4);
    author3.setLastName("Smith");
    author3.setPassword("Использован метод сервиса: {}");
    author3.setPhone("8605550118");
    author3.setRole(Role.ADMIN);

    Image image5 = new Image();
    image5.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image5.setFileSize(2L);
    image5.setId(2L);
    image5.setMediaType("Media Type");

    Ad ad2 = new Ad();
    ad2.setAuthor(author3);
    ad2.setComments(new ArrayList<>());
    ad2.setDescription("Использован метод сервиса: {}");
    ad2.setId(2L);
    ad2.setImage(image5);
    ad2.setPrice(1);
    ad2.setTitle("Mr");

    Image image6 = new Image();
    image6.setData(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
    image6.setFileSize(2L);
    image6.setId(2L);
    image6.setMediaType("Media Type");

    User author4 = new User();
    author4.setAds(new ArrayList<>());
    author4.setComments(new ArrayList<>());
    author4.setEmail("john.smith@example.org");
    author4.setFirstName("John");
    author4.setId(2L);
    author4.setImage(image6);
    author4.setLastName("Smith");
    author4.setPassword("Использован метод сервиса: {}");
    author4.setPhone("8605550118");
    author4.setRole(Role.ADMIN);

    Comment comment2 = new Comment();
    comment2.setAd(ad2);
    comment2.setAuthor(author4);
    comment2.setCreatedAt(1L);
    comment2.setId(2L);
    comment2.setText("Text");

    ArrayList<Comment> commentList = new ArrayList<>();
    commentList.add(comment2);
    commentList.add(comment);
    when(commentRepository.findAllByAd(Mockito.<Ad>any())).thenReturn(commentList);

    Image image7 = new Image();
    image7.setData("AXAXAXAX".getBytes("UTF-8"));
    image7.setFileSize(3L);
    image7.setId(1L);
    image7.setMediaType("Media Type");

    User author5 = new User();
    author5.setAds(new ArrayList<>());
    author5.setComments(new ArrayList<>());
    author5.setEmail("jane.doe@example.org");
    author5.setFirstName("Jane");
    author5.setId(1L);
    author5.setImage(image7);
    author5.setLastName("Doe");
    author5.setPassword("iloveyou");
    author5.setPhone("6625550144");
    author5.setRole(Role.USER);

    Image image8 = new Image();
    image8.setData("AXAXAXAX".getBytes("UTF-8"));
    image8.setFileSize(3L);
    image8.setId(1L);
    image8.setMediaType("Media Type");

    Ad ad3 = new Ad();
    ad3.setAuthor(author5);
    ad3.setComments(new ArrayList<>());
    ad3.setDescription("The characteristics of someone or something");
    ad3.setId(1L);
    ad3.setImage(image8);
    ad3.setPrice(1);
    ad3.setTitle("Dr");
    Optional<Ad> ofResult = Optional.of(ad3);
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    CommentsDTO actualComments = adServiceImpl.getComments(1L);
    verify(adRepository).findById(Mockito.<Long>any());
    verify(commentRepository).findAllByAd(Mockito.<Ad>any());
    assertEquals(2, actualComments.getCount().intValue());
    assertEquals(2, actualComments.getResults().size());
  }

  /**
   * Method under test:
   * {@link AdServiceImpl#addComment(Long, CreateOrUpdateCommentDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testAddComment() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
    //       at ru.skypro.homework.service.impl.AdServiceImpl.addComment(AdServiceImpl.java:164)
    //   See https://diff.blue/R013 to resolve this issue.

    CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
    createOrUpdateCommentDTO.setText("Text");
    adServiceImpl.addComment(1L, createOrUpdateCommentDTO);
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
   * Method under test:
   * {@link AdServiceImpl#patchComment(Long, Long, CreateOrUpdateCommentDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testPatchComment() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
    //       at ru.skypro.homework.service.impl.AdServiceImpl.patchComment(AdServiceImpl.java:191)
    //   See https://diff.blue/R013 to resolve this issue.

    CreateOrUpdateCommentDTO createOrUpdateCommentDTO = new CreateOrUpdateCommentDTO();
    createOrUpdateCommentDTO.setText("Text");
    adServiceImpl.patchComment(1L, 1L, createOrUpdateCommentDTO);
  }

  /**
   * Method under test: {@link AdServiceImpl#isAuthorAd(String, Long)}
   */
  @Test
  void testIsAuthorAd() throws UnsupportedEncodingException {
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
    when(adRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    boolean actualIsAuthorAdResult = adServiceImpl.isAuthorAd("janedoe", 1L);
    verify(adRepository).findById(Mockito.<Long>any());
    assertFalse(actualIsAuthorAdResult);
  }

  /**
   * Method under test: {@link AdServiceImpl#isAuthorComment(String, Long)}
   */
  @Test
  void testIsAuthorComment() throws UnsupportedEncodingException {
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

    Image image3 = new Image();
    image3.setData("AXAXAXAX".getBytes("UTF-8"));
    image3.setFileSize(3L);
    image3.setId(1L);
    image3.setMediaType("Media Type");

    User author2 = new User();
    author2.setAds(new ArrayList<>());
    author2.setComments(new ArrayList<>());
    author2.setEmail("jane.doe@example.org");
    author2.setFirstName("Jane");
    author2.setId(1L);
    author2.setImage(image3);
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
    when(commentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    boolean actualIsAuthorCommentResult = adServiceImpl.isAuthorComment("janedoe", 1L);
    verify(commentRepository).findById(Mockito.<Long>any());
    assertFalse(actualIsAuthorCommentResult);
  }
}
