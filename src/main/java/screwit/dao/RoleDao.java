package screwit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import screwit.model.Role;

import java.util.List;
import java.util.Set;


public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRole(String role);
    List<Role> findAll();
}
