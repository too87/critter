package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NamedQuery(
        name = "Pet.listAll",
        query = "select p from Pet p"
)
@NamedQuery(
        name = "Pet.listPetByOwner",
        query = "select p from Pet p where p.owner.id = :ownerId"
)
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PetType type;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    //ownerId
    private Customer owner;

    @ManyToMany(mappedBy = "pets", fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();
    private LocalDate birthDate;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public Pet setType(PetType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public Customer getOwner() {
        return owner;
    }

    public Pet setOwner(Customer owner) {
        this.owner = owner;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pet setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Pet setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Pet setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }
}
