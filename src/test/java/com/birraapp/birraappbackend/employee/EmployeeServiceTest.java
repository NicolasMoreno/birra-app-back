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
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.jvm.hotspot.utilities.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

//    @Test
//    public void ProfileTest() {
//
//    }

    @Test
    public void ABMEmployeeTest() {
        final EmployeeModel savedEmployee = employeeService.saveEmployee(testingEmployee);

        Assert.that(savedEmployee.getId() != null, "Asserting savedEmployee has ID");
        Assert.that(savedEmployee.getUser().getUsername().equalsIgnoreCase(testingEmployee.getUser().getUsername()), "Asserting content is not changed");
        Assert.that(savedEmployee.getProfile().getSectors().size() == 2, "Asserting has 2 sectors profile");
        Assert.that(savedEmployee.getProfile().getName().equals("admin"), "Asserting has admin profile");

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