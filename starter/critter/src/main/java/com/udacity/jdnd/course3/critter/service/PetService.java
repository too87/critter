package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public List<Pet> listPet() {
        return petRepository.list();
    }

    public Pet save(Pet pet, Long ownerId) {
        Customer customer = customerRepository.findById(ownerId);
        if (nonNull(customer)) {
            return petRepository.save(pet, customer);
        } else {
            throw new UnsupportedOperationException("Owner not found");
        }
    }

    public Pet get(long petId) {
        Pet pet = petRepository.findById(petId);
        if (nonNull(pet)) {
            return pet;
        } else {
            throw new UnsupportedOperationException("Pet not found");
        }
    }


    public List<Pet> listPetByOwnerId(long ownerId) {
        return petRepository.listByOwnerId(ownerId);
    }
}
