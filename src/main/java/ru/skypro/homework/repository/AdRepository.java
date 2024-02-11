package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Long> {
    @Query(value = "SELECT * FROM ads WHERE title LIKE '%title%'", nativeQuery = true)
    List<AdEntity> findByTitleLikeIgnoreCase(@Param("title") String title);

    Collection<AdEntity> findAllAdsByAuthorId(long userId);
}
