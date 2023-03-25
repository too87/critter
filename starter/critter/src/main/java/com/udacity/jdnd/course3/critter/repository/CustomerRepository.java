package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Customer save(Customer customer) {
        Customer mergedEntity = entityManager.merge(customer);
        entityManager.flush();
        entityManager.refresh(mergedEntity);
        return mergedEntity;
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> list() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.listAll", Customer.class);
        return query.getResultList();
    }

    public Customer getOwnerByPetId(long petId) {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.getOwnerByPetId", Customer.class);
        query.setParameter("petId", petId);
        return query.getSingleResult();
    }
}
