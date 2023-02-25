package ru.skypro.homework.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.mapper.AdMapperImpl;
import ru.skypro.homework.mapper.AdsOtherMapper;
import ru.skypro.homework.mapper.CommentMapperImpl;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AdsServiceImpl;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AdsController.class)
class AdsControllerTest {

  private final Integer ONE = 1;
  @Autowired
  private MockMvc mockMvc;
  @SpyBean
  private CommentMapperImpl commentMapper;
  @SpyBean
  private AdMapperImpl adMapper;
  @MockBean
  private UserRepository userRepository;
  @MockBean
  private CommentRepository commentRepository;
  @MockBean
  private AdsRepository adsRepository;
  @MockBean
  private ImageMapper imageMapper;
  @MockBean
  private UserService userService;
  @MockBean
  private UserMapper userMapper;
  @MockBean
  private AdsOtherMapper adsOtherMapper;
  @MockBean
  private ImageRepository imageRepository;
  @SpyBean
  private AdsServiceImpl adsService;
  @InjectMocks
  private AdsController adsController;
  private MockMultipartFile image;
  private MockMultipartFile properties;
  private MockMultipartFile propertiesNonValid;
  private MockMultipartFile authentication;
  private JSONObject propertiesNonValidJS;
  private JSONObject propertiesJS;
  private JSONObject authenticationJS;
  private JSONObject commentDTO;
  private JSONObject commentDTONonValid;
  private Integer price;
  private String title;
  private String email;
  private String time;

  @BeforeEach
  void init() {
    time = "23-02-2022 08:09:10";
    price = 11;
    title = "заголовок";
    email = "dmitr@gmail.com";
    propertiesJS = new JSONObject();
    propertiesJS.put("price", price);
    propertiesJS.put("title", title);
    authenticationJS = new JSONObject();
    authenticationJS.put("email", email);
    propertiesNonValidJS = new JSONObject();
    propertiesNonValidJS.put("title", title);
    propertiesNonValidJS.put("price", price - 100);
    commentDTO = new JSONObject();
    commentDTO.put("author", ONE);
    commentDTO.put("createdAt", time);
    commentDTO.put("pk", ONE);
    commentDTO.put("text", title);
    commentDTONonValid = new JSONObject();
    commentDTONonValid.put("author", ONE);
    commentDTONonValid.put("createdAt", time);
    commentDTONonValid.put("pk", ONE - 100);
    commentDTONonValid.put("text", title);

    image = new MockMultipartFile(
        "image",
        "image.jpeg",
        MediaType.MULTIPART_FORM_DATA_VALUE,
        "image.jpeg".getBytes()
    );
    properties = new MockMultipartFile(
        "properties",
        "properties.json",
        MediaType.APPLICATION_JSON_VALUE,
        propertiesJS.toString().getBytes()
    );
    authentication = new MockMultipartFile(
        "authentication",
        "authentication.json",
        MediaType.APPLICATION_JSON_VALUE,
        authenticationJS.toString().getBytes()
    );
    propertiesNonValid = new MockMultipartFile(
        "properties",
        "propertiesNonValidJS.json",
        MediaType.APPLICATION_JSON_VALUE,
        propertiesNonValidJS.toString().getBytes()
    );
  }

