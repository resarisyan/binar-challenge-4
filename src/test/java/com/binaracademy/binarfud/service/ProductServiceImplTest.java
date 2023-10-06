package com.binaracademy.binarfud.service;

import static org.junit.jupiter.api.Assertions.*;

import com.binaracademy.binarfud.model.Merchant;
import com.binaracademy.binarfud.model.Product;
import com.binaracademy.binarfud.repository.MerchantRepository;
import com.binaracademy.binarfud.repository.ProductRepository;
import com.binaracademy.binarfud.response.ProductResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private MerchantRepository merchantRepository;

    @BeforeAll
    public void setUp() {
        System.out.println("Before each");
        Merchant merchant = new Merchant();
        merchant.setMerchantName("SampleMerchant");
        merchant.setMerchantLocation("SampleLocation");
        merchant.setOpen(true);
        merchantRepository.save(merchant);
    }

    @Test
    public void testAddNewProduct() {
        Merchant merchant = merchantRepository.findByMerchantName("SampleMerchant");
        Product product = Product.builder().
                productName("SampleProduct").
                price(100.0).
                merchant(merchant).
                build();
        boolean result = productService.addNewProduct(product);
        assertTrue(result);
    }

    @Test
    public void testUpdateProduct() {
        Merchant merchant = merchantRepository.findByMerchantName("SampleMerchant");
        Product product = Product.builder().
                productName("SampleProduct").
                price(200.0).
                merchant(merchant).
                build();
        boolean result = productService.updateProduct("SampleProduct", product);
        assertTrue(result);
    }

    @Test
    public void testDeleteProduct() {
        boolean result = productService.deleteProduct("SampleProduct");
        assertTrue(result);
    }

//    @Test
//    public void testGetProductDetail() {
//        Product product = new Product();
//        product.setProductName("SampleProduct");
//        product.setPrice(100.0);
//        Merchant merchant = merchantRepository.findByMerchantName("SampleMerchant");
//        product.setMerchant(merchant);
//        productRepository.save(product);
//
//        ProductResponse response = productService.getProductDetail("SampleProduct");
//
//        assertNotNull(response);
//        assertEquals("SampleProduct", response.getProductName());
//        assertEquals(merchant, response.getMerchant());
//        assertEquals(100.0, response.getPrice());
//    }
}
