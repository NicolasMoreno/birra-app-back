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
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;
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

        // SECTORES, PERFILES, USUARIOS, EMPLEADOS
        addSectorsUserEmployee();

        // UNIDADES
        final UnitModel litros = new UnitModel(null, "Litro", "Lt.", QuantityType.REAL);
        final UnitModel kilos = new UnitModel(null, "Kilo", "kg.", QuantityType.REAL);
        final UnitModel unidades = new UnitModel(null, "Unidad", "ud.", QuantityType.INTEGER);
        final UnitModel temperatura = new UnitModel(null, "Celcius", "˚C", QuantityType.INTEGER);

        final UnitModel savedLitros = unitRepository.save(litros);
        final UnitModel savedKilos = unitRepository.save(kilos);
        final UnitModel savedUnidades = unitRepository.save(unidades);
        final UnitModel savedCelcius = unitRepository.save(temperatura);

        // Materiales

        final MaterialModel agua = new MaterialModel("Agua", savedLitros);
        final MaterialModel botellas = new MaterialModel("Botellas", savedUnidades);
        final MaterialModel chapitas = new MaterialModel("Chapitas", savedUnidades);
        final MaterialModel levadura = new MaterialModel("Levadura", savedKilos);
        final MaterialModel lupulo = new MaterialModel("Lúpulo", savedKilos);
        final MaterialModel malta = new MaterialModel("Malta", savedKilos);
        final MaterialModel miel = new MaterialModel("Miel", savedKilos);

        final MaterialModel aguaModel = materialService.createMaterial(agua.toDTO());
        final MaterialModel botellasModel = materialService.createMaterial(botellas.toDTO());
        final MaterialModel chapitasModel = materialService.createMaterial(chapitas.toDTO());
        final MaterialModel levaduraModel = materialService.createMaterial(levadura.toDTO());
        final MaterialModel lupuloModel = materialService.createMaterial(lupulo.toDTO());
        final MaterialModel maltaModel = materialService.createMaterial(malta.toDTO());
        final MaterialModel mielModel = materialService.createMaterial(miel.toDTO());

        // STOCK
        stockRepository.save(new StockModel(null, aguaModel, 50000D));
        stockRepository.save(new StockModel(null, botellasModel, 5000D));
        stockRepository.save(new StockModel(null, chapitasModel, 5000D));
        stockRepository.save(new StockModel(null, levaduraModel, 500D));
        stockRepository.save(new StockModel(null, lupuloModel, 100D));
        stockRepository.save(new StockModel(null, maltaModel, 100D));
        stockRepository.save(new StockModel(null, mielModel, 100D));


        final CreateProductDTO productDTO = generateProduct("Golden",
                "Cerveza Rubia 5% 4.5%",
                generateProductItem(lupuloModel.toDTO(), 0.005D),
                generateProductItem(levaduraModel.toDTO(), 0.0025D),
                generateProductItem(aguaModel.toDTO(), 0.34D),
                generateProductItem(chapitasModel.toDTO(), 1D),
                generateProductItem(botellasModel.toDTO(), 1D),
                generateProductItem(maltaModel.toDTO(), 0.25D),
                generateProductItem(mielModel.toDTO(), 0.05D)
        );

        productService.saveProduct(productDTO);

    }

    private CreateProductDTO generateProduct(String name, String description, ProductItemDTO ...productItems) {
        return new CreateProductDTO(name, description, productItems);
    }

    private ProductItemDTO generateProductItem(CreateMaterialDTO material, Double quantity) {
        return new ProductItemDTO(material, quantity);
    }

    private void addSectorsUserEmployee() {
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
    }


}
