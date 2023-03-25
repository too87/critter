package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NamedQuery(
        name = "Schedule.listAll",
        query = "select s from Schedule s"
)
@NamedQuery(
        name = "Schedule.getScheduleForPet",
        query = "select s from Schedule s " +
                "join s.pets p where p.id = :petId"
)
@NamedQuery(
        name = "Schedule.getScheduleForEmployee",
        query = "select s from Schedule s " +
                "join s.employees e where e.id = :employeeId"
)
@NamedQuery(
        name = "Schedule.getScheduleForCustomer",
        query = "select s from Schedule s " +
                "join s.pets e " +
                "join e.owner o " +
                "where o.id = :customerId"
)
@NamedQuery(
        name = "Schedule.getScheduleByDate",
        query = "select s from Schedule s " +
                "where s.date =:localDate"
)
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="schedule_employees", joinColumns={@JoinColumn(name = "schedule_id")}
                , inverseJoinColumns={@JoinColumn(name = "employee_id")})
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="schedule_pets", joinColumns={@JoinColumn(name = "schedule_id")}
       , inverseJoinColumns={@JoinColumn(name = "pet_id")})
    private Set<Pet> pets = new HashSet<>();

    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "employee_activity", joinColumns = @JoinColumn(name = "schedule_id"))
        @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Schedule setEmployees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Schedule addEmployee(List<Employee> employee) {
        this.employees.addAll(employee);
        return this;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public Schedule setPets(Set<Pet> pets) {
        this.pets = pets;
        return this;
    }
    public Schedule addPet(List<Pet> pet) {
        this.pets.addAll(pet);
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Schedule setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public Schedule setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
        return this;
    }

    public Schedule addActivity(Set<EmployeeSkill> activity) {
        this.activities.addAll(activity);
        return this;
    }
}
