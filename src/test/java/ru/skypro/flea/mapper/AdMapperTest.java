package ru.skypro.flea.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.flea.dto.AdDto;
import ru.skypro.flea.dto.AdsDto;
import ru.skypro.flea.dto.CreateOrUpdateAdDto;
import ru.skypro.flea.dto.ExtendedAdDto;
import ru.skypro.flea.model.Ad;
import ru.skypro.flea.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdMapperTest {

    private static Ad defaultAd;

    private final AdMapper mapper = Mappers.getMapper(AdMapper.class);

    @BeforeAll
    public static void fillDefaultAd() {
        User defaultAuthor = new User();
        defaultAuthor.setId(123);
        defaultAuthor.setFirstName("John");
        defaultAuthor.setLastName("Brown");
        defaultAuthor.setEmail("john.brown2007@example.com");
        defaultAuthor.setPhone("+0(000)000-00-00");

        defaultAd = new Ad();
        defaultAd.setUser(defaultAuthor);
        defaultAd.setImage("https://imagehostingservice.org/4jhf1n31.png");
        defaultAd.setId(456);
        defaultAd.setPrice(100500);
        defaultAd.setTitle("Dubstep machine");
        defaultAd.setDescription("Simple to use, very affordable");
    }

    @Test
    public void adToAdDtoMappingTest() {
        AdDto dto = mapper.toAdDto(defaultAd);

        assertEntityEqualsToDto(defaultAd, dto);
    }

    @Test
    public void emptyAdCollectionToAdDtoListMappingTest() {
        Collection<Ad> emptyEntityList = Collections.emptyList();

        List<AdDto> dtoList = mapper.toAdDtoList(emptyEntityList);

        assertTrue(dtoList.isEmpty());
    }

    @Test
    public void adCollectionToAdDtoListMappingTest() {
        Collection<Ad> entityList = Collections.singletonList(defaultAd);

        List<AdDto> dtoList = mapper.toAdDtoList(entityList);

        assertEquals(dtoList.size(), 1);
        assertEntityEqualsToDto(defaultAd, dtoList.get(0));
    }

    @Test
    public void adCollectionToAdsDtoMappingTest() {
        Collection<Ad> entityList = Collections.singletonList(defaultAd);

        AdsDto dto = mapper.toAdsDto(entityList);

        assertEquals(dto.getCount(), 1);
        assertEquals(dto.getResults().size(), 1);
        assertEntityEqualsToDto(defaultAd, dto.getResults().get(0));
    }

    @Test
    public void adToExtendedAdDtoMappingTest() {
        ExtendedAdDto dto = mapper.toExtendedAdDto(defaultAd);

        assertEntityEqualsToExtendedDto(defaultAd, dto);
    }

    @Test
    public void creatingAdFromCreateOrUpdateAdDtoTest() {
        CreateOrUpdateAdDto dto = new CreateOrUpdateAdDto();
        dto.setTitle("Title");
        dto.setPrice(1);
        dto.setDescription("Description");
        String imageLink = "https://imagehostingservice.org/fjh32bn.png";

        Ad entity = mapper.createAdFromDto(imageLink, dto);
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getPrice(), dto.getPrice());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getImage(), imageLink);
    }

    @Test
    public void updatingAdFromCreateOrUpdateAdDtoTest() {
        Ad entity = new Ad();
        entity.setTitle("Old title");
        entity.setPrice(1);
        entity.setDescription("Old description");

        CreateOrUpdateAdDto dto = new CreateOrUpdateAdDto();
        dto.setTitle("New title");
        dto.setPrice(2);
        dto.setDescription("New description");

        mapper.updateAdFromDto(entity, dto);

        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getPrice(), dto.getPrice());
        assertEquals(entity.getDescription(), dto.getDescription());
    }

    private void assertEntityEqualsToDto(Ad entity, AdDto dto) {
        assertEquals(dto.getAuthor(), entity.getUser().getId());
        assertEquals(dto.getImage(), entity.getImage());
        assertEquals(dto.getPk(), entity.getId());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

    private void assertEntityEqualsToExtendedDto(Ad entity, ExtendedAdDto dto) {
        assertEquals(dto.getPk(), entity.getId());
        assertEquals(dto.getAuthorFirstName(), entity.getUser().getFirstName());
        assertEquals(dto.getAuthorLastName(), entity.getUser().getLastName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getEmail(), entity.getUser().getEmail());
        assertEquals(dto.getImage(), entity.getImage());
        assertEquals(dto.getPhone(), entity.getUser().getPhone());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

}
