package ru.skypro.homework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import ru.skypro.homework.api.dto.Ad;
import ru.skypro.homework.api.dto.CreateOrUpdateAd;
import ru.skypro.homework.db.model.AdModel;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.model.UserRole;
import ru.skypro.homework.db.repository.AdRepository;
import ru.skypro.homework.db.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AdServiceTest {

    @Autowired
    private AdService adService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void userCanNotUpdateAnotherUserAdButAdminCan() {
        UserModel owner = createUser("owner");
        UserModel anotherUser = createUser("another");
        AdModel ad = createAd(owner);
        CreateOrUpdateAd updateAd = createUpdateAd();

        assertThrows(AccessDeniedException.class,
                () -> adService.updateAd(anotherUser.getEmail(), ad.getId(), updateAd));

        Ad updatedAd = adService.updateAd("admin@gmail.com", ad.getId(), updateAd);

        assertEquals("Updated title", updatedAd.getTitle());
        assertEquals(1500, updatedAd.getPrice());
    }

    private UserModel createUser(String prefix) {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        return userRepository.save(UserModel.builder()
                .email(prefix + unique + "@gmail.com")
                .password("password")
                .firstName("User")
                .lastName("Userov")
                .phone("+7 999 111-22-33")
                .role(UserRole.USER)
                .build());
    }

    private AdModel createAd(UserModel owner) {
        return adRepository.save(AdModel.builder()
                .author(owner)
                .title("Test title")
                .price(1000)
                .description("Test description")
                .image("/ads/0/image")
                .build());
    }

    private CreateOrUpdateAd createUpdateAd() {
        CreateOrUpdateAd updateAd = new CreateOrUpdateAd();
        updateAd.setTitle("Updated title");
        updateAd.setPrice(1500);
        updateAd.setDescription("Updated description");
        return updateAd;
    }
}
