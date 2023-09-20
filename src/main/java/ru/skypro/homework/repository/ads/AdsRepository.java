package ru.skypro.homework.repository.ads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.users.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByAuthor(User author);

    Optional<Ad> findByAuthorAndPk(User author, Integer pk);

    Optional<Ad> findByPkIs(Integer pk);

}
