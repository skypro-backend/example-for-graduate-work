package ru.skypro.homework.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.db.model.AdModel;
import ru.skypro.homework.db.model.UserModel;

import java.util.List;

public interface AdRepository extends JpaRepository<AdModel, Integer> {
    List<AdModel> findAllByAuthor(UserModel author);
}
