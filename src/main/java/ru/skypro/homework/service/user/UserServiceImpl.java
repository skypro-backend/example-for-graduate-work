package ru.skypro.homework.service.user;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.user.UserService;

@Data
@Service
public class UserServiceImpl implements UserService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


}
