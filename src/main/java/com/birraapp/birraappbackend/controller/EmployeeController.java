package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.employee.EmployeeService;
import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity addEmployee(@RequestBody CreateEmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @PutMapping("/")
    public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        if (updateEmployeeDTO.getId() == null) return ResponseEntity.badRequest().body("Usuario a editar no tiene ID");
        final Optional<EmployeeModel> employeeById = employeeService.getEmployeeById(updateEmployeeDTO.getId());
        if (!employeeById.isPresent()) return ResponseEntity.badRequest().body("Empleado a editar no encontrado");
        return ResponseEntity.ok(employeeService.updateEmployee(updateEmployeeDTO));
    }

    @GetMapping("/")
    public ResponseEntity getEmployeeById(@RequestParam Long employeeId) {
        final Optional<EmployeeModel> employeeById = employeeService.getEmployeeById(employeeId);
        if (employeeById.isPresent()) {
            return ResponseEntity.ok(employeeById.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emplpeado no encontrado");
        }
    }
}
