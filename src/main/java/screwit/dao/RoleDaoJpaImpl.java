package screwit.dao;

import org.springframework.stereotype.Repository;
import screwit.model.Role;
import screwit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
public class RoleDaoJpaImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role findByRole(String role) {

        List<Role> roles = new ArrayList<>();
        Query query = entityManager.createQuery( "from Role where role = :roleName");
        query.setParameter("roleName", role);
        roles =  query.getResultList();

        if (!roles.isEmpty()){
            return  roles.get(0);
        }
        return null;
    }

    @Override
    public Set<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        Query query = entityManager.createQuery( "from Role");
        roles =  query.getResultList();
        Set<Role> rolesSet = new HashSet(roles);
        return rolesSet;
    }
}
