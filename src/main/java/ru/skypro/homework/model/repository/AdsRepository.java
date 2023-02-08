package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.Ads;

import java.util.Collection;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    Collection<Ads> findByTitleContainsOrderByTitle(String title);

    @Query(nativeQuery = true, value = "select user_id from ads where id = ?1")
    Integer getUserProfileId(Integer adsId);

    Collection<Ads> findByAuthorId(int authorId);

    void deleteAllById(Integer adsId);
}