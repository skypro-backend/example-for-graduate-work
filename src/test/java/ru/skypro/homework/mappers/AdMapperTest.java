package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.users.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdMapperTest {
    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Test
    public void shouldMapToAd() {

        //given
        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto();
        createOrUpdateAdDto.setTitle("title");
        createOrUpdateAdDto.setPrice(25000);
        createOrUpdateAdDto.setDescription("description");

        //when
        Ad ad = adMapper.toAd(createOrUpdateAdDto);
        System.out.println(ad);

        //then
        assertThat(ad).isNotNull();
        assertThat(ad.getTitle()).isEqualTo(createOrUpdateAdDto.getTitle());
        assertThat(ad.getPrice().intValue()).isEqualTo(createOrUpdateAdDto.getPrice());
        assertThat(ad.getDescription()).isEqualTo(createOrUpdateAdDto.getDescription());
        assertThat(ad.getPk()).isNull();
        assertThat(ad.getImage()).isNull();
        assertThat(ad.getAuthor()).isNull();

    }

    @Test
    void shouldMapToDto() {
        //given
        Ad ad = new Ad();
        ad.setPk(25);
        ad.setImage("path");
        ad.setTitle("title");
        ad.setPrice(5000);
        ad.setDescription("description");
        User testAuthor = new User();
        testAuthor.setId(239);
        testAuthor.setFirstName("Ivan");
        testAuthor.setLastName("Ivanov");
        testAuthor.setPhone("phone");
        testAuthor.setEmail("email");
        testAuthor.setImage("avatar");
        testAuthor.setUsername("username");
        testAuthor.setPassword("password");
        ad.setAuthor(testAuthor);

        //when
        AdDto adDto = adMapper.toAdDto(ad);
        System.out.println(adDto);

        //then
        assertThat(adDto).isNotNull();
        assertThat(adDto.getPk().intValue()).isEqualTo(ad.getPk());
        assertThat(adDto.getImage()).isEqualTo(ad.getImage());
        assertThat(adDto.getTitle()).isEqualTo(ad.getTitle());
        assertThat(adDto.getPrice().intValue()).isEqualTo(ad.getPrice());
        assertThat(adDto.getAuthor().intValue()).isNotEqualTo(ad.getAuthor());
    }

    @Test
    void shouldMapToExtendedAdDto() {
        //given
        User testAuthor = new User();
        testAuthor.setId(128);
        testAuthor.setFirstName("Ivan");
        testAuthor.setLastName("Ivanov");
        testAuthor.setEmail("email");
        testAuthor.setPhone("phone");
        Ad ad = new Ad();
        ad.setPk(20);
        ad.setImage("path");
        ad.setTitle("title");
        ad.setPrice(32000);
        ad.setDescription("description");
        ad.setAuthor(testAuthor);

        //when
        ExtendedAdDto extendedAdDto = adMapper.toExtendedAdDto(ad);
        System.out.println(extendedAdDto);

        //then
        assertThat(extendedAdDto).isNotNull();
        assertThat(extendedAdDto.getPk().intValue()).isEqualTo(ad.getPk());
        assertThat(extendedAdDto.getAuthorFirstName()).isEqualTo(ad.getAuthor().getFirstName());
        assertThat(extendedAdDto.getAuthorLastName()).isEqualTo(ad.getAuthor().getLastName());
        assertThat(extendedAdDto.getDescription()).isEqualTo(ad.getDescription());
        assertThat(extendedAdDto.getEmail()).isEqualTo(ad.getAuthor().getEmail());
        assertThat(extendedAdDto.getImage()).isEqualTo(ad.getImage());
        assertThat(extendedAdDto.getPhone()).isEqualTo(ad.getAuthor().getPhone());
        assertThat(extendedAdDto.getPrice().intValue()).isEqualTo(ad.getPrice());
        assertThat(extendedAdDto.getTitle()).isEqualTo(ad.getTitle());
    }

    @Test
    void shouldMapToAdsDto() {
        //given
        User author1 = new User();
        author1.setId(333);
        Ad ad1 = new Ad();
        ad1.setImage("path to image of ad1");
        ad1.setPk(31);
        ad1.setPrice(45000);
        ad1.setTitle("Title of ad1");
        ad1.setAuthor(author1);

        User author2 = new User();
        author2.setId(13);
        Ad ad2 = new Ad();
        ad2.setImage("path to image of ad2");
        ad2.setPk(318);
        ad2.setPrice(411000);
        ad2.setTitle("Title of ad2");
        ad2.setAuthor(author1);
        List<Ad> adList = List.of(ad1, ad2);

        //when
        AdsDto adsDto = adMapper.toAdsDto(adList);
        System.out.println(adsDto);

        //then
        assertThat(adsDto).isNotNull();
        assertThat(adsDto.getCount()).isEqualTo(adList.size());
        assertThat(adsDto.getResults()).isNotNull();
    }
}