package ru.skypro.homework.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.WebSecurityConfigTest;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.AdsServiceImpl;


@WebMvcTest(ImageController.class)
@Import(value = WebSecurityConfigTest.class)
class ImageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @InjectMocks
  private ImageController imageController;

  @MockBean
  private AdsServiceImpl adsService;

  @MockBean
  private AdsRepository adsRepository;

  @MockBean
  private ImageService imageService;

  @MockBean
  private ImageRepository imageRepository;

  private AdEntity adEntity;
  private CommentEntity comment;
  private UserEntity user;
  private ImageEntity imageEntity;

  @Test
  public void imageControllerTest() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    MockMultipartFile image = new MockMultipartFile("image", "image.jpeg",
        MediaType.IMAGE_JPEG_VALUE, "image.jpeg".getBytes());

    LocalDateTime date = LocalDateTime.parse("2007-12-03T10:15:30");
    AdEntity adEntity = new AdEntity(1, null, 100, "TitleTest", "TestDescription", null, null);
    UserEntity user = new UserEntity(1, "firstname", "lastname", "user@mgmail.com", "+788994455", date, "Moscow", "path/to/image",
        List.of(adEntity), null);
    CommentEntity comment = new CommentEntity(1,user, date, adEntity, "TextComments");
    ImageEntity imageEntity = new ImageEntity(1, "path/to/image", adEntity);

    when(adsRepository.findById(anyInt())).thenReturn(Optional.of(adEntity));
    when(imageRepository.save(imageEntity)).thenReturn(imageEntity);


    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/ads/image/{id}", 1,HttpMethod.PATCH)
        .file(image)
        .with(user("user@gmail.com").password("password").roles("USER"))
        .with(csrf());

    mockMvc.perform(builder).andExpect(status().isOk());
  }





}