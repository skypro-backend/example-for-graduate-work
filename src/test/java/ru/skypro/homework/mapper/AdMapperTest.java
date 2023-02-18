package ru.skypro.homework.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@SpringBootTest
public class AdMapperTest {

  @Autowired
  private AdMapper adMapper;

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

}
