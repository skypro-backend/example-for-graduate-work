package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ru.skypro.homework.model.PhotoAd;

import java.util.Optional;

public interface PhotoAdRepository extends JpaRepository<PhotoAd, Long> {

    Optional<PhotoAd> findById(@Nullable Long id);
}
