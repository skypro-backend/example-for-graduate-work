package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.List;

public interface AdRepository extends JpaRepository <Ad, Integer>, JpaSpecificationExecutor <Ad> {
      Collection <Ad> findAllByAuthorId(Integer id);
      List <Ad> findByAuthor(User author);
      List<Ad> findByTitleContainingIgnoreCase(String title);
}