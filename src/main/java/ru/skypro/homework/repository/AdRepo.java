package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Repository
public interface AdRepo extends JpaRepository <Ad, Integer> {

    List<Ad> findAllByAuthorId(Long author_id);

    Ad findByPk(int pk);
}
