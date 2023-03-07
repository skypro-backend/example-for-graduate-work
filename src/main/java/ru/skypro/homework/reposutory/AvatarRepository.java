package ru.skypro.homework.reposutory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AvatarEntity;

public interface AvatarRepository extends JpaRepository<AvatarEntity, Long> {
    AvatarEntity findByUserId(Long userId);
}
