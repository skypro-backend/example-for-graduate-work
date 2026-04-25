package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdServiceTest {

    @Autowired
    private AdService adService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;
    private UserEntity admin;
    private final String USER_EMAIL = "user@test.com";
    private final String ADMIN_EMAIL = "admin@test.com";

    private int phoneCounter = 10000;

    @BeforeEach
    void setUp() {
        adRepository.deleteAll();
        userRepository.deleteAll();
        phoneCounter = 10000;

        user = createUser(USER_EMAIL, "User", Role.USER);
        admin = createUser(ADMIN_EMAIL, "Admin", Role.ADMIN);
    }

    private UserEntity createUser(String email, String name, Role role) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword("$2a$10$dwvtj/wz45H8SI7M3.7t.eoJw9pOx6oKq7r9qZ.zQnL3u1kZ9fWVW");
        user.setFirstName(name);
        user.setLastName("Test");
        user.setPhone("+7999" + (phoneCounter++));
        user.setRole(role);
        return userRepository.save(user);
    }

    private AdEntity createAd(String title, Integer price, UserEntity author) {
        AdEntity ad = new AdEntity();
        ad.setTitle(title);
        ad.setPrice(price);
        ad.setAuthor(author);
        ad.setDescription("Test description");
        ad.setCreatedAt(java.time.LocalDateTime.now());
        return adRepository.save(ad);
    }

    private void authenticate(String email, UserEntity user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(email).password(user.getPassword()).roles(user.getRole().name()).build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }

    @Test
    void getAllAds_ReturnsAllAds() {
        createAd("Ad 1", 100, user);
        createAd("Ad 2", 200, user);

        var result = adService.getAllAds();

        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertTrue(result.getResults().stream().anyMatch(a -> a.getTitle().equals("Ad 1")));
        assertTrue(result.getResults().stream().anyMatch(a -> a.getTitle().equals("Ad 2")));
    }

    @Test
    void getExtendedAd_ExistingId_ReturnsExtendedAd() {
        AdEntity ad = createAd("Extended", 500, user);

        ExtendedAd result = adService.getExtendedAd(ad.getPk());

        assertNotNull(result);
        assertEquals("Extended", result.getTitle());
        assertEquals("Test description", result.getDescription());
        assertEquals("user@test.com", result.getEmail());
        assertEquals("/users/" + user.getId() + "/image", result.getAuthorImage());
    }

    @Test
    void createAd_ValidData_CreatesAndReturnsAd() {
        CreateOrUpdateAd dto = new CreateOrUpdateAd();
        dto.setTitle("New Ad");
        dto.setPrice(1000);
        dto.setDescription("New Description");

        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(dto.getTitle());
        adEntity.setPrice(dto.getPrice());
        adEntity.setDescription(dto.getDescription());

        authenticate(USER_EMAIL, user);

        Ad created = adService.createAd(adEntity, USER_EMAIL);

        assertNotNull(created);
        assertEquals("New Ad", created.getTitle());
        assertEquals(1000, created.getPrice());
        assertEquals(user.getId(), created.getAuthor());
        assertTrue(adRepository.findById(created.getPk()).isPresent());
    }

    @Test
    void deleteAd_ByOwner_DeletesSuccessfully() {
        AdEntity ad = createAd("To delete", 100, user);
        authenticate(USER_EMAIL, user);

        assertDoesNotThrow(() -> adService.deleteAd(ad.getPk(), USER_EMAIL));
        assertFalse(adRepository.findById(ad.getPk()).isPresent());
    }

    @Test
    void deleteAd_ByOtherUser_ThrowsAccessDenied() {
        AdEntity ad = createAd("Owner's Ad", 100, user);
        authenticate("other@test.com", user);

        Exception exception = assertThrows(AccessDeniedException.class, () -> adService.deleteAd(ad.getPk(), "other@test.com"));
        assertTrue(exception.getMessage().contains("прав"));
    }

    @Test
    void deleteAd_ByAdmin_DeletesSuccessfully() {
        AdEntity ad = createAd("To delete by admin", 100, user);
        authenticate(ADMIN_EMAIL, admin);

        assertDoesNotThrow(() -> adService.deleteAd(ad.getPk(), ADMIN_EMAIL));
        assertFalse(adRepository.findById(ad.getPk()).isPresent());
    }

    @Test
    void updateAd_ByOwner_UpdatesSuccessfully() {
        AdEntity ad = createAd("Old", 100, user);
        AdEntity updatedEntity = new AdEntity();
        updatedEntity.setTitle("Updated");
        updatedEntity.setPrice(150);
        updatedEntity.setDescription("Updated desc");

        authenticate(USER_EMAIL, user);

        Ad result = adService.updateAd(ad.getPk(), updatedEntity, USER_EMAIL);

        assertEquals("Updated", result.getTitle());
        assertEquals(150, result.getPrice());
    }

    @Test
    void updateAd_ByAdmin_UpdatesSuccessfully() {
        AdEntity ad = createAd("Old", 100, user);
        AdEntity update = new AdEntity();
        update.setTitle("Admin updated");
        update.setPrice(999);

        authenticate(ADMIN_EMAIL, admin);

        Ad result = adService.updateAd(ad.getPk(), update, ADMIN_EMAIL);

        assertEquals("Admin updated", result.getTitle());
        assertEquals(999, result.getPrice());
    }

    @Test
    void getAdsByUser_UserHasAds_ReturnsTheirAds() {
        createAd("User Ad 1", 100, user);
        createAd("User Ad 2", 200, user);

        var result = adService.getAdsByUser(USER_EMAIL);

        assertNotNull(result);
        assertEquals(2, result.getCount());
    }

    @Test
    void updateImage_ValidImage_UpdatesImage() {
        AdEntity ad = createAd("Image Ad", 100, user);
        byte[] image = "fake-image".getBytes();

        Ad result = adService.updateImage(ad.getPk(), image);

        assertNotNull(result);
        assertEquals("/ads/" + ad.getPk() + "/image", result.getImage());
    }
}