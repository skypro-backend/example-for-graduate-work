package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.ProfileUser;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<ProfileUser, Integer> {

    Optional<ProfileUser> findByEmail(String email);
    @Query(nativeQuery = true, value = "select user_profile_id from profile_user where email like ?1")
    Integer getUserProfileId(String name);

}
