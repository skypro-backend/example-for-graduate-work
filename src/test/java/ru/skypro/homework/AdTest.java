package ru.skypro.homework;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapping.RegisterDtoToUserMapper;
import ru.skypro.homework.repository.UserRepository;

@SpringBootTest(classes = UserRepository.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Data
public class AdTest {

    @Autowired
    private final UserRepository userRepository;

    @Test
   public void test1() {

        RegisterDto registerDto = new RegisterDto("user@name.com", "password", "Jan", "Ambroz",
                "111-555-1234", Role.USER);

        System.out.println("registerDto = " + registerDto);

        System.out.println(RegisterDtoToUserMapper.INSTANCE.registerDtoToUserMapper(registerDto));

        System.out.println(userRepository.findAll());

    }

}
