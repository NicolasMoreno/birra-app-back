package com.birraapp.birraappbackend.employee;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeModel saveEmployee(CreateEmployeeDTO createEmployeeDTO) {
        return employeeRepository.save(createEmployeeDTO.toModel());
    }

    public EmployeeModel updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        return employeeRepository.save(updateEmployeeDTO.toModel());
    }

    public boolean deleteEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        employeeRepository.delete(updateEmployeeDTO.toModel());
        return true;
    }

    public Optional<EmployeeModel> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Iterable<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }
}
