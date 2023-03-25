package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PetRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Pet> list() {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.listAll", Pet.class);
        return query.getResultList();
    }

    public Pet save(Pet pet, Customer customer) {
        pet.setOwner(customer);
        Pet managedPet = entityManager.merge(pet);
        customer.addPet(managedPet);
        entityManager.flush();
        return managedPet;
    }

    public Pet findById(long petId) {
        return entityManager.find(Pet.class, petId);
    }

    public List<Pet> listByOwnerId(long ownerId) {
        TypedQuery<Pet> query = entityManager.createNamedQuery("Pet.listPetByOwner", Pet.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
