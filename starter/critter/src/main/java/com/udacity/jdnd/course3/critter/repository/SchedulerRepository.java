package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
@Transactional
public class SchedulerRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Schedule merge(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    public Schedule save(Schedule schedule) {
        entityManager.flush();
        return schedule;
    }

    public List<Schedule> list() {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.listAll", Schedule.class);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.getScheduleForPet", Schedule.class);
        query.setParameter("petId", petId);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.getScheduleForEmployee", Schedule.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.getScheduleForCustomer", Schedule.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public Schedule getScheduleByDate(LocalDate date) {
        try {
            TypedQuery<Schedule> query = entityManager.createNamedQuery("Schedule.getScheduleByDate", Schedule.class);
            query.setParameter("localDate", date);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
