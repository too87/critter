package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulerRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ScheduleService {
    @Autowired
    SchedulerRepository schedulerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;

    //Create or update schedule
    public Schedule create(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        Schedule scheduleByDate = schedulerRepository.getScheduleByDate(schedule.getDate());

        List<Employee> employees = new ArrayList<>();
        if (nonNull(employeeIds)) {
            employees = employeeIds.stream()
                .map(employeeId ->  employeeRepository.get(employeeId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }
        List<Pet> pets = new ArrayList<>();
        if (nonNull(petIds)) {
            pets = petIds.stream()
                .map(petId -> petRepository.findById(petId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }

        if (nonNull(scheduleByDate)) {
            scheduleByDate.addActivity(schedule.getActivities());
            scheduleByDate.addEmployee(employees);
            scheduleByDate.addPet(pets);
            return schedulerRepository.save(scheduleByDate);
        } else {
            Schedule mergedSchedule = schedulerRepository.merge(schedule);
            mergedSchedule.addActivity(schedule.getActivities());
            mergedSchedule.addEmployee(employees);
            mergedSchedule.addPet(pets);
            return schedulerRepository.save(mergedSchedule);
        }
    }

    public List<Schedule> listAll() {
        return schedulerRepository.list();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return schedulerRepository.getScheduleForPet(petId);
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return schedulerRepository.getScheduleForEmployee(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return schedulerRepository.getScheduleForCustomer(customerId);
    }
}
