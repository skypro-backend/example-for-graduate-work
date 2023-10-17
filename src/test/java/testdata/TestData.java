package testdata;

import org.junit.jupiter.params.provider.Arguments;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.projection.CreateOrUpdateAd;

import java.util.stream.Stream;



public class TestData {
    public static final CreateOrUpdateAd CORRECT_CREATE_OR_UPDATE_AD_TEST = new CreateOrUpdateAd("test obj", 666, "test obj");
    public static final User CORRECT_USER_TEST = new User()
            .setRole(Role.USER)
            .setPhone("76669996699")
            .setLastName("UserTest")
            .setEmail("testuser@mail.ru")
            .setPassword("$2a$10$55xAjng95Q4KZaAN5thfCuBzCvCz57We0EfaZ2cW2CbleJ6xcbRma")
            .setFirstName("TestUser");
    public static final User CORRECT_ADMIN_TEST = new User()
            .setRole(Role.ADMIN)
            .setPhone("79996669966")
            .setLastName("AdminTest")
            .setEmail("testadmin@mail.ru")
            .setPassword("$2a$10$55xAjng95Q4KZaAN5thfCuBzCvCz57We0EfaZ2cW2CbleJ6xcbRma")
            .setFirstName("TestAdmin");
    public static final Ad CORRECT_AD_TEST = new Ad()
            .setUser(CORRECT_USER_TEST)
            .setPrice(666)
            .setTitle("TestAd");
    public static Stream<Arguments> provideIncorrectParamsForCreateAdTestMethod() {
        return Stream.of(
                Arguments.of(new CreateOrUpdateAd("", 1, "test obj")),
                Arguments.of(new CreateOrUpdateAd(" ".repeat(33), 1, "test obj")),
                Arguments.of(new CreateOrUpdateAd("test obj", -1, "test obj")),
                Arguments.of(new CreateOrUpdateAd("test obj", 10_000_001, "test obj")),
                Arguments.of(new CreateOrUpdateAd("test obj", 1, "")),
                Arguments.of(new CreateOrUpdateAd("test obj", 1, " ".repeat(33))
                ));
    }
}
