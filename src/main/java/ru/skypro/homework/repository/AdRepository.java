package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface AdRepository extends CrudRepository<Ad, Integer> {
    Ad findByPk(int id);

    List<Ad> findAllByAuthor_username(String username);
}
