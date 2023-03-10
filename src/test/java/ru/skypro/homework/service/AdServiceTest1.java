package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.SecurityService;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest1 {

  @Mock
  AdsRepository adsRepository;
  @Mock
  SecurityService securityService;
  @Mock
  CommentRepository commentRepository;
  @Spy
  AdMapper adMapper = new AdMapperImpl();
  @Spy
  AdsOtherMapper adsOtherMapper = new AdsOtherMapperImpl();
  @InjectMocks
  AdsServiceImpl adsService;
  ResponseWrapperComment responseWrapperComment;
  @Spy
  CommentMapper commentMapper = new CommentMapperImpl();

  @BeforeEach
  void cleaner() {

  }

  /**
   * Получение всех комментариев объявления
   */
  @Test
  void getAdsCommentsTest() {
    List<CommentEntity> commentEntityList = new ArrayList<>();
    commentEntityList.add(adCommentEntity(1));
    commentEntityList.add(adCommentEntity(2));

    Collection<CommentDTO> commentDTOList = commentMapper.toDTOList(commentEntityList);
    int count = commentDTOList.size();

    ResponseWrapperComment responseWrapperComment = new ResponseWrapperComment(count,
        commentDTOList);

    when(commentRepository.findAllById(Collections.singleton(1))).thenReturn(commentEntityList);
    assertThat(adsService.getAdsComments(1)).isEqualTo(responseWrapperComment);
    verify(commentRepository, times(1)).findAllById(any());
  }

  @Test
  void getAdsTest() {
    ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
    responseWrapperAds.setCount(2);
    List<AdEntity> adEntities = new ArrayList<>();
    adEntities.add(getAdEntity(1));
    adEntities.add(getAdEntity(1));
    responseWrapperAds.setResults(adMapper.toDTOList(adEntities));
    when(adsRepository.findAll()).thenReturn(adEntities);
    assertThat(adsService.getAds()).isEqualTo(responseWrapperAds);
    verify(adsRepository,times(1)).findAll();
  }

  @Test
  void getCommentsTest() {
    CommentEntity commentEntity = adCommentEntity(1);
    CommentDTO commentDTO = new CommentDTO(1, "20-02-2023 14:20:10", 1, "123456789");
    when(commentRepository.findByIdAndAd_Id(1, 1)).thenReturn(Optional.of(commentEntity));
    assertThat(adsService.getComments(1, 1)).isEqualTo(commentDTO);
    verify(commentRepository,times(1)).findByIdAndAd_Id(any(),any());
  }

  @Test
  void getAdByIdTest() {
    UserEntity author = getAuthor();
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    Authentication authentication = new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);
    authentication.setAuthenticated(true);
    FullAds fullAds = adsOtherMapper.toFullAds(getAdEntity(1));
    when(adsRepository.findById(1)).thenReturn(Optional.of(getAdEntity(1)));
    lenient().when(securityService.isAdsUpdateAvailable(any(Authentication.class),anyInt())).thenReturn(true);
    assertThat(adsService.getAdById(1,authentication)).isEqualTo(fullAds);
    verify(adsRepository,times(1)).findById(any());
  }

  @Test
  void getCommentsTestNegative() {
    assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> adsService.getComments(1,1));
  }
  @Test
  void getAdByIdTestNegative() {
    UserEntity author = getAuthor();
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    Authentication authentication = new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);

    assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> adsService.getAdById(1,authentication));
  }

  private AdEntity getAdEntity(int id) {
    AdEntity adEntity = new AdEntity();
    List<ImageEntity> imageEntities = new ArrayList<>();
    ImageEntity imageEntity = new ImageEntity(1, "/path/to/image/1", new AdEntity());

    imageEntities.add(imageEntity);

    adEntity.setId(id);
    adEntity.setTitle("afsdf");
    adEntity.setPrice(123);
    adEntity.setDescription("asfsdf");
    adEntity.setAuthor(getAuthor());
    adEntity.setImageEntities(imageEntities);
    return adEntity;
  }

  private CommentEntity adCommentEntity(int id) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setId(id);
    commentEntity.setAuthor(getAuthor());
    commentEntity.setCreatedAt(LocalDateTime.of(2023, 02, 20, 14, 20, 10));
    commentEntity.setAd(getAdEntity(1));
    commentEntity.setText("123456789");
    return commentEntity;
  }

  private UserEntity getAuthor() {
    UserEntity author = new UserEntity();
    author.setImage("/users/author.1");
    author.setLastName("Иванов");
    author.setFirstName("Иван");
    author.setPassword("1111");
    author.setCity("MSK");
    author.setPhone("+79876543210");
    author.setEmail("mail@mail.ru");
    author.setRegDate(LocalDateTime.of(2023, 02, 20, 14, 20, 10));
    author.setId(1);

    return author;
  }
}
