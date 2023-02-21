package ru.skypro.homework.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
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
  private AdsService adsService = mock(AdsService.class);

  @Mock
  private CommentRepository commentRepository;
  @Mock
  private AdsRepository adsRepository;
  @Mock
  private ImageRepository imageRepository;
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
  void deleteCommentsPositiveTest() {
    when(adsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(adEntity));
    when(commentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(comment));
    Assertions.assertThat(adsRepository.findById(anyInt())).isNotNull().contains(adEntity).hasValue(adEntity)
            .containsInstanceOf(AdEntity.class);
    Assertions.assertThat(commentRepository.findById(anyInt())).isNotNull().contains(comment).hasValue(comment)
            .containsInstanceOf(CommentEntity.class);

    doNothing().when(commentRepository).deleteById(anyInt());
    assertDoesNotThrow(() -> commentRepository.deleteById(anyInt()));

    doNothing().when(adsService).deleteComments(anyInt(), anyInt());
    assertDoesNotThrow(() -> adsService.deleteComments(anyInt(), anyInt()));


    verify(commentRepository, times(1)).deleteById(anyInt());
    verify(adsService, times(1)).deleteComments(anyInt(),anyInt());
  }

  @Test
  void deleteCommentsNegativeTest() {
    doThrow(new ElemNotFound()).when(commentRepository).deleteById(anyInt());
    doThrow(new ElemNotFound()).when(adsService).deleteComments(anyInt(),anyInt());

    Assertions.assertThatThrownBy(() -> commentRepository.deleteById(anyInt()));
    Assertions.assertThatThrownBy(() -> adsService.deleteComments(anyInt(), anyInt()));

    verify(commentRepository, times(1)).deleteById(anyInt());
    verify(adsService, times(1)).deleteComments(anyInt(), anyInt());
  }

  @Test
  void removeAdsPositiveTest() {
    when(adsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(adEntity));
    Assertions.assertThat(adsRepository.findById(anyInt())).isNotNull().contains(adEntity)
            .hasValue(adEntity).containsInstanceOf(AdEntity.class);

    doNothing().when(adsService).removeAds(anyInt());
    assertDoesNotThrow(() -> adsService.removeAds(anyInt()));
    doNothing().when(adsRepository).deleteById(anyInt());
    assertDoesNotThrow(() -> adsRepository.deleteById(anyInt()));

    verify(adsRepository, times(1)).deleteById(anyInt());
    verify(adsService, times(1)).removeAds(anyInt());
  }

  @Test
  void removeAdsNegativeTest() {
    doThrow(new ElemNotFound()).when(adsService).removeAds(anyInt());
    doThrow(new ElemNotFound()).when(adsRepository).deleteById(anyInt());

    Assertions.assertThatThrownBy(() -> adsService.removeAds(anyInt()));
    Assertions.assertThatThrownBy(() -> adsRepository.deleteById(anyInt()));

    verify(adsService, times(1)).removeAds(anyInt());
    verify(adsRepository, times(1)).deleteById(anyInt());
    }

  @Test
  void uploadImagePositiveTest() throws IOException {
    AdsService adsService = mock(AdsService.class);
    MultipartFile file = getTestPhoto();
    when(adsRepository.findById(1)).thenReturn(Optional.ofNullable(adEntity));
    Assertions.assertThat(adsRepository.findById(1)).isNotNull().contains(adEntity).hasValue(adEntity)
            .containsInstanceOf(AdEntity.class);
    doNothing().when(adsService).uploadImage(1, file);
    Assertions.assertThatNoException().isThrownBy(() ->adsService.uploadImage(1, file));
    verify(adsService, times(1)).uploadImage(1, file);
  }

  @Test
  void uploadImageNegativeTest() throws IOException {
    MultipartFile file = getTestPhoto();
    doThrow(ElemNotFound.class).when(adsService).uploadImage(99, file);
    doThrow(IOException.class).when(adsService).uploadImage(1, null);
    Assertions.assertThatThrownBy(() -> adsService.uploadImage(99, file));
    Assertions.assertThatThrownBy(() -> adsService.uploadImage(1, null));
    verify(adsService, times(1)).uploadImage(99, file);
    verify(adsService, times(1)).uploadImage(1, null);
  }

  private MockMultipartFile getTestPhoto() {
    return new MockMultipartFile("data", "photo.jpeg",
            MediaType.MULTIPART_FORM_DATA_VALUE, "photo.jpeg".getBytes());
  }
}