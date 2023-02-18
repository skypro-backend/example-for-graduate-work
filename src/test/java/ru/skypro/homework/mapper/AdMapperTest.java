package ru.skypro.homework.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@SpringBootTest
public class AdMapperTest {

  @Autowired
  private AdMapper adMapper;

  @Test
  void toCreateAds() {
    CreateAds createAds = adMapper.toCreateAds(getAd());
    FullAds fullAds = adMapper.toFullAds(getAd(), getUser(), getImage());
    assertNotNull(createAds);
    assertEquals(createAds.getPrice(), getAd().getPrice());
    assertEquals(createAds.getTitle(), getAd().getTitle());
    assertEquals(createAds.getDescription(), getAd().getDescription());
    assertNotNull(fullAds);

    assertEquals(fullAds.getPrice(), getAd().getPrice());
    assertEquals(fullAds.getTitle(), getAd().getTitle());
    assertEquals(fullAds.getEmail(), getAd().getAuthor().getEmail());
    assertEquals(fullAds.getPhone(), getAd().getAuthor().getPhone());
    assertEquals(fullAds.getAuthorFirstName(), getAd().getAuthor().getFirstName());
    assertEquals(fullAds.getAuthorLastName(), getAd().getAuthor().getLastName());
    assertEquals(fullAds.getPk(), getAd().getAuthor().getId());
    assertEquals(fullAds.getDescription(), getAd().getDescription());
    assertEquals(fullAds.getImage(), List.of(getAd().getAuthor().getImage()));
  }

  @Test
  void toDTO() {

    AdsDTO adsDTO = new AdsDTO();
    adsDTO.setPk(getAdEntity().getId());
    adsDTO.setTitle(getAdEntity().getTitle());
    adsDTO.setPrice(getAdEntity().getPrice());
    adsDTO.setAuthor(getAdEntity().getAuthor().getId());
    adsDTO.setImage(getAdEntity().getImageEntities()
        .stream()
        .map(ImageEntity::getPath)
        .collect(Collectors.toList()));

    AdsDTO actual = adMapper.toDTO(getAdEntity());
    assertEquals(adsDTO, actual);

  }

  @Test
  void toEntity() {

    AdEntity adEntity = new AdEntity();
    adEntity.setId(getAdsDTO().getPk());
    adEntity.setTitle(getAdsDTO().getTitle());
    adEntity.setPrice(getAdsDTO().getPrice());
    adEntity.setDescription("Неполная реклама");

    UserEntity userEntity = new UserEntity();
    userEntity.setId(getAdsDTO().getAuthor());
    adEntity.setAuthor(userEntity);

    List<ImageEntity> imageEntities = new ArrayList<>();
    for (String s : getAdsDTO().getImage()) {
      ImageEntity imageEntity = new ImageEntity();
      imageEntity.setPath(s);
      imageEntities.add(imageEntity);
    }
    adEntity.setImageEntities(imageEntities);
    adEntity.setCommentEntities(new ArrayList<>());

    AdEntity actual = adMapper.toEntity(getAdsDTO());
    assertEquals(adEntity, actual);

  }

  private AdEntity getAdEntity() {
    AdEntity adEntity = new AdEntity();
    List<ImageEntity> imageEntities = new ArrayList<>();
    ImageEntity imageEntity = new ImageEntity(1, "/path/to/image/1", new AdEntity());
    ImageEntity imageEntity1 = new ImageEntity(2, "/path/to/image/2", new AdEntity());
    ImageEntity imageEntity2 = new ImageEntity(3, "/path/to/image/3", new AdEntity());
    imageEntities.add(imageEntity);
    imageEntities.add(imageEntity1);
    imageEntities.add(imageEntity2);
    adEntity.setId(1);
    adEntity.setTitle("afsdf");
    adEntity.setPrice(123);
    adEntity.setDescription("asfsdf");
    adEntity.setAuthor(new UserEntity());
    adEntity.setImageEntities(imageEntities);
    return adEntity;
  }

  private AdsDTO getAdsDTO() {
    AdsDTO adsDTO = new AdsDTO();
    List<String> images = new ArrayList<>();
    String images1 = "/path/to/image/1";
    String images2 = "/path/to/image/2";
    String images3 = "/path/to/image/3";
    images.add(images1);
    images.add(images2);
    images.add(images3);
    adsDTO.setPk(1);
    adsDTO.setTitle("afsdf");
    adsDTO.setPrice(123);
    adsDTO.setAuthor(1);
    adsDTO.setImage(images);
    return adsDTO;
  }


  private AdEntity getAd() {
    UserEntity userEntity = new UserEntity(1, "firstname", "lastname", "email@.mail.ru",
        "+79999992211",
        LocalDateTime.now(), "nsk", "/path/to/image/1", null, null);
    ImageEntity imageEntity = new ImageEntity(1, "/path/to/image/1", new AdEntity());
    AdEntity adEntity = new AdEntity();
    adEntity.setId(1);
    adEntity.setAuthor(userEntity);
    adEntity.setPrice(100);
    adEntity.setTitle("title");
    adEntity.setDescription("description");
    adEntity.setCommentEntities(List.of(new CommentEntity()));
    adEntity.setImageEntities(List.of(imageEntity));
    return adEntity;
  }

  private UserEntity getUser() {
    return new UserEntity(1, "firstname", "lastname", "email@.mail.ru", "+79999992211",
        LocalDateTime.now(), "nsk", "/path/to/image/1", null, null);
  }

  private ImageEntity getImage() {
    return new ImageEntity(1, "/path/to/image/1", new AdEntity());
  }




}
