package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.data.entity.AccountEntity;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    @Query(
            "select a from AccountEntity a join fetch a.profile where a.email = :email"
    )
    Optional<AccountEntity> findByEmailWithProfile(@Param("email") String email);
}
