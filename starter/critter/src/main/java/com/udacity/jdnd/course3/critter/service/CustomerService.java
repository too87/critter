package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> listCustomer() {
        return customerRepository.list();
    }

    public Customer getOwnerByPetId(long petId) {
        Pet pet = petRepository.findById(petId);
        if (isNull(pet)) {
            throw new UnsupportedOperationException("Pet not found");
        }
        return customerRepository.getOwnerByPetId(petId);
    }
}
