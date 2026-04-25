package ru.skypro.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class AdRepositoryTest {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByAuthorId_AdsExist_ReturnsAdsList() {
        UserEntity author = new UserEntity();
        author.setEmail("author@test.com");
        author.setPassword("pass");
        author.setRole(ru.skypro.homework.entity.Role.USER);
        author = userRepository.save(author);

        AdEntity ad1 = new AdEntity();
        ad1.setTitle("Laptop");
        ad1.setPrice(1000);
        ad1.setDescription("Good condition");
        ad1.setAuthor(author);
        ad1.setCreatedAt(LocalDateTime.now());

        AdEntity ad2 = new AdEntity();
        ad2.setTitle("Phone");
        ad2.setPrice(500);
        ad2.setDescription("Like new");
        ad2.setAuthor(author);
        ad2.setCreatedAt(LocalDateTime.now());

        adRepository.save(ad1);
        adRepository.save(ad2);

        List<AdEntity> ads = adRepository.findByAuthorId(author.getId());

        assertNotNull(ads);
        assertEquals(2, ads.size());
        assertTrue(ads.stream().anyMatch(a -> a.getTitle().equals("Laptop")));
        assertTrue(ads.stream().anyMatch(a -> a.getTitle().equals("Phone")));
    }

    @Test
    void findByAuthorId_NoAds_ReturnsEmptyList() {
        UserEntity author = new UserEntity();
        author.setEmail("author@test.com");
        author.setPassword("pass");
        author.setRole(ru.skypro.homework.entity.Role.USER);
        author = userRepository.save(author);

        List<AdEntity> ads = adRepository.findByAuthorId(author.getId());

        assertNotNull(ads);
        assertTrue(ads.isEmpty());
    }
}