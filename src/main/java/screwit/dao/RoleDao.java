package screwit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import screwit.model.Role;

import java.util.Set;


public interface RoleDao  {
    Role findByRole(String role);
    Set<Role> findAll();
}
