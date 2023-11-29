package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ru.skypro.homework.model.Images;

import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {

    Optional<Images> findById(@Nullable Long id);
}