  @AfterEach
  void clearAllTestData() {
    price = null;
    title = null;
    email = null;
    propertiesJS = null;
    authenticationJS = null;
    image = null;
    authentication = null;
    properties = null;
  }

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
    savedCommentEntity.setCreatedAt(LocalDateTime.of(2022, 2, 23, 8, 9, 10));
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
  void updateAds() throws Exception {
    int id = 3;
    String url = "/ads/" + id;

    JSONObject CreateAdsObject = new JSONObject();
    CreateAdsObject.put("description", "описание");
    CreateAdsObject.put("price", 99);
    CreateAdsObject.put("title", "заголовок");

    when(adsRepository.findById(id)).thenReturn(Optional.of(getAdEntity()));

    AdEntity resultAdEntity = getAdEntity();
    resultAdEntity.setPrice(99);
    resultAdEntity.setDescription("описание");
    resultAdEntity.setTitle("заголовок");

    when(adsRepository.save(resultAdEntity)).thenReturn(resultAdEntity);

    mockMvc.perform(MockMvcRequestBuilders.patch(
                url)
            .content(CreateAdsObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.author").value(1))
        .andExpect(jsonPath("$.image[0]").value(("/ads/image/1")))
        .andExpect(jsonPath("$.image[1]").value(("/ads/image/2")))
        .andExpect(jsonPath("$.pk").value(3))
        .andExpect(jsonPath("$.title").value("заголовок"))
        .andExpect(jsonPath("$.price").value(99));

  }

  @Test
  void updateAdsNegative() throws Exception {
    int id = 1;
    String url = "/ads/" + id;

    JSONObject CreateAdsObject = new JSONObject();
    CreateAdsObject.put("description", "описание");
    CreateAdsObject.put("price", 99);
    CreateAdsObject.put("title", "заголовок");

    when(adsRepository.findById(id)).thenThrow(ElemNotFound.class);

    mockMvc.perform(MockMvcRequestBuilders.patch(
                url)
            .content(CreateAdsObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createAdsWithValidArg() throws Exception {
    String url = "/ads";

    when(imageMapper.toEntity(any(ImageDTO.class))).thenReturn(getImageEntity());

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(image)
            .file(properties)
            .file(authentication)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(
            jsonPath("$.image[0]").value((Base64.getEncoder().encodeToString(image.getBytes()))))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.price").value(price))
        .andExpect(status().isOk());
  }

  @Test
  void createAdsWithNonValidArg() throws Exception {
    String url = "/ads";

    when(imageMapper.toEntity(any(ImageDTO.class))).thenReturn(getImageEntity());

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(image)
            .file(propertiesNonValid)
            .file(authentication)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void addAdsCommentsWithValidArg() throws Exception {
    String ad_pk = "1";
    String url = "/ads/" + ad_pk + "/comments";

    when(adsRepository.findById(anyInt())).thenReturn(Optional.of(getAdEntity()));

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(authentication)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .content(String.valueOf(commentDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }

  @Test
  void addAdsCommentsWithNoValidArgs() throws Exception {
    String ad_pk = "1";
    String url = "/ads/" + ad_pk + "/comments";

    when(adsRepository.findById(anyInt())).thenReturn(Optional.of(getAdEntity()));

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(authentication)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .content(String.valueOf(commentDTONonValid))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

  }

  @Test
  void getAdsMe() throws Exception {
    String url = "/ads/me";

    mockMvc.perform(get(url)
            .content(authenticationJS.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }


  private CommentEntity getComment() {
    CommentEntity comment = new CommentEntity();
    comment.setId(2);
    comment.setAd(getAdEntity());
    comment.setAuthor(getCurrentAuthor());
    comment.setCreatedAt(LocalDateTime.of(2023, 02, 10, 10, 10, 10));
    comment.setText("текст");
    return comment;
  }

  private AdEntity getAdEntity() {
    AdEntity adEntity = new AdEntity();
    adEntity.setId(3);
    adEntity.setAuthor(getNewAuthor());
    List<ImageEntity> imageEntityList = new ArrayList<>();
    imageEntityList.add(new ImageEntity(1, "/ads/image/1", adEntity));
    imageEntityList.add(new ImageEntity(2, "/ads/image/2", adEntity));
    adEntity.setImageEntities(imageEntityList);
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

  private ImageEntity getImageEntity() {
    return new ImageEntity(1, "path/to/image", getAdEntity());
  }

  private UserDTO getUserDTO() {
    return new UserDTO("dmitry@gmail.com"
        , "Dmitry", 1, "Pospelov"
        , "89299129121", "20-02-2023 10:12:13", "Moscow", "Реклама");
  }

  private CommentDTO getCommentDTO() {
    return new CommentDTO(getNewAuthor().getId(), "20-02-2023 10:12:13", getAdEntity().getId(),
        "testText");

  }

}