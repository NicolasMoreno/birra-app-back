package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.AbstractIntegrationTest;
import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.product.model.QuantityType;
import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sun.jvm.hotspot.utilities.Assert;

public class ProductServiceTest extends AbstractIntegrationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private UnitService unitService;

    private ProductModel testingProduct;
    private MaterialModel lupulo;
    private MaterialModel agua;
    private MaterialModel tapitas;
    private MaterialModel botellas;

    @Before
    public void setUp() {

        final UnitModel litrosUnit = unitService.saveUnit(new UnitModel("1", "LITRO", "Lt.", QuantityType.REAL));
        final UnitModel kilosUnit = unitService.saveUnit(new UnitModel("2", "KILO", "Kg.", QuantityType.REAL));
        final UnitModel unitsUnit = unitService.saveUnit(new UnitModel("3", "UNIDADES", "un", QuantityType.INTEGER));

        final CreateMaterialDTO material1 = generateMaterial("Lupulo", kilosUnit);
        final CreateMaterialDTO material2 = generateMaterial("Agua", litrosUnit);
        final CreateMaterialDTO material3 = generateMaterial("Tapitas", unitsUnit);
        final CreateMaterialDTO material4 = generateMaterial("Botellas", unitsUnit);
        lupulo = materialService.createMaterial(material1);
        agua = materialService.createMaterial(material2);
        tapitas = materialService.createMaterial(material3);
        botellas = materialService.createMaterial(material4);

    }

    @Test
    public void testProduct() {
        final CreateProductDTO productDTO = generateProduct("Negra",
                "Cerveza Negra 5% 4.5%",
                generateProductItem(lupulo.toDTO(), 20D),
                generateProductItem(agua.toDTO(), 10D),
                generateProductItem(tapitas.toDTO(), 2D),
                generateProductItem(botellas.toDTO(), 200D)
        );

        testingProduct = productService.saveProduct(productDTO);
        Assert.that(testingProduct.getId() != null, "Asserting saved product has id");

        Assert.that(
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(lupulo.getId())),
                "Testing if product has material1"
        );

        Assert.that(
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(agua.getId())),
                "Testing if product has material2"
        );

        Assert.that(
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(tapitas.getId())),
                "Testing if product has material3"
        );

        Assert.that(
                testingProduct.getMaterials().stream().anyMatch(item -> item.getMaterial().getId().equals(botellas.getId())),
                "Testing if product has material4"
        );

        final UnitModel units = unitService.findUnit("3").get();
        final MaterialModel newMaterial = materialService.createMaterial(generateMaterial("Etiquetas", units));
        testingProduct.getMaterials().add(generateProductItem(newMaterial.toDTO(), 200D).toModel(testingProduct));

        final ProductModel updatedTestingProduct = productService.updateProduct(testingProduct.toDTO());

        Assert.that(updatedTestingProduct.getId().equals(testingProduct.getId()), "Asserting id has not changed");
        Assert.that(updatedTestingProduct.getMaterials().stream().anyMatch( item -> item.getMaterial().getId().equals(newMaterial.getId())),
                "Asserting has added new material");


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