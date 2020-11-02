package screwit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import screwit.model.Role;


public interface RoleDao  {
    Role findByRole(String role);
}
