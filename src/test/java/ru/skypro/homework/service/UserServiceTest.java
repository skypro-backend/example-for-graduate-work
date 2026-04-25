package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserEntity user;
    private final String IMAGE_DIR = "src/main/resources/images/user/";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        user = new UserEntity();
        user.setEmail("user@test.com");
        user.setPassword(passwordEncoder.encode("password123"));
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPhone("+79991234567");
        user.setRole(Role.USER);
        user.setImagePath("/users/1/image");

        user = userRepository.save(user);
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void getUser_ValidEmail_ReturnsUser() {
        // when
        User result = userService.getUser("user@test.com");

        // then
        assertNotNull(result);
        assertEquals("user@test.com", result.getEmail());
        assertEquals("Ivan", result.getFirstName());
        assertEquals("Ivanov", result.getLastName());
        assertEquals("+79991234567", result.getPhone());
        assertEquals(Role.USER, result.getRole());
        assertEquals("/users/1/image", result.getImage());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateUser_ValidData_UpdatesUser() {
        // given
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Petr");
        updateUser.setLastName("Petrov");
        updateUser.setPhone("+79997654321");

        // when
        User updated = userService.updateUser("user@test.com", updateUser);

        // then
        assertNotNull(updated);
        assertEquals("Petr", updated.getFirstName());
        assertEquals("Petrov", updated.getLastName());
        assertEquals("+79997654321", updated.getPhone());

        UserEntity saved = userRepository.findByEmail("user@test.com").orElse(null);
        assertNotNull(saved);
        assertEquals("Petr", saved.getFirstName());
        assertEquals("Petrov", saved.getLastName());
        assertEquals("+79997654321", saved.getPhone());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updatePassword_ValidCurrentPassword_ChangesPassword() {
        // given
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("password123");
        newPassword.setNewPassword("newpass123");

        // when
        assertDoesNotThrow(() -> userService.updatePassword("user@test.com", newPassword));

        // then
        UserEntity updatedUser = userRepository.findByEmail("user@test.com").orElse(null);
        assertNotNull(updatedUser);
        assertTrue(passwordEncoder.matches("newpass123", updatedUser.getPassword()));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updatePassword_InvalidCurrentPassword_ThrowsException() {
        // given
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("wrongpass");
        newPassword.setNewPassword("newpass123");

        // when + then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.updatePassword("user@test.com", newPassword));
        assertEquals("Current password is incorrect", exception.getMessage());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateImage_ValidImage_SavesToFileAndUpdatesPath() throws Exception {
        // given
        byte[] image = "fake-image-data".getBytes();
        Path imagePath = Paths.get(IMAGE_DIR).resolve("1.jpg");

        // ensure directory exists
        Files.createDirectories(Paths.get(IMAGE_DIR));

        // when
        assertDoesNotThrow(() -> userService.updateImage("user@test.com", image));

        // then
        assertTrue(Files.exists(imagePath));
        byte[] savedImage = Files.readAllBytes(imagePath);
        assertArrayEquals(image, savedImage);

        UserEntity updatedUser = userRepository.findByEmail("user@test.com").orElse(null);
        assertNotNull(updatedUser);
        assertEquals("/users/1/image", updatedUser.getImagePath());
    }
}