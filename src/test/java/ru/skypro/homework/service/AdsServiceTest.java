package ru.skypro.homework.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AdsServiceImpl;

/**
 * Юнит тесты для сервиса
 */
@ExtendWith(MockitoExtension.class)
class AdsServiceTest {

  @InjectMocks
  private AdsService adsService;
  @Mock
  private UserService userService;

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private AdsRepository adsRepository;
  @Mock
  private ImageRepository imageRepository;
  @Mock
  private UserRepository userRepository;

  @Mock
  private AdMapper adMapper;
  @Mock
  private CommentMapper commentMapper;

  @Mock
  private ImageMapper imageMapper;

  @Mock
  private UserMapper userMapper;

  @Mock
  private AdsOtherMapper adsOtherMapper;



  private AdEntity adEntity;
  private CommentEntity comment;
  private UserEntity user;
  private ImageEntity image;

  AdsServiceTest() {
    adsService = new AdsServiceImpl(adsRepository,commentRepository,userRepository, adMapper, commentMapper, imageRepository, imageMapper, userService,userMapper, adsOtherMapper);
  }

  @BeforeEach
  void init() {
    LocalDateTime date = LocalDateTime.parse("2007-12-03T10:15:30");
    adEntity = new AdEntity(1, null, 100, "TitleTest", "TestDescription", null, null);
    user = new UserEntity(1, "firstname", "lastname", "user@mgmail.com", "+788994455", date, "Moscow", "path/to/image",
        List.of(adEntity), null);
    comment = new CommentEntity(1,user, date, adEntity, "TextComments");
    image = new ImageEntity(1, "path/to/image", adEntity);
    user.setCommentEntities(List.of(comment));
    adEntity.setCommentEntities(List.of(comment));
    adEntity.setImageEntities(List.of(image));
  }

  @AfterEach
  void clearAllTestData() {
    adEntity =null;
    comment = null;
    image = null;
    user = null;
  }

  @Test
  void deleteCommentsPositiveTest() {
    lenient().when(adsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(adEntity));
    lenient().when(commentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(comment));
    Assertions.assertThat(adsRepository.findById(anyInt())).isNotNull();
    lenient().doNothing().when(commentRepository).deleteById(anyInt());
//    assertDoesNotThrow(() -> adsService.deleteComments(anyInt(), anyInt()));
  }

  @Test
  void deleteCommentsNegativeTest() {
    lenient().when(commentRepository.findById(anyInt())).thenThrow(ElemNotFound.class);
    assertThrows(ElemNotFound.class, () -> commentRepository.findById(anyInt()));
    lenient().doNothing().when(adsService).deleteComments(anyInt(), anyInt());
  }

  @Test
  void removeAds() {

  }

  @Test
  void uploadImage() {

  }
}