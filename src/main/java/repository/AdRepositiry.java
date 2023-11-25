package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Ad;

import java.util.Optional;

@Repository
public interface AdRepositiry extends JpaRepository<Ad, Integer> {
    static Optional<Object> findByPk(Integer id) {
        return null;
    }
}
