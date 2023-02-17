package ru.skypro.homework.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@SpringBootTest
class AdMapperTest {

  @Autowired
  private AdMapper adMapper;


  @Test
  void toDTO() {
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

  AdEntity getAd() {
    UserEntity userEntity = new UserEntity(1, "firstname", "lastname", "email@.mail.ru", "+79999992211",
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

  UserEntity getUser() {
    return new UserEntity(1, "firstname", "lastname", "email@.mail.ru", "+79999992211",
        LocalDateTime.now(), "nsk", "/path/to/image/1", null, null);
  }

  ImageEntity getImage() {
    return new ImageEntity(1, "/path/to/image/1", new AdEntity());
  }



}
