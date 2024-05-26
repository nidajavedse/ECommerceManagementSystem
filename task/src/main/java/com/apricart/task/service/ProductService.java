package com.apricart.task.service;

import com.apricart.task.model.Product;
import com.apricart.task.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        logger.info("Creating product: {}", product);
        Product createdProduct = productRepository.save(product);
        logger.info("Product created successfully: {}", createdProduct);
        return createdProduct;
    }

    public Product updateProduct(Long productId, Product productDetails) {
        logger.info("Updating product with ID {}: {}", productId, productDetails);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setProductName(productDetails.getProductName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setBrand(productDetails.getBrand());
        product.setStatus(productDetails.getStatus());
        product.setWarehouse(productDetails.getWarehouse());

        Product updatedProduct = productRepository.save(product);
        logger.info("Product updated successfully: {}", updatedProduct);
        return updatedProduct;
    }

    public void deleteProduct(Long productId) {
        logger.info("Deleting product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
        logger.info("Product deleted successfully with ID: {}", productId);
    }

    public Product getProductById(Long productId) {
        logger.info("Retrieving product by ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        logger.info("Product retrieved successfully by ID: {}", productId);
        return product;
    }
}

