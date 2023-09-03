package com.jung9.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        // 데이터베이스에서 이미 같은 이름의 상품이 있는지 확인
        Product existingProduct = productRepository.findByPrdtName(product.getPrdtName());

        if (existingProduct == null) {
            // 중복된 상품이 없으면 저장
            return productRepository.save(product);
        } else {
            // 중복된 상품이 이미 존재함
            return existingProduct;
        }
    }

}