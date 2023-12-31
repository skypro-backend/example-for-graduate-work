package ru.skypro.kakavito.mappers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.model.Ad;
import ru.skypro.kakavito.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class AdMapperTest {

    private final AdMapper adMapper;

    @Test
    void convertToAdDTO_ShouldMapAdEntityToAdDto() {
        Ad ad = new Ad();
        ad.setPk(1);
        ad.setTitle("Title");
        ad.setDescription("Description");
        ad.setPrice(100);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.ru");
        ad.setAuthor(user);

        AdDTO adDto = adMapper.convertToAdDTO(ad);

        assertEquals(adDto.getPk(), adDto.getPk());
        assertEquals(adDto.getTitle(), adDto.getTitle());
        assertEquals(adDto.getAuthor(), adDto.getPk(), adDto.getAuthor());
        assertEquals(adDto.getPrice(), adDto.getPrice());
    }

    @Test
    void convertToAdsDto_ShouldMapCollectionOfAdEntitiesToAdsDto() {
        Ad ad1 = new Ad();
        ad1.setPk(1);
        ad1.setTitle("Ad 1");
        ad1.setPrice(50);
        User user1 = new User();
        user1.setId(1L);
        ad1.setAuthor(user1);

        Ad ad2 = new Ad();
        ad2.setPk(2);
        ad2.setTitle("Ad 2");
        ad2.setPrice(75);
        User user2 = new User();
        user2.setId(2L);
        ad2.setAuthor(user2);

        Collection<Ad> adList = Arrays.asList(ad1, ad2);

        AdsDTO adsDto = adMapper.convertToAdsDTO((List<Ad>) adList);

        List<AdDTO> adDtoList = adsDto.getResults();
        assertEquals(adList.size(), adsDto.getCount());
        assertEquals(adList.size(), adDtoList.size());
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
        Ad ad = new Ad();
        ad.setPk(1);
        ad.setTitle("Title");
        ad.setDescription("Description");
        ad.setPrice(100);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.ru");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("123-456-7890");
        ad.setAuthor(user);

        ExtendedAdDTO extendedAdDTO = adMapper.convertToExtendedAd(ad);

        assertEquals(ad.getPk(), extendedAdDTO.getPk());
        assertEquals(ad.getTitle(), extendedAdDTO.getTitle());
        assertEquals(ad.getDescription(), extendedAdDTO.getDescription());
        assertEquals(ad.getPrice(), extendedAdDTO.getPrice());
        assertEquals(user.getFirstName(), extendedAdDTO.getAuthorFirstName());
        assertEquals(user.getLastName(), extendedAdDTO.getAuthorLastName());
        assertEquals(user.getEmail(), extendedAdDTO.getEmail());
        assertEquals(user.getPhone(), extendedAdDTO.getPhone());
    }
}
