package ru.skypro.homework.service.users.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


}
