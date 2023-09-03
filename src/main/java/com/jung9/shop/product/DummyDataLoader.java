package com.jung9.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyDataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        // 더미 데이터 생성 및 저장
        Product product = new Product();
        product.setImgPath("/img/product/t-shirt_img/b_mst.png");
        product.setPrdtName("상품 1");
        product.setPrdtPrice(10000);
//        productRepository.save(product);
        productService.addProduct(product);

        // 원하는 만큼 더미 데이터를 생성하고 저장
    }
}