package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;


@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee get(long employeeId) {
        Employee employee = employeeRepository.get(employeeId);
        if (nonNull(employee)) {
            return employee;
        } else {
            throw new UnsupportedOperationException("Employee not found");
        }
    }

    public Employee setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = get(employeeId);
        return employeeRepository.setAvailability(daysAvailable, employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return employeeRepository.findEmployeesForService(employeeDTO);
    }
}
