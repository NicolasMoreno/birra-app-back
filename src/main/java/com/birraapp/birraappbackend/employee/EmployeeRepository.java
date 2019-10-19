package com.birraapp.birraappbackend.employee;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Long> {


}
