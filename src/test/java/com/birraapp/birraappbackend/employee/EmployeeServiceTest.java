package com.birraapp.birraappbackend.employee;

import com.birraapp.birraappbackend.AbstractIntegrationTest;
import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.profile.ProfileService;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.sector.SectorService;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import com.birraapp.birraappbackend.user.UserService;
import com.birraapp.birraappbackend.user.model.dto.CreateUserDTO;
import com.birraapp.birraappbackend.user.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


public class EmployeeServiceTest extends AbstractIntegrationTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private ProfileService profileService;

    private CreateEmployeeDTO testingEmployee;
    private CreateProfileDTO testingProfile;
    private List<SectorModel> sectorList;

    @Before
    public void setup() throws Exception {
        generateProfile();
        final SectorModel aSector = newSector("A");
        final SectorModel bSector = newSector("B");
        testingProfile.getSectors().add(aSector);
        testingProfile.getSectors().add(bSector);
        testingProfile = profileService.saveProfile(testingProfile).toDTO();
        testingEmployee = generateCreateEmployee();

    }


    @Test
    public void ABMEmployeeTest() {
        final EmployeeModel savedEmployee = employeeService.saveEmployee(testingEmployee);

        Assert.assertNotNull("Asserting savedEmployee has ID", savedEmployee.getId());
        Assert.assertTrue("Asserting content is not changed",savedEmployee.getUser().getUsername().equalsIgnoreCase(testingEmployee.getUser().getUsername()));
        Assert.assertEquals("Asserting has 2 sectors profile", 2, savedEmployee.getProfile().getSectors().size());
        Assert.assertEquals("Asserting has admin profile", "admin", savedEmployee.getProfile().getName());

        final Optional<EmployeeModel> optionalFoundEmployee = employeeService.getEmployeeById(savedEmployee.getId());
        Assert.assertTrue("Asserting is saved on DB", optionalFoundEmployee.isPresent());

        final EmployeeModel employee = optionalFoundEmployee.get();
        employee.getUser().setUsername("changedUsernameTest");

        final EmployeeModel updatedEmployee = employeeService.updateEmployee(employee.toDTO());

        Assert.assertEquals("Asserting updated id is equals", updatedEmployee.getId(), employee.getId());
        Assert.assertEquals("Asserting content has updated", updatedEmployee.getUser().getUsername(), employee.getUser().getUsername());

        final String userId = updatedEmployee.getUser().getId();

        employeeService.deleteEmployee(updatedEmployee.toDTO());

        final Optional<EmployeeModel> optionalFoundEmployee2 = employeeService.getEmployeeById(updatedEmployee.getId());
        Assert.assertFalse(optionalFoundEmployee2.isPresent());

        final Optional<UserModel> userById = userService.getUserById(userId);

        Assert.assertFalse(userById.isPresent());

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
                user, testingProfile
        );
    }

    private void generateProfile() {
        testingProfile = new CreateProfileDTO(
                "admin"
        );
    }

    private SectorModel newSector(String name) {
        return new SectorModel(name, new HashSet<>());
    }
}