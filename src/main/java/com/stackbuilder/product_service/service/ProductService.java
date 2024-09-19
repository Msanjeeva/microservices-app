package com.stackbuilder.product_service.service;

import com.stackbuilder.product_service.dto.ProductRequest;
import com.stackbuilder.product_service.dto.ProductResponse;
import com.stackbuilder.product_service.model.Product;
import com.stackbuilder.product_service.repository.ProductRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private  ProductResponse mapToProductResponse(Product products) {
        return ProductResponse.builder()
                .id(products.getId())
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice()).build();
    }


    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved", product.getId());


    }
}
