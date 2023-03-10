package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    SecurityService securityService;
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
        Authentication authentication = getTestAuthentication();

        lenient().when(securityService.isCommentUpdateAvailable(
                any(Authentication.class),anyInt(),anyInt())).thenReturn(true);
        lenient().when(commentRepository.findByIdAndAd_Id(sourceCommentId, sourceAdsId))
                .thenReturn(Optional.of(getCommentEntity()));
        lenient().when(userRepository.findById(3)).thenReturn(Optional.of(getNewCommentAuthor()));

        commentEntity.setAuthor(getNewCommentAuthor());
        commentEntity.setText("Реклама");
        commentEntity.setCreatedAt(LocalDateTime.of(2023, 02, 20, 10, 12,13));

        lenient().when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
        lenient().when(commentMapper.toDTO(commentEntity)).thenReturn(commentImplMapper.toDTO(commentEntity));

        CommentDTO excepted = out.updateComments(sourceAdsId, sourceCommentId, sourceCommentDTO, authentication);
        CommentDTO actual = getCommentDTO();

        assertEquals(excepted,actual);
    }

    @Test
    void updateCommentsNegativeNotFoundComment() {
        Authentication authentication = getTestAuthentication();

        lenient().when(commentRepository.findByIdAndAd_Id(anyInt(), anyInt()))
                .thenReturn(Optional.of(getCommentEntity()));
        lenient().when(userRepository.findById(anyInt())).thenThrow(ElemNotFound.class);
        lenient().when(securityService.isCommentUpdateAvailable(any(Authentication.class),
                anyInt(), anyInt())).thenReturn(true);

        assertThrows(ElemNotFound.class, () -> out.updateComments(1,1, getCommentDTO(), authentication));
    }

    @Test
    void updateCommentsNegativeNotFoundUser() {
        UserEntity author = getAuthor();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);
        authentication.setAuthenticated(true);
        lenient().when(commentRepository.findByIdAndAd_Id(anyInt(),anyInt())).thenThrow(ElemNotFound.class);
        lenient().when(securityService.isAdmin(any(Authentication.class))).thenReturn(true);
        assertThrows(ElemNotFound.class, () -> out.updateComments(1,1, getCommentDTO(), authentication));
    }

    @Test
    void updateAds() {
        CreateAds sourceCreateAds = getCreateAds();
        UserEntity author = getAuthor();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);
        authentication.setAuthenticated(true);
        int sourceId = 1;
        lenient().when(adsRepository.findById(anyInt())).thenReturn(Optional.of(getAdEntity()));
        lenient().when(adsRepository.save(any(AdEntity.class))).thenReturn(getResultAdEntity());
        lenient().when(adMapper.toDTO(getResultAdEntity())).thenReturn(adImplMapper.toDTO(getResultAdEntity()));
        lenient().when(securityService.isAdsUpdateAvailable(any(Authentication.class),anyInt())).thenReturn(true);
        AdsDTO excepted = out.updateAds(sourceId, sourceCreateAds, authentication);
        AdsDTO actual = getResultAdsDTO();

        assertEquals(excepted,actual);
    }

    @Test
    void updateAdsNegativeTest() {
        UserEntity author = getAuthor();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);
        lenient().when(adsRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ElemNotFound.class, () -> out.updateAds(1, getCreateAds(),authentication));
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

    private Authentication getTestAuthentication() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        UserEntity author = getAuthor();
        return new TestingAuthenticationToken(author.getEmail(), author.getPassword(), authorities);
    }
}