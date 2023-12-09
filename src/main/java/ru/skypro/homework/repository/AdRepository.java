package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    Ad findByPk(int id);

    List<Ad> findAllByAuthor_username(String username);
}
