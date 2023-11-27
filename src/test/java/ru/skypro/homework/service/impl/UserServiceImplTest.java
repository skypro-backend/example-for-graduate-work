package ru.skypro.homework.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.repository.UserRepository;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getCurrentUser()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentUser() {
 // ЗАДАЧА: завершить этот тест.
        // Причина: R013 Не найдено входных данных, которые бы не вызывали тривиальное исключение.
        // Diffblue Cover попыталась запустить раздел аранжировки/действия, но метод под
        // тест брошен
        // java.lang.NullPointerException: невозможно вызвать «org.springframework.security.core.Authentication.getName()», поскольку «auth» имеет значение null
        // на ru.skypro.homework.service.impl.UserServiceImpl.getCurrentUser(UserServiceImpl.java:31)

        userServiceImpl.getCurrentUser();
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UpdateUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser() {
// ЗАДАЧА: завершить этот тест.
        // Причина: R013 Не найдено входных данных, которые бы не вызывали тривиальное исключение.
        // Diffblue Cover попыталась запустить раздел аранжировки/действия, но метод под
        // тест брошен
        // java.lang.NullPointerException: невозможно вызвать «org.springframework.security.core.Authentication.getName()», поскольку «auth» имеет значение null
        // на ru.skypro.homework.service.impl.UserServiceImpl.updateUser(UserServiceImpl.java:38)

        userServiceImpl.updateUser(new UpdateUserDTO());
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(NewPasswordDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetPassword() {
// ЗАДАЧА: завершить этот тест.
        // Причина: R013 Не найдено входных данных, которые бы не вызывали тривиальное исключение.
        // Diffblue Cover попыталась запустить раздел аранжировки/действия, но метод под
        // тест брошен
        // java.lang.NullPointerException: невозможно вызвать «org.springframework.security.core.Authentication.getName()», поскольку «auth» имеет значение null
        // на ru.skypro.homework.service.impl.UserServiceImpl.setPassword(UserServiceImpl.java:46)

//        userServiceImpl.setPassword(new NewPasswordDTO());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUserImage(MultipartFile, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage() throws IOException {
// ЗАДАЧА: завершить этот тест.
        // Причина: R013 Не найдено входных данных, которые бы не вызывали тривиальное исключение.
        // Diffblue Cover попыталась запустить раздел аранжировки/действия, но метод под
        // тест брошен
        // java.lang.NullPointerException: невозможно вызвать «org.springframework.security.core.Authentication.getName()», поскольку «auth» имеет значение null
        // на ru.skypro.homework.service.impl.UserServiceImpl.updateUserImage(UserServiceImpl.java:69)

//        userServiceImpl
//                .updateUserImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }
}
