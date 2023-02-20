package ru.skypro.homework.service;

import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;

/**
 * Юнит тесты для сервиса
 */
@ExtendWith(MockitoExtension.class)
class AdsServiceTest {

  @Mock
  AdsService adsService;

  @Mock
  UserService userService;
  @Mock
  ImageService imageService;

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private AdsRepository adsRepository;

  private AdEntity adEntity;
  private CommentEntity comment;
  private UserEntity user;
  private ImageEntity image;

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
  void deleteComments() {
    lenient().when(adsRepository.findById(adEntity.getId())).thenReturn(Optional.ofNullable(adEntity));
    lenient().when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
    Assertions.assertThat(adsRepository.findById(1)).isNotNull();

  }

  @Test
  void removeAds() {

  }

  @Test
  void uploadImage() {

  }
}