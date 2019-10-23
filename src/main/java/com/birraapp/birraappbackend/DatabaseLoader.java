package com.birraapp.birraappbackend;

import com.birraapp.birraappbackend.employee.EmployeeService;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.product.MaterialService;
import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.profile.ProfileService;
import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.sector.SectorService;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import com.birraapp.birraappbackend.user.UserService;
import com.birraapp.birraappbackend.user.model.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    MaterialService materialService;

    @Autowired
    ProductService productService;

    @Autowired
    ProfileService profileService;
    @Autowired
    SectorService sectorService;

    @Override
    public void run(String... strings) throws Exception {
        final CreateUserDTO user = new CreateUserDTO(
                "adminadmin",
                "admin",
                "istrator",
                "admin@mail.com",
                "admin123"
        );

        final SectorModel recirculado = new SectorModel("RECIRCULADO", new HashSet<>());
        final SectorModel embotellado_y_gasificado = new SectorModel("EMBOTELLADO Y GASIFICADO", new HashSet<>());
        final SectorModel enfriado = new SectorModel("ENFRIADO", new HashSet<>());
        final SectorModel molido = new SectorModel("MOLIDO", new HashSet<>());
        final SectorModel macerado = new SectorModel("MACERADO", new HashSet<>());
        final SectorModel fermentacion_y_madurado = new SectorModel("FERMENTACION Y MADURADO", new HashSet<>());
        final SectorModel hervido = new SectorModel("HERVIDO", new HashSet<>());

        final CreateProfileDTO admin = new CreateProfileDTO("Administrador");

        admin.getSectors().add(recirculado);
        admin.getSectors().add(embotellado_y_gasificado);
        admin.getSectors().add(enfriado);
        admin.getSectors().add(molido);
        admin.getSectors().add(macerado);
        admin.getSectors().add(fermentacion_y_madurado);
        admin.getSectors().add(hervido);
//        sectorService.saveSector(embotellado_y_gasificado);
//        sectorService.saveSector(enfriado);
//        sectorService.saveSector(molido);
//        sectorService.saveSector(macerado);
//        sectorService.saveSector(fermentacion_y_madurado);
//        sectorService.saveSector(hervido);
//        sectorService.saveSector(recirculado);
        final ProfileModel savedProfile = profileService.saveProfile(admin);

        final CreateEmployeeDTO firstEmployee = new CreateEmployeeDTO(
                user, savedProfile.toDTO()
        );

        employeeService.saveEmployee(firstEmployee);
    }


}
