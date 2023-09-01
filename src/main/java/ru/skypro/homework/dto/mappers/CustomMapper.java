package ru.skypro.homework.dto.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.repository.users.UsersRepository;

@Component
public class CustomMapper {

    private final UsersRepository usersRepository;

    public CustomMapper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User mapToAuthor(Integer author) {
        return usersRepository.findById(author).orElse(null);
    }

}
