package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findAllBy();

    Ad getReferenceById(int id);

    Ad findById(int id);

    @Modifying
    @Query("delete from Ad b where b.id=:id")
    void deleteById(int id);
    Ad saveAndFlush(Ad ad);
}
