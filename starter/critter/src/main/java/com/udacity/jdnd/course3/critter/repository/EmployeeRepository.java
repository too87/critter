package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;

@Repository
@Transactional
public class EmployeeRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Employee save(Employee employee) {
        Employee mergedEmployee = entityManager.merge(employee);
        entityManager.flush();
        return mergedEmployee;
    }

    public Employee get(long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    public Employee setAvailability(Set<DayOfWeek> daysAvailable, Employee employee) {
        employee.setDaysAvailable(daysAvailable);
        Employee managedEmployee = entityManager.merge(employee);
        entityManager.flush();
        return managedEmployee;
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>(employeeDTO.getSkills().size());
        for (EmployeeSkill skill : employeeDTO.getSkills()) {
            predicates.add(cb.isMember(skill, root.get("skills")));
        }
        query.select(root);
        query.where(cb.and(
            cb.isMember(dayOfWeek, root.get("daysAvailable")),
            cb.and(predicates.toArray(new Predicate[0])))
        );

        return entityManager.createQuery(query).getResultList();
    }
}
