package ru.skypro.kakavito.mappers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.Image;
import ru.skypro.kakavito.model.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdMapperTest {

    private final AdMapper adMapper = new AdMapper(new ModelMapper());

    @Test
    void convertToAdDTO_ShouldMapAdEntityToAdDto() {
        Ad ad = new Ad();
        ad.setId(1);
        ad.setTitle("Title");
        ad.setDescription("Description");
        ad.setPrice(100);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.ru");
        ad.setUser(user);

        AdDTO adDTO = adMapper.convertToAdDTO(ad);

        assertEquals(adDTO.getPk(), adDTO.getPk());
        assertEquals(adDTO.getTitle(), adDTO.getTitle());
        assertEquals(adDTO.getAuthor(), adDTO.getPk(), adDTO.getAuthor());
        assertEquals(adDTO.getPrice(), adDTO.getPrice());
    }

    @Test
    void convertToAdsDto_ShouldMapCollectionOfAdEntitiesToAdsDto() {
        Ad ad1 = new Ad();
        ad1.setId(1);
        ad1.setTitle("Ad 1");
        ad1.setPrice(50);
        User user1 = new User();
        user1.setId(1L);
        ad1.setUser(user1);

        Ad ad2 = new Ad();
        ad2.setId(2);
        ad2.setTitle("Ad 2");
        ad2.setPrice(75);
        User user2 = new User();
        user2.setId(2L);
        ad2.setUser(user2);

        List<Ad> adList = Arrays.asList(ad1, ad2);

        AdsDTO adsDTO = adMapper.convertToAdsDTO(adList);

        List<AdDTO> adDTOList = adsDTO.getResults();
        assertEquals(adList.size(), adsDTO.getCount());
        assertEquals(adList.size(), adDTOList.size());
    }

    @Test
    void convertToUpdateOrCreateDTO_ShouldMapCreateOrUpdateAdToAdEntity() {
        CreateOrUpdateAdDTO createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        createOrUpdateAdDTO.setTitle("Title");
        createOrUpdateAdDTO.setDescription("Description");
        createOrUpdateAdDTO.setPrice(100);

        Ad ad = adMapper.convertCreatDTOToAd(createOrUpdateAdDTO);

        assertEquals(createOrUpdateAdDTO.getTitle(), ad.getTitle());
        assertEquals(createOrUpdateAdDTO.getDescription(), ad.getDescription());
        assertEquals(createOrUpdateAdDTO.getPrice(), ad.getPrice());
    }

    @Test
    void convertToExtendedDTO_ShouldMapAdEntityToExtendedAdDto() {
        Image image = new Image();
        Ad ad = new Ad();
        User user = new User();

        image.setId(1);
        image.setAd(ad);
        image.setUser(user);
        image.setData(image.getData());
        image.setFileSize(image.getFileSize());
        image.setFilePath(image.getFilePath());

        ad.setId(1);
        ad.setTitle("Title");
        ad.setDescription("Description");
        ad.setPrice(100);
        ad.setImage(image);

        user.setId(1L);
        user.setEmail("test@mail.ru");
        user.setFirstName("Name");
        user.setLastName("LastName");
        user.setPhone("123-456-7890");
        ad.setUser(user);

        ExtendedAdDTO extendedAdDTO = adMapper.convertToExtendedAd(ad);

        assertEquals(ad.getId(), extendedAdDTO.getPk());
        assertEquals(ad.getTitle(), extendedAdDTO.getTitle());
        assertEquals(ad.getDescription(), extendedAdDTO.getDescription());
        assertEquals(ad.getPrice(), extendedAdDTO.getPrice());
        assertEquals(ad.getImage(), extendedAdDTO.getImage());
        assertEquals(user.getFirstName(), extendedAdDTO.getAuthorFirstName());
        assertEquals(user.getLastName(), extendedAdDTO.getAuthorLastName());
        assertEquals(user.getEmail(), extendedAdDTO.getEmail());
        assertEquals(user.getPhone(), extendedAdDTO.getPhone());
    }
}
