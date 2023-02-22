package ru.skypro.homework.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.impl.AdsServiceImpl;


import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest1 {
    @Mock
    AdsRepository adsRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    AdMapper adMapper;
    @InjectMocks
    AdsServiceImpl adsService;
    ResponseWrapperComment responseWrapperComment;
    CommentMapper commentMapper;

    @BeforeEach
    void cleaner() {

    }
@Test
    void getAdsCommentsTest() {
    ResponseWrapperComment responseWrapperComment1 = new ResponseWrapperComment();
    responseWrapperComment1.setCount(2);
    List<CommentEntity> commentEntityList = new ArrayList<>() ;
    commentEntityList.add(adCommentEntity(1));
    commentEntityList.add(adCommentEntity(2));
    List<CommentDTO> commentDTOList = new ArrayList<>();
    commentDTOList.add(new CommentDTO(1,"20-02-2023 14:20:10",1,"123456789"));
    commentDTOList.add(new CommentDTO(1,"20-02-2023 14:20:10",2,"123456789"));
    responseWrapperComment1.setResults(commentDTOList);
    System.out.println(commentEntityList);
    System.out.println(commentDTOList);
    when(commentRepository.findAllById(Collections.singleton(1))).thenReturn(commentEntityList);
//    System.out.println(adsService.getAdsComments(1));
    assertThat(adsService.getAdsComments(1)).isEqualTo(responseWrapperComment1);

    }
@Test
    void getAdsTest() {
    ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
    responseWrapperAds.setCount(1);
    List<AdEntity> adEntities = new ArrayList<>();
    adEntities.add(getAdEntity(1));
    adEntities.add(getAdEntity(2));
    responseWrapperAds.setResults(adMapper.toDTOList(adEntities));
    when(adsRepository.findAll()).thenReturn(adEntities);
    assertThat(adsService.getAds()).isEqualTo(responseWrapperAds);

    }
@Test
    void getCommentsTest() {
    CommentEntity commentEntity = adCommentEntity(1);
    System.out.println(commentEntity);
    CommentDTO commentDTO = new CommentDTO(1,"20-02-2023 14:20:10",1,"123456789");
    when(commentRepository.findByIdAndAd_Id(1,1)).thenReturn(Optional.of(commentEntity));
    assertThat(adsService.getComments(1,1)).isEqualTo(commentDTO);

    }
@Test
    void getAdByIdTest() {
        List<String> imageList = new ArrayList<>(List.of("/path/to/image/1","/path/to/image/2","/path/to/image/3"));
    FullAds fullAds = new FullAds("Иван","Иванов","3333","mail@mail.ru",imageList,
            "+79876543210",1,123,"3333");
    when(adsRepository.findById(1)).thenReturn(Optional.of(getAdEntity(1)));
    assertThat(adsService.getAdById(1)).isEqualTo(fullAds);

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
        author.setCity("MSK");
        author.setPhone("+79876543210");
        author.setEmail("mail@mail.ru");
        author.setRegDate(LocalDateTime.of(2023, 02, 20, 14, 20, 10));
        author.setId(1);

        return author;
    }
}
