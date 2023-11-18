package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.UserRepository;

//@Service
public class CleanService {
    UserRepository userRepository;

    public CleanService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CleanService() {
    }

}
