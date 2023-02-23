package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.AdMapperImpl;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.CommentMapperImpl;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AdsServiceImplTest {

    @Mock
    AdsRepository adsRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    AdMapper adMapper;
    @Mock
    CommentMapper commentMapper;
    @InjectMocks
    AdsServiceImpl out;
    AdMapper adImplMapper = new AdMapperImpl();
    CommentMapper commentImplMapper = new CommentMapperImpl();
    @Test
    void updateComments() {
        int sourceCommentId = 2;
        int sourceAdsId = 1;
        CommentDTO sourceCommentDTO = getCommentDTO();
        CommentEntity commentEntity = getCommentEntity();

        lenient().when(commentRepository.findByIdAndAd_Id(sourceCommentId, sourceAdsId))
                .thenReturn(Optional.of(getCommentEntity()));
        lenient().when(userRepository.findById(3)).thenReturn(Optional.of(getNewCommentAuthor()));

        commentEntity.setAuthor(getNewCommentAuthor());
        commentEntity.setText("Реклама");
        commentEntity.setCreatedAt(LocalDateTime.of(2023, 02, 20, 10, 12,13));

        lenient().when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
        lenient().when(commentMapper.toDTO(commentEntity)).thenReturn(commentImplMapper.toDTO(commentEntity));

        CommentDTO excepted = out.updateComments(sourceAdsId, sourceCommentId, sourceCommentDTO);
        CommentDTO actual = getCommentDTO();

        assertEquals(excepted,actual);
    }

    @Test
    void updateCommentsNegativeNotFoundComment() {
        lenient().when(commentRepository.findByIdAndAd_Id(anyInt(), anyInt()))
                .thenReturn(Optional.of(getCommentEntity()));
        lenient().when(userRepository.findById(anyInt())).thenThrow(ElemNotFound.class);
        assertThrows(ElemNotFound.class, () -> out.updateComments(1,1, getCommentDTO()));
    }

    @Test
    void updateCommentsNegativeNotFoundUser() {
        lenient().when(commentRepository.findByIdAndAd_Id(anyInt(),anyInt())).thenThrow(ElemNotFound.class);
        assertThrows(ElemNotFound.class, () -> out.updateComments(1,1, getCommentDTO()));
    }

    @Test
    void updateAds() {
        CreateAds sourceCreateAds = getCreateAds();
        int sourceId = 1;
        lenient().when(adsRepository.findById(anyInt())).thenReturn(Optional.of(getAdEntity()));
        lenient().when(adsRepository.save(any(AdEntity.class))).thenReturn(getResultAdEntity());
        lenient().when(adMapper.toDTO(getResultAdEntity())).thenReturn(adImplMapper.toDTO(getResultAdEntity()));
        AdsDTO excepted = out.updateAds(sourceId, sourceCreateAds);
        AdsDTO actual = getResultAdsDTO();

        assertEquals(excepted,actual);
    }

    @Test
    void updateAdsNegativeTest() {
        lenient().when(adsRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ElemNotFound.class, () -> out.updateAds(1, getCreateAds()));
    }

    private CreateAds getCreateAds() {
        return new CreateAds("Описание", 99, "Заголовок");
    }

    private AdEntity getAdEntity() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);

        List<ImageEntity> imageEntities = new ArrayList<>();
        imageEntities.add(new ImageEntity(1, "/ads/image/1", adEntity));

        adEntity.setImageEntities(imageEntities);
        adEntity.setTitle("Title");
        adEntity.setDescription("Description");
        adEntity.setCommentEntities(Collections.emptyList());
        adEntity.setPrice(100);
        adEntity.setAuthor(getAuthor());

        return adEntity;
    }

    private AdEntity getResultAdEntity() {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(1);

        List<ImageEntity> imageEntities = new ArrayList<>();
        imageEntities.add(new ImageEntity(1, "/ads/image/1", adEntity));

        adEntity.setImageEntities(imageEntities);
        adEntity.setTitle("Заголовок");
        adEntity.setDescription("Описание");
        adEntity.setCommentEntities(Collections.emptyList());
        adEntity.setPrice(99);
        adEntity.setAuthor(getAuthor());

        return adEntity;
    }

    private AdsDTO getResultAdsDTO() {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setPk(1);
        adsDTO.setTitle("Заголовок");
        adsDTO.setPrice(99);

        List<String> images = new ArrayList<>();
        images.add("/ads/image/1");

        adsDTO.setImage(images);
        adsDTO.setAuthor(2);
        return adsDTO;
    }

    private UserEntity getAuthor() {
        UserEntity author = new UserEntity();
        author.setImage("/users/author.png");
        author.setLastName("Иванов");
        author.setFirstName("Иван");
        author.setCity("MSK");
        author.setPhone("+79876543210");
        author.setEmail("mail@mail.ru");
        author.setRegDate(LocalDateTime.of(2023, 02, 20, 14, 20, 10));
        author.setId(2);

        return author;
    }

    private UserEntity getNewCommentAuthor() {
        UserEntity author = new UserEntity();
        author.setImage("/users/authorComment.png");
        author.setLastName("Иванов");
        author.setFirstName("Иван");
        author.setCity("MSK");
        author.setPhone("+79876543210");
        author.setEmail("mail@mail.ru");
        author.setRegDate(LocalDateTime.of(2023, 02, 20, 14, 20, 10));
        author.setId(3);

        return author;
    }

    private CommentEntity getCommentEntity() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText("Text");
        commentEntity.setCreatedAt(LocalDateTime.of(2023, 02, 22, 14, 20, 10));
        commentEntity.setAuthor(getAuthor());
        commentEntity.setId(2);
        commentEntity.setAd(getAdEntity());

        return commentEntity;
    }

    private CommentDTO getCommentDTO() {
        return new CommentDTO(3,"20-02-2023 10:12:13",2, "Реклама");
    }
}