package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.ProductItem;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;
import com.birraapp.birraappbackend.stock.StockRepository;
import com.birraapp.birraappbackend.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductItemRepository productItemRepository;


    @Autowired
    private StockService stockService;

    public ProductModel saveProduct(CreateProductDTO createProductDTO) {
        return productRepository.save(createProductDTO.toModel());
    }

    public ProductModel updateProduct(UpdateProductDTO updateProductDTO) {
        return productRepository.save(updateProductDTO.toModel());
    }

    public Optional<ProductModel> findProductById(Long productId) {
        return productRepository.findById(productId);
    }


    // TODO check unit test of this.
    public boolean checkProductAvailability(Long productId, Integer productAmount) {
        final List<ProductItem> productItems = productItemRepository.findByProduct_Id(productId);
        return productItems.stream()
                .allMatch(item -> stockService.checkMaterialStock(item.getId(), item.getQuantity() * productAmount));
    }

}
