package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT a FROM Ad a JOIN FETCH a.author")
    List<Ad> findAllWithAuthor();

    @Query("SELECT a FROM Ad a JOIN FETCH a.author WHERE a.author.id = :authorId")
    List<Ad> findByAuthorIdWithAuthor(@Param("authorId") Long authorId);

    @Query("SELECT a FROM Ad a JOIN FETCH a.author WHERE a.id = :id")
    Optional<Ad> findByIdWithAuthor(@Param("id") Long id);
}
