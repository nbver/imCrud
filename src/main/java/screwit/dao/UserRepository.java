package screwit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import screwit.model.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();
}
