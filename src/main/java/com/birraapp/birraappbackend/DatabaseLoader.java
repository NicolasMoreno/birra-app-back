package com.birraapp.birraappbackend;

import com.birraapp.birraappbackend.controller.StockController;
import com.birraapp.birraappbackend.employee.EmployeeService;
import com.birraapp.birraappbackend.employee.model.dto.CreateEmployeeDTO;
import com.birraapp.birraappbackend.product.MaterialService;
import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.product.UnitRepository;
import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.QuantityType;
import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.profile.ProfileService;
import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.sector.SectorService;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import com.birraapp.birraappbackend.stock.StockRepository;
import com.birraapp.birraappbackend.stock.model.StockModel;
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
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    StockRepository stockRepository;

    @Override
    public void run(String... strings) throws Exception {
        final CreateUserDTO user = new CreateUserDTO(
                "adminadmin",
                "admin",
                "istrator",
                "admin@mail.com",
                "admin123"
        );


        // SECTORES, PERFILES, USUARIOS, EMPLEADOS
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
        sectorService.saveSector(embotellado_y_gasificado);
        sectorService.saveSector(enfriado);
        sectorService.saveSector(molido);
        sectorService.saveSector(macerado);
        sectorService.saveSector(fermentacion_y_madurado);
        sectorService.saveSector(hervido);
        sectorService.saveSector(recirculado);
        final ProfileModel savedProfile = profileService.saveProfile(admin);

        final CreateEmployeeDTO firstEmployee = new CreateEmployeeDTO(
                user, savedProfile.toDTO()
        );

        employeeService.saveEmployee(firstEmployee);

        // UNIDADES

        final UnitModel litros = new UnitModel(null, "Litros", "Lt.", QuantityType.REAL);
        final UnitModel kilos = new UnitModel(null, "Kilos", "kg.", QuantityType.REAL);
        final UnitModel unidades = new UnitModel(null, "Unidades", "ud.", QuantityType.INTEGER);
        final UnitModel savedLitros = unitRepository.save(litros);
        final UnitModel savedKilos = unitRepository.save(kilos);
        final UnitModel savedUnidades = unitRepository.save(unidades);

        // Materiales

        final MaterialModel agua = new MaterialModel("Agua", savedLitros);
        final MaterialModel botellas = new MaterialModel("Botellas", savedUnidades);
        final MaterialModel chapitas = new MaterialModel("Chapitas", savedUnidades);
        final MaterialModel levadura = new MaterialModel("Levadura", savedKilos);
        final MaterialModel lupulo = new MaterialModel("Lúpulo", savedKilos);
        final MaterialModel malta = new MaterialModel("Malta", savedKilos);
        final MaterialModel miel = new MaterialModel("Miel", savedKilos);

        final MaterialModel material1 = materialService.createMaterial(agua.toDTO());
        final MaterialModel material2 = materialService.createMaterial(botellas.toDTO());
        final MaterialModel material3 = materialService.createMaterial(chapitas.toDTO());
        final MaterialModel material4 = materialService.createMaterial(levadura.toDTO());
        final MaterialModel material5 = materialService.createMaterial(lupulo.toDTO());
        final MaterialModel material6 = materialService.createMaterial(malta.toDTO());
        final MaterialModel material7 = materialService.createMaterial(miel.toDTO());

        // STOCK

        stockRepository.save(new StockModel(null, material1, 50000D));
        stockRepository.save(new StockModel(null, material2, 5000D));
        stockRepository.save(new StockModel(null, material3, 5000D));
        stockRepository.save(new StockModel(null, material4, 500D));
        stockRepository.save(new StockModel(null, material5, 100D));
        stockRepository.save(new StockModel(null, material6, 100D));
        stockRepository.save(new StockModel(null, material7, 100D));


    }


}
