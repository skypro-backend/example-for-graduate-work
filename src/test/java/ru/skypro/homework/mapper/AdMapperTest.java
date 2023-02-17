package ru.skypro.homework.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@SpringBootTest
public class AdMapperTest {

  private AdMapper adMapper;

  public AdMapperTest(AdMapper adMapper) {
    this.adMapper = adMapper;
  }

  @Test
  void toDTO() {
    UserEntity userEntity = new UserEntity(1, "name", "name 2", "email@mai.ru", "+79999993322", LocalDateTime.now(),
        "nsk", "/path/to", List.of(new AdEntity()), List.of(new CommentEntity()));
    CommentEntity commentEntity = new CommentEntity(1,userEntity, LocalDateTime.now(), new AdEntity(), "text");
    ImageEntity imageEntity = new ImageEntity(1, "/path/to", new AdEntity());
    AdEntity adEntity = new AdEntity(1, new UserEntity(), 100, "title",
        "new", List.of(commentEntity), List.of(new ImageEntity()));

    CreateAds createAds = adMapper.toCreateAds(adEntity);
    assertNotNull(createAds);
    assertEquals(createAds.getPrice(), adEntity.getPrice());
    assertEquals(createAds.getTitle(), adEntity.getTitle());
    assertEquals(createAds.getDescription(), adEntity.getDescription());
  }



}
