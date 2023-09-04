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

        Product product2 = new Product();
        product2.setImgPath("./img/product/t-shirt_img/b_opts.png");
        product2.setPrdtName("상품 2");
        product2.setPrdtPrice(12000);
        productService.addProduct(product2);

        Product product3 = new Product();
        product3.setImgPath("./img/product/t-shirt_img/b_scts.png");
        product3.setPrdtName("상품 3");
        product3.setPrdtPrice(9000);
        productService.addProduct(product3);

        Product product4 = new Product();
        product4.setImgPath("./img/product/t-shirt_img/g_snt.png");
        product4.setPrdtName("상품 4");
        product4.setPrdtPrice(13000);
        productService.addProduct(product4);

        Product product5 = new Product();
        product5.setImgPath("./img/product/t-shirt_img/w_ns.png");
        product5.setPrdtName("상품 5");
        product5.setPrdtPrice(12500);
        productService.addProduct(product5);

        Product product6 = new Product();
        product6.setImgPath("./img/product/t-shirt_img/w_ost.png");
        product6.setPrdtName("상품 6");
        product6.setPrdtPrice(10500);
        productService.addProduct(product6);

        Product product7 = new Product();
        product7.setImgPath("./img/product/t-shirt_img/w_rst.png");
        product7.setPrdtName("상품 7");
        product7.setPrdtPrice(9800);
        productService.addProduct(product7);

        Product product8 = new Product();
        product8.setImgPath("./img/product/t-shirt_img/wb_osts.png");
        product8.setPrdtName("상품 8");
        product8.setPrdtPrice(12000);
        productService.addProduct(product8);
    }
}