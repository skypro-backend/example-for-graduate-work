package ru.skypro.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_Exists_ReturnsUser() {
        UserEntity user = new UserEntity();
        user.setEmail("test@test.com");
        user.setPassword("encoded-pass");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPhone("+79991234567");
        user.setRole(ru.skypro.homework.entity.Role.USER);

        userRepository.save(user);

        Optional<UserEntity> found = userRepository.findByEmail("test@test.com");

        assertTrue(found.isPresent());
        assertEquals("Ivan", found.get().getFirstName());
        assertEquals("Ivanov", found.get().getLastName());
    }

    @Test
    void findByEmail_NotExists_ReturnsEmpty() {
        Optional<UserEntity> found = userRepository.findByEmail("unknown@test.com");

        assertFalse(found.isPresent());
    }
}