package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.User;

import java.util.Collection;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    Collection<AdEntity> findAdEntitiesByAuthor(User author);
}
