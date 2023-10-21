package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;
@Repository
public interface AdRepo extends JpaRepository<AdModel, Integer> {
}
