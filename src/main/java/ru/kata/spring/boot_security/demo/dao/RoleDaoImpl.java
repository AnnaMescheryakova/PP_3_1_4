package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select role from Role role where role.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getResultStream().collect(Collectors.toSet());
    }
}
