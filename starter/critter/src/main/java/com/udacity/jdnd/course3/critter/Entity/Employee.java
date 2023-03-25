package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@NamedQuery(
        name = "Employee.findEmployeesForService",
        query = "SELECT e FROM Employee e " +
            "JOIN e.daysAvailable d " +
            "JOIN e.skills s " +
            "WHERE :dayOfWeek MEMBER OF d and s IN (:skill) " +
            "GROUP BY e.id"
)
@Entity
public class Employee extends Person{

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "day_of_week", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Employee setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
        return this;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public Employee setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
        return this;
    }
    public Employee addDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable.addAll(daysAvailable);
        return this;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Employee setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public Employee addSchedules(Schedule schedule) {
        this.schedules.add(schedule);
        return this;
    }
}
