package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.AbstractIntegrationTest;
import com.birraapp.birraappbackend.employee.EmployeeService;
import com.birraapp.birraappbackend.order.OrderService;
import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.product.model.QuantityType;
import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;

import com.birraapp.birraappbackend.stock.StockRepository;
import com.birraapp.birraappbackend.stock.StockService;
import com.birraapp.birraappbackend.stock.model.StockModel;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;

import java.util.Optional;


public class ProductServiceTest extends AbstractIntegrationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EmployeeService employeeService;

    private ProductModel testingProduct;
    private MaterialModel lupulo;
    private MaterialModel agua;
    private MaterialModel tapitas;
    private MaterialModel botellas;

    @Before
    public void setUp() {

        final UnitModel litrosUnit = unitService.saveUnit(new UnitModel(1L, "LITRO", "Lt.", QuantityType.REAL));
        final UnitModel kilosUnit = unitService.saveUnit(new UnitModel(2L, "KILO", "Kg.", QuantityType.REAL));
        final UnitModel unitsUnit = unitService.saveUnit(new UnitModel(3L, "UNIDADES", "un", QuantityType.INTEGER));

        unitService.saveUnit(litrosUnit);
        unitService.saveUnit(kilosUnit);
        unitService.saveUnit(unitsUnit);

        final CreateMaterialDTO material1 = generateMaterial("Lupulo", kilosUnit);
        final CreateMaterialDTO material2 = generateMaterial("Agua", litrosUnit);
        final CreateMaterialDTO material3 = generateMaterial("Tapitas", unitsUnit);
        final CreateMaterialDTO material4 = generateMaterial("Botellas", unitsUnit);
        lupulo = materialService.createMaterial(material1);
        agua = materialService.createMaterial(material2);
        tapitas = materialService.createMaterial(material3);
        botellas = materialService.createMaterial(material4);

        stockService.saveStock(new StockModel(null, agua, 500D));
        stockService.saveStock(new StockModel(null, lupulo, 5D));
        stockService.saveStock(new StockModel(null, tapitas, 50D));
        stockService.saveStock(new StockModel(null, botellas, 500D));

    }

    @Test
    public void testProduct() {
        final CreateProductDTO productDTO = generateProduct("Negra",
                "Cerveza Negra 5% 4.5%",
                generateProductItem(lupulo.toDTO(), 20D),
                generateProductItem(agua.toDTO(), 10D),
                generateProductItem(tapitas.toDTO(), 1D),
                generateProductItem(botellas.toDTO(), 1D)
        );

        testingProduct = productService.saveProduct(productDTO);
        Assert.assertNotNull("Asserting saved product has id", testingProduct.getId());

        Assert.assertTrue(
                "Testing if product has material1",
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(lupulo.getId()))
        );

        Assert.assertTrue(
                "Testing if product has material2",
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(agua.getId()))
        );

        Assert.assertTrue(
                "Testing if product has material3",
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(tapitas.getId()))
        );

        Assert.assertTrue(
                "Testing if product has material4",
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(botellas.getId()))
        );

        final UnitModel units = unitService.findUnit(3L).get();
        final MaterialModel newMaterial = materialService.createMaterial(generateMaterial("Etiquetas", units));
        testingProduct.getMaterials().add(generateProductItem(newMaterial.toDTO(), 200D).toModel(testingProduct));

        final ProductModel updatedTestingProduct = productService.updateProduct(testingProduct.toDTO());

        Assert.assertEquals("Asserting id has not changed", updatedTestingProduct.getId(), testingProduct.getId());
        Assert.assertTrue("Asserting has added new material", updatedTestingProduct.getMaterials().stream().anyMatch( item -> item.getMaterial().getId().equals(newMaterial.getId())));


    }

    @Test
    public void testProductStock() {
        final CreateProductDTO productDTO = generateProduct("Negra",
                "Cerveza Negra 5% 4.5%",
                generateProductItem(lupulo.toDTO(), 10D),
                generateProductItem(agua.toDTO(), 10D),
                generateProductItem(tapitas.toDTO(), 1D),
                generateProductItem(botellas.toDTO(), 1D)
        );
        final ProductModel productToTest = productService.saveProduct(productDTO);

        Assert.assertFalse(productService.checkProductAvailability(productToTest.getId(), 4000));

        // Aumento el stock para poder producir esa cantidad

        stockService.reStockMaterial(lupulo.getId(), 50000D);
        stockService.reStockMaterial(agua.getId(), 50000D);
        stockService.reStockMaterial(tapitas.getId(), 4100D);
        stockService.reStockMaterial(botellas.getId(), 4100D);

        final Optional<StockModel> stockByMaterialId = stockService.findStockByMaterialId(lupulo.getId());

        Assert.assertTrue(stockByMaterialId.isPresent());
        Assert.assertTrue(stockByMaterialId.get().getStoredQuantity() > 50000D);

        Assert.assertTrue(productService.checkProductAvailability(productToTest.getId(), 4000));

    }

    @Test
    public void testCreatingOrder() {
        final Double lupuloQuantity = 15D;
        final CreateProductDTO productDTO = generateProduct("Rubia",
                "Cerveza Rubia 3.5% 4.5%",
                generateProductItem(lupulo.toDTO(), lupuloQuantity),
                generateProductItem(agua.toDTO(), 20D),
                generateProductItem(tapitas.toDTO(), 1D),
                generateProductItem(botellas.toDTO(), 1D)
        );
        final ProductModel productToTest = productService.saveProduct(productDTO);

        stockService.reStockMaterial(lupulo.getId(), 49995D);
        stockService.reStockMaterial(agua.getId(), 50000D);
        stockService.reStockMaterial(tapitas.getId(), 4100D);
        stockService.reStockMaterial(botellas.getId(), 4100D);

        final RequestOrderDTO requestOrder = new RequestOrderDTO(productToTest.getId(), 2, "Hacer rapido por favor");

        final OrderModel newOrder = orderService.createNewOrder(productToTest, requestOrder);
        Assert.assertEquals(newOrder.getProduct().getId(), productToTest.getId());
        Assert.assertEquals(newOrder.getState(), OrderState.IN_PROGRESS);

        final Optional<StockModel> optionalLupulo = stockService.findStockByMaterialId(lupulo.getId());
        Assert.assertTrue(optionalLupulo.isPresent());
        Assert.assertEquals( 50000D - (lupuloQuantity * 2), optionalLupulo.get().getStoredQuantity(), 0.00001);

    }

    private ProductItemDTO generateProductItem(CreateMaterialDTO material, Double quantity) {
        return new ProductItemDTO(material, quantity);
    }

    private CreateProductDTO generateProduct(String name, String description, ProductItemDTO ...productItems) {
        return new CreateProductDTO(name, description, productItems);
    }

    private CreateMaterialDTO generateMaterial(String materialName, UnitModel unit) {
        return new CreateMaterialDTO(
                materialName, unit
        );
    }
}