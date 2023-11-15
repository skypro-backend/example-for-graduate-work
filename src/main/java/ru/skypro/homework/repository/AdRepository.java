package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Long> {

    List<AdEntity> findAllByUserRelated(int id);

    AdEntity getReferenceById(int id);

    AdEntity findById(int id);

    @Modifying
    @Query("delete from AdEntity b where b.id=:id")
    void deleteById(int id);
    AdEntity saveAndFlush(AdEntity ad);
}
