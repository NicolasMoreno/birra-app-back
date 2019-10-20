package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel saveProduct(CreateProductDTO createProductDTO) {
        return productRepository.save(createProductDTO.toModel());
    }

    public ProductModel updateproduct(UpdateProductDTO updateProductDTO) {
        return productRepository.save(updateProductDTO.toModel());
    }

    public Optional<ProductModel> findProductById(Long productId) {
        return productRepository.findById(productId);
    }

}
