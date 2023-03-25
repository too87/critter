package com.udacity.jdnd.course3.critter.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Customer.listAll",
        query = "select c from Customer c"
)
@NamedQuery(
        name = "Customer.getOwnerByPetId",
        query = "select c from Customer c " +
                "left join c.pets p " +
                "where p.id = :petId"
)
@Entity
public class Customer extends Person{

    private String phoneNumber;
    private String notes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Customer setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public Customer setPets(List<Pet> pets) {
        this.pets = pets;
        return this;
    }

    public Customer addPet(Pet pet) {
        this.pets.add(pet);
        return this;
    }
}
