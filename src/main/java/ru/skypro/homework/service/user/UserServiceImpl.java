package ru.skypro.homework.service.user;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.UserRepository;


@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
