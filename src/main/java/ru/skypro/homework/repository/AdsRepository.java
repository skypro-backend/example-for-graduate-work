package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comments;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long>{


    Ads findByNameLike(String title);
    Ads findByIdBetween(int from, int to);
    Ads getById();
    List<Comments> findByTitle(String comments);
    }


