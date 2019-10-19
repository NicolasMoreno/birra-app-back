package com.birraapp.birraappbackend.employee;

import com.birraapp.birraappbackend.AbstractIntegrationTest;
import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.user.UserService;
import com.birraapp.birraappbackend.user.dto.CreateUserDTO;
import com.birraapp.birraappbackend.user.model.UserModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Optional;


public class EmployeeServiceTest extends AbstractIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    private CreateEmployeeDTO testingEmployee;

    @Test
    public void ABMEmployeeTest() {
        testingEmployee = generateCreateEmployee();
        final EmployeeModel savedEmployee = employeeService.saveEmployee(testingEmployee);

        Assert.that(savedEmployee.getId() != null, "Asserting savedEmployee has ID");
        Assert.that(savedEmployee.getUser().getUsername().equalsIgnoreCase(testingEmployee.getUser().getUsername()), "Asserting content is not changed");

        final Optional<EmployeeModel> optionalFoundEmployee = employeeService.getEmployeeById(savedEmployee.getId());
        Assert.that(optionalFoundEmployee.isPresent(), "Asserting is saved on DB");

        final EmployeeModel employee = optionalFoundEmployee.get();
        employee.getUser().setUsername("changedUsernameTest");

        final EmployeeModel updatedEmployee = employeeService.updateEmployee(employee.toDTO());

        Assert.that(updatedEmployee.getId().equals(employee.getId()), "Asserting updated id is equals");
        Assert.that(updatedEmployee.getUser().getUsername().equals(employee.getUser().getUsername()), "Asserting content has updated");

        final String userId = updatedEmployee.getUser().getId();

        employeeService.deleteEmployee(updatedEmployee.toDTO());

        final Optional<EmployeeModel> optionalFoundEmployee2 = employeeService.getEmployeeById(updatedEmployee.getId());
        Assert.that(!optionalFoundEmployee2.isPresent(), "Asserting Deleted Employee");

        final Optional<UserModel> userById = userService.getUserById(userId);

        Assert.that(!userById.isPresent(), "Asserting user is deleted also");

    }


    private CreateEmployeeDTO generateCreateEmployee() {
        final CreateUserDTO user = new CreateUserDTO(
                "test",
                "tester",
                "retset",
                "test@test.com",
                "tester1337"
        );

        return new CreateEmployeeDTO(
                user
        );
    }
}