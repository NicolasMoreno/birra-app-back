package com.birraapp.birraappbackend.order;

import com.birraapp.birraappbackend.AbstractIntegrationTest;
import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.dto.ChangeOrderStatusDTO;
import com.birraapp.birraappbackend.order.model.dto.CreateOrderDTO;
import com.birraapp.birraappbackend.product.MaterialService;
import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.product.UnitService;
import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.product.model.QuantityType;
import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.birraapp.birraappbackend.TestUtils.*;
import static com.birraapp.birraappbackend.TestUtils.generateMaterial;

public class OrderServiceTest extends AbstractIntegrationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private UnitService unitService;

    @Test
    public void updateOrderProcess() {

        final UnitModel litrosUnit = unitService.saveUnit(new UnitModel(1, "LITRO", "Lt.", QuantityType.REAL));
        final UnitModel kilosUnit = unitService.saveUnit(new UnitModel(2, "KILO", "Kg.", QuantityType.REAL));
        final UnitModel unitsUnit = unitService.saveUnit(new UnitModel(3, "UNIDAD", "un", QuantityType.INTEGER));
        final UnitModel celcius = unitService.saveUnit(new UnitModel(4, "CELCIUS", "˚C", QuantityType.REAL));

        final CreateMaterialDTO material1 = generateMaterial("Lupulo", kilosUnit);
        final CreateMaterialDTO material2 = generateMaterial("Agua", litrosUnit);
        final CreateMaterialDTO material3 = generateMaterial("Tapitas", unitsUnit);
        final CreateMaterialDTO material4 = generateMaterial("Botellas", unitsUnit);

        final MaterialModel lupulo = materialService.createMaterial(material1);
        final MaterialModel agua = materialService.createMaterial(material2);
        final MaterialModel tapitas = materialService.createMaterial(material3);
        final MaterialModel botellas = materialService.createMaterial(material4);

        final Double lupuloQuantity = 15D;
        final CreateProductDTO productDTO = generateProduct("Rubia",
                "Cerveza Rubia 3.5% 4.5%",
                generateProductItem(lupulo.toDTO(), lupuloQuantity),
                generateProductItem(agua.toDTO(), 20D),
                generateProductItem(tapitas.toDTO(), 1D),
                generateProductItem(botellas.toDTO(), 1D)
        );
        final ProductModel productToTest = productService.saveProduct(productDTO);
        OrderModel orderModel = orderService.addOrder(CreateOrderDTO.startNewOrder(productToTest.toDTO(), 20, "Test", unitService.getAllUnits()));
        Assert.assertNotNull("Asserting order has ID", orderModel.getId());
        Assert.assertEquals(orderModel.getState(), OrderState.NO_EMPEZADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MOLIENDA, OrderState.EN_PROGRESO));
        Assert.assertEquals(orderModel.getState(), OrderState.EN_PROGRESO);
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MOLIENDA, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.MACERADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MACERADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MACERADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.RECIRCULADO_LAVADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.RECIRCULADO_LAVADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.RECIRCULADO_LAVADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.HERVIDO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.HERVIDO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.HERVIDO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.ENFRIADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.ENFRIADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.ENFRIADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.FERMENTACION);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.FERMENTACION, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.FERMENTACION, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.MADURADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MADURADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.MADURADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.EMBOTELLADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.EMBOTELLADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.EMBOTELLADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.GASIFICADO);
        Assert.assertNotEquals(orderModel.getState(), OrderState.FINALIZADO);

        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.GASIFICADO, OrderState.EN_PROGRESO));
        orderModel = orderService.updateOrderProcess(orderModel.toDTO(), generateChangeOrder(orderModel.getId(), OrderProcess.GASIFICADO, OrderState.FINALIZADO));
        Assert.assertEquals(orderModel.toDTO().getActualProcess(), OrderProcess.GASIFICADO);
        Assert.assertEquals(orderModel.getState(), OrderState.FINALIZADO);

    }

    private ChangeOrderStatusDTO generateChangeOrder(Long orderId, OrderProcess process, OrderState state) {
        return new ChangeOrderStatusDTO(orderId, Math.random(), process, state);
    }
}