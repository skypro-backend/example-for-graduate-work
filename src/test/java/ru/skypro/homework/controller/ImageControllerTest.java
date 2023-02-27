package ru.skypro.homework.controller;


import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.WebSecurityConfigTest;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  private AdsService adsService;

  @MockBean
  private AdsRepository adsRepository;

  @MockBean
  private ImageService imageService;

  @MockBean
  private ImageRepository imageRepository;

  @Test
  public void imageControllerTest() throws Exception {

    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    MockMultipartFile image = new MockMultipartFile("image", "image.jpeg",
        MediaType.IMAGE_JPEG_VALUE, "image.jpeg".getBytes());

//    mockMvc.perform(multipart(HttpMethod.PATCH, "/ads/image/{id}", 1).file(image)
//            .with(user("user@gmail.com").password("password").roles("USER"))
//            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).accept(MediaType.MULTIPART_FORM_DATA_VALUE))
//            .andDo(print())
//            .andExpect(status().isOk());

//    mockMvc.perform(multipart("/ads/image/{id}", 1).file(image)
//                    .with(user("user@gmail.com").password("password").roles("USER"))
//                    .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
//            .andDo(print())
//            .andExpect(status().isOk());

    MockMultipartHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.multipart("/ads/image/{id}",1);
    builder.with(new RequestPostProcessor() {
      @Override
      public @NotNull MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        request.setMethod("PATCH");
        return request;
      }
    });
    mockMvc.perform(builder
                    .file(image))
            .andDo(print())
            .andExpect(status().isOk());
  }





}